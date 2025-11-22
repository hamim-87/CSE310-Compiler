parser grammar C2105160Parser;

options {
    tokenVocab = C2105160Lexer;
}

@parser::header {
    #include <iostream>
    #include <fstream>
    #include <string>
    #include <cstdlib>
    #include "C2105160Lexer.h"
	#include "headers/str_list.cpp"
	#include "symbol_table/2105160_symbol_table.hpp"
	#include <vector>
	#include "symbol_table/2105160_symbol_info.hpp"

	#include <sstream>
	#include <utility> 
	



    extern std::ofstream parserLogFile;
    extern std::ofstream errorFile;

    extern int syntaxErrorCount;
	extern SymbolTable symbol_table;

}

@parser::members {
		void removeLinesFromLogFile() {
			const std::string filename = "output/parserLog.txt";

			std::ifstream fileIn(filename);
			if (!fileIn) {
				std::cerr << "Error opening " << filename << " for reading" << std::endl;
				return;
			}

			std::vector<std::string> lines;
			std::string line;
			while (std::getline(fileIn, line)) {
				lines.push_back(line);
			}
			fileIn.close();

			if (lines.size() >= 4) {
				lines.pop_back();
				lines.pop_back();
				lines.pop_back();
				lines.pop_back();
			} else if (!lines.empty()) {
				lines.pop_back();
			}

			// Close the global ofstream before rewriting the file
			parserLogFile.close();

			std::ofstream fileOut(filename, std::ios::trunc);
			if (!fileOut) {
				std::cerr << "Error opening " << filename << " for writing" << std::endl;
				return;
			}

			for (const auto& l : lines) {
				fileOut << l << '\n';
			}
			fileOut.close();

			// Reopen the global ofstream for further use
			parserLogFile.open(filename, std::ios::app);
			if (!parserLogFile.is_open()) {
				std::cerr << "Error reopening parserLogFile after modification." << std::endl;
			}
		}


    void writeIntoparserLogFile(const std::string message) {
        if (!parserLogFile) {
            std::cout << "Error opening parserLogFile.txt" << std::endl;
            return;
        }

        parserLogFile << message << std::endl;
        parserLogFile.flush();
    }

    void writeIntoErrorFile(const std::string message) {
        if (!errorFile) {
            std::cout << "Error opening errorFile.txt" << std::endl;
            return;
        }
        errorFile << message << std::endl;
        errorFile.flush();
    }

	// void handleInsert(const std::vector<std::string>& variables, const std::string& type)
	// {
	// 	for (const auto& var : variables) {
	// 		if (!symbol_table.Insert(var, type)) {
	// 			writeIntoErrorFile("Error: Variable '" + var + "' already declared.");
	// 			syntaxErrorCount++;
	// 		}
	// 	}
	// }

	std:: string type;
	void handle_insert(std::string id, std::string type,std::string line) {

		if(!symbol_table.Insert(id, type)) {
			writeIntoErrorFile("Error at line "+line+": Multiple declaration of "+id+"\n");
			writeIntoparserLogFile("Error at line "+line+": Multiple declaration of "+id+"\n\n");
			syntaxErrorCount++;
		}

	}


	void handle_param_insert(std::string id, std::string type,std::string line) {

		if(!symbol_table.Insert(id, type)) {
			writeIntoErrorFile("Error at line "+line+": Multiple declaration of "+id+" in parameter\n");
			writeIntoparserLogFile("Error at line "+line+": Multiple declaration of "+id+" in parameter\n");
			syntaxErrorCount++;
		}

	}

	

	

	std::vector<std::pair<std::string,std::string>> buffer_param_list;

	void handleInsertParamList() {

		for(const auto& param : buffer_param_list) {
			if (!symbol_table.Insert(param.second, param.first)) {
				writeIntoErrorFile("Error: Parameter '" + param.second + "' already declared.");
				syntaxErrorCount++;
			}
		}
		buffer_param_list.clear();

	}


	std::vector<std::pair<std::string, std::string>> splitIntoPairs(const std::vector<std::string>& input) {
		std::vector<std::pair<std::string, std::string>> output;

		for (const std::string& str : input) {
			std::istringstream iss(str);
			std::string first, second;
			iss >> first >> second;
			if (!first.empty() && !second.empty()) {
				output.emplace_back(first, second);
			}
		}

		return output;
	}


	int scope = 0;
	void handleInsertFunction(FunctionInfo* func,std::string id,std::string type,std::string line) {
		SymbolInfo *symbol_info = symbol_table.LookUp(id);
		if(symbol_info == nullptr)
		{
			cout<<"--------------insert--------funct------"<<id<<"--return--"<<func->returnType<<endl;
			if(!symbol_table.InsertInParent(id,type,scope,func)){ 
				cout<<"fuction not insert"<<endl;
			}
		}else{
			FunctionInfo *info = symbol_info->functionInfo;
			if(info == nullptr)
			{
				writeIntoErrorFile("Error at line "+line+": Multiple declaration of "+id+"\n");
				writeIntoparserLogFile("Error at line "+line+": Multiple declaration of "+id+"\n\n");
				syntaxErrorCount++;	

				return;		
			}

			std::cout<<"----------------------->>>"<<info->returnType<<"---match---"<<type<<endl;

			if(info->returnType != type) {
				writeIntoErrorFile("Error at line "+line+": Return type mismatch with function declaration in function "+id+"\n");
				writeIntoparserLogFile("Error at line "+line+": Return type mismatch with function declaration in function "+id+"\n");
				syntaxErrorCount++;
				return;
			}
			if(info->parameters.size() != func->parameters.size())
			{
				writeIntoErrorFile("Error at line "+line+": Total number of arguments mismatch with declaration in function var\n");
				writeIntoparserLogFile("Error at line "+line+": Total number of arguments mismatch with declaration in function var\n");
				syntaxErrorCount++;
				return;

			}

			std:: vector<std::pair<std::string,std::string>> param = info->parameters;

			for(int i = 0;i<param.size();i++)
			{
				if(param[i].first != func->parameters[i].first || param[i].second != func->parameters[i].second)
				{
						writeIntoErrorFile("Error at line "+line+": "+std::to_string(i+1)+"th argument mismatch in function func\n");
						writeIntoparserLogFile("Error at line "+line+": "+std::to_string(i+1)+"th argument mismatch in function func\n\n");
						syntaxErrorCount++;

					return;
				}
			}



			info->isDefined = true;
		}

	}

	std::string which_type(std::string a) {
		bool isfloat = false;
		for(int i =0;i<a.size();i++){
			if(a[i]=='.') 
			{
				isfloat = true;
				break;
			}
		}

		if(isfloat) {
			return "float";
		}else{
			
			bool isNumber = true;
			for(int i =0;i<a.size();i++) {
				if(a[i] < '0' || a[i] > '9') {
					isNumber = false;
					break;
				}
			}

			if(isNumber) {
				return "int";
			}else{
				return "string";

			}
		}
	}


	void mismatch_error(std::string id,std::string argument,std::string line) {
		SymbolInfo *symbol_info = symbol_table.LookUp(id);

		if(symbol_info != nullptr)
		{
			FunctionInfo* func = symbol_info->functionInfo;
			if(func == nullptr) return;


			int i =0;
			int ct= 0;
			int argument_count = 1;
			for(int j = 0;j<argument.size();j++) {
				if(argument[j] == ',') {
					argument_count++;
				}
			}

			if(argument_count != func->parameters.size())
			{
				writeIntoErrorFile("Error at line "+line+": Total number of arguments mismatch in function "+id+"\n");
				writeIntoparserLogFile("Error at line "+line+": Total number of arguments mismatch in function "+id+"\n");
				syntaxErrorCount++;
				return;

			}

			for(auto const& param:func->parameters) {

				string s="";
				for(;i<argument.size();i++) 
				{	
					if(argument[i] == ',') {
						ct++;
						i++;
						break;
					}
					s+=argument[i];

					if(i==argument.size()-1) ct++;

				}


				
				std::string type = which_type(s) ;
				cout<<"##########"<<symbol_info->getSymbolName()<<"###"<<param.first<<"---------"<<s<<"----------"<<type<<endl;
				if(type == "string") {
					return;
					SymbolInfo *symbol_info = symbol_table.LookUp(s);

					if(symbol_info != nullptr) {
						type = symbol_info->getSymbolType();
					}else{
						return;
					}
				}


				if(param.first != type)
				{
						writeIntoErrorFile("Error at line "+line+": "+std::to_string(ct)+"th argument mismatch in function "+id+ "\n");
						writeIntoparserLogFile("Error at line "+line+": "+std::to_string(ct)+"th argument mismatch in function "+id+ "\n");
						syntaxErrorCount++;
						return;
				}
			}






		}else{
			if(id == "println" || id == "printf") {
				return;
			}
			writeIntoErrorFile("Error at line "+line+": Undeclared function "+id+"\n");
			writeIntoparserLogFile("Error at line "+line+": Undeclared function "+id+"\n");
			syntaxErrorCount++;
			return;
		}
	}

	bool isFromFunction = false;
	bool fromFuncDefinition = false;


	//type mismatch
	void handle_type_mismatch(std::string id, std::string line)
	{
		std::string type = which_type(id);

		SymbolInfo* s_info = symbol_table.LookUp(id);



		if(s_info == nullptr) {
			writeIntoErrorFile("Error at line "+line+": Undeclared variable "+id+"\n");
			writeIntoparserLogFile("Error at line "+line+": Undeclared variable "+id+"\n");
			syntaxErrorCount++;
			return;
		}

		cout<<"________"<<line<<"________checking__________"<<s_info->getSymbolName()<<"---"<<s_info->getSymbolType()<<"---"<<endl;

		if(s_info->getSymbolType() == "int" || s_info->getSymbolType() == "float" )
		{
			return;
		}

		writeIntoErrorFile("Error at line "+line+": Type mismatch, "+id+" is an array\n");
		writeIntoparserLogFile("Error at line "+line+": Type mismatch, "+id+" is an array\n");
		syntaxErrorCount++;
		
		return;
	}


	//array error
	void handle_array_errors(std::string id, std::string expression, std::string line)
	{
		SymbolInfo* s_info = symbol_table.LookUp(id);

		if(s_info == nullptr) {

			return;
		}

		if(s_info->getSymbolType() == "int" || s_info->getSymbolType() == "float")
		{
			writeIntoErrorFile("Error at line "+line+": "+id+" not an array\n");
			writeIntoparserLogFile("Error at line "+line+": "+id+" not an array\n");
			syntaxErrorCount++;
			return;
		}

		std::string type =which_type(expression);

		if(type == "float") {
			writeIntoErrorFile("Error at line "+line+": Expression inside third brackets not an integer\n");
			writeIntoparserLogFile("Error at line "+line+": Expression inside third brackets not an integer\n");
			syntaxErrorCount++;
			return;

		}

	}


	//void but return
	void handle_void_function(std::string expression, std::string line) {

		std::string func ="";
		for(int i =0;i<expression.size();i++) {
			if(expression[i] == '(' ) {
				break;
			}
			func += expression[i];

			if(i == expression.size()-1) {
				return;
			}
		}

		SymbolInfo* s_info = symbol_table.LookUp(func);

		if(s_info == nullptr) {
			return;
		}

		if(s_info->functionInfo->returnType == "void") {
			writeIntoErrorFile("Error at line "+line+": Void function used in expression\n");
			writeIntoparserLogFile("Error at line "+line+": Void function used in expression\n");
			syntaxErrorCount++;
			return;
		}
	}


	//asign mismatch
	void handle_assign_mismatch(std::string variable, std::string expression, std::string line) {

		for(int i =0;i<expression.size();i++) {
			if(expression[i] == '%') {
				return;
			}
		}
		

		std::string var = "";
		for(int i =0;i<variable.size();i++) {
			if(variable[i] == '[') {
				var = variable.substr(0,i);
				break;
			}
			var += variable[i];

			if(i == variable.size()-1) {
				var = variable;
			}
		}

		SymbolInfo* s_info = symbol_table.LookUp(var);

		if(s_info == nullptr)
			return;

		std::string type = which_type(expression);

		if(type == "string" || type == "int") 
		{
			return;
		}

		if(s_info->getSymbolType() != type) {
			writeIntoErrorFile("Error at line "+line+": Type Mismatch\n");
			writeIntoparserLogFile("Error at line "+line+": Type Mismatch\n");
			syntaxErrorCount++;
			return;
		}

		    




	}



	int curl = 0;

	std::string last_function_name = "";
}


start : program
	{
		writeIntoparserLogFile("Line " + std::to_string($program.stop->getLine()) + ": start : program\n");
		symbol_table.PrintAllScopeTable();

		writeIntoparserLogFile("Total lines: "+std::to_string($program.stop->getLine())+"\n");
		writeIntoparserLogFile("Total errors: "+std::to_string(syntaxErrorCount)+"\n");
	}
	;

program returns [std::string name_line]
    :   p=program u=unit {
			$name_line = $p.name_line + "\n" + $u.name_line;
            writeIntoparserLogFile("Line " + std::to_string($u.stop->getLine()) + ": program : program unit\n");
            writeIntoparserLogFile($name_line+"\n\n");
            
        }
    |   u=unit {
			$name_line = $u.name_line;
            writeIntoparserLogFile("Line " + std::to_string($u.stop->getLine()) + ": program : unit\n");
            writeIntoparserLogFile($name_line+"\n\n");
            
        }
    ;

	
unit returns [std::string name_line]: var_declaration 
		{
			$name_line = $var_declaration.name_line;
			writeIntoparserLogFile("Line " + std::to_string($var_declaration.start->getLine()) + ": unit : var_declaration\n");
			writeIntoparserLogFile($name_line + "\n\n");
			
      }
 		
     | f=func_declaration
		{
			$name_line = $f.name_line;
			writeIntoparserLogFile("Line " + std::to_string($f.start->getLine()) + ": unit : func_declaration\n");
			writeIntoparserLogFile($name_line + "\n\n");			
		}

     | fc=func_definition
		{
			$name_line = $fc.name_line;
			writeIntoparserLogFile("Line " + $fc.line_number + ": unit : func_definition\n");
			writeIntoparserLogFile($name_line + "\n\n");			
		}
     ;
     
func_declaration returns [std::string name_line]

		:ts=type_specifier ID LPAREN {symbol_table.EnterScope();scope++;} p=parameter_list RPAREN smn=SEMICOLON{ buffer_param_list.clear(); }
			{
				$name_line = $ts.name_line + " "+ $ID->getText() + $LPAREN->getText()+ $p.param_list.get_list_as_string()+ $RPAREN->getText() + $smn->getText(); 
				writeIntoparserLogFile("Line "+std:: to_string($smn->getLine()) + ": func_declaration : type_specifier ID LPAREN parameter_list RPAREN SEMICOLON\n");
				writeIntoparserLogFile($name_line + "\n\n");

				FunctionInfo*  func_info = new FunctionInfo(
					$ts.name_line,
					splitIntoPairs($p.param_list.get_variables()),
					false
				);	

				handleInsertFunction(func_info,$ID->getText(),$ts.name_line,std::to_string($ID->getLine()));


			}
		| t=type_specifier ID LPAREN{symbol_table.EnterScope();scope++;} RPAREN sm=SEMICOLON { buffer_param_list.clear(); }
			{
							
				$name_line = $t.name_line + " "+ $ID->getText() + $LPAREN->getText() + $RPAREN->getText() + $sm->getText(); 
				writeIntoparserLogFile("Line "+std:: to_string($sm->getLine()) + ": func_declaration : type_specifier ID LPAREN RPAREN SEMICOLON\n");
				writeIntoparserLogFile($name_line + "\n\n");

				FunctionInfo*  func_info = new FunctionInfo(
					$t.name_line,
					{},
					false
				);	

				handleInsertFunction(func_info,$ID->getText(),$t.name_line,std::to_string($ID->getLine()));			
			}
		;
		 
func_definition returns[std::string name_line, std::string line_number]
		: t=type_specifier ID LPAREN {symbol_table.EnterScope();scope++;fromFuncDefinition=true;} p=parameter_list RPAREN
		{
			fromFuncDefinition = false;
				FunctionInfo*  func_info = new FunctionInfo(
					$t.name_line,
					splitIntoPairs($p.param_list.get_variables()),
					true
				);	

				handleInsertFunction(func_info,$ID->getText(),$t.name_line,std::to_string($ID->getLine()));

				isFromFunction = true;	
				curl = 0;	

				last_function_name = $ID->getText();	
		} c=compound_statement 
			{

				isFromFunction = false;
				writeIntoparserLogFile("Line "+$c.line_number + ": func_definition : type_specifier ID LPAREN parameter_list RPAREN compound_statement\n");
				$name_line =$t.name_line +" "+$ID->getText()+$LPAREN->getText()+$p.param_list.get_list_as_string()+$RPAREN->getText()+$c.name_line;
				$line_number = $c.line_number;
				writeIntoparserLogFile($name_line + "\n");	
						

			}
		| ts=type_specifier ID LPAREN{symbol_table.EnterScope();scope++;} RPAREN
		{
				FunctionInfo*  func_info = new FunctionInfo(
					$ts.name_line,
					{},
					true
				);	

				handleInsertFunction(func_info,$ID->getText(),$ts.name_line,std::to_string($ID->getLine()));	

				isFromFunction = true;
		} compound_statement
			{

				isFromFunction = false;
				writeIntoparserLogFile("Line "+$compound_statement.line_number + ": func_definition : type_specifier ID LPAREN RPAREN compound_statement\n");
				$name_line = $type_specifier.name_line +" "+$ID->getText()+$LPAREN->getText()+$RPAREN->getText()+$compound_statement.name_line;
				$line_number = $compound_statement.line_number;
				writeIntoparserLogFile($name_line + "\n");

		
			}

 		;				


parameter_list  returns [str_list param_list]
		: p=parameter_list COMMA t=type_specifier ID
		{

			if(fromFuncDefinition) {	
				handle_param_insert($ID->getText(),$t.name_line,std:: to_string($ID->getLine()));
			}

				$param_list.set_variables($p.param_list.get_variables());
				$param_list.add($t.name_line +" "+ $ID->getText());

				
				buffer_param_list.push_back({$t.name_line , $ID->getText()});

				
				writeIntoparserLogFile("Line "+std:: to_string($ID->getLine()) + ": parameter_list : parameter_list COMMA type_specifier ID\n");
				writeIntoparserLogFile($param_list.get_list_as_string() +"\n");

				
		}
		| parameter_list COMMA type_specifier
 		| t=type_specifier ID
		{
			if(fromFuncDefinition) { 
				handle_param_insert($ID->getText(),$t.name_line,std:: to_string($ID->getLine()));
			}

			$param_list.add($t.name_line +" "+$ID->getText());
			writeIntoparserLogFile("Line "+std:: to_string($ID->getLine()) + ": parameter_list : type_specifier ID\n");
			writeIntoparserLogFile($param_list.get_list_as_string() +"\n");
			
			buffer_param_list.push_back({$t.name_line , $ID->getText()});


			
		}

		| type_specifier ADDOP
		{
			$param_list.add($type_specifier.name_line );
			cout<<"------------------->>>>>>type_specifier ADDOP"<<endl;
			writeIntoparserLogFile("Line "+std:: to_string($type_specifier.start->getLine()) + ": parameter_list : type_specifier\n");
			writeIntoparserLogFile($type_specifier.name_line +"\n");

			writeIntoErrorFile("Error at line "+std::to_string($type_specifier.stop->getLine())+": syntax error\n");
			writeIntoparserLogFile("Error at line "+std::to_string($type_specifier.stop->getLine())+": syntax error\n");
			writeIntoparserLogFile($param_list.get_list_as_string()+"\n");
			writeIntoparserLogFile($param_list.get_list_as_string());

			writeIntoErrorFile("Error at line "+std::to_string($type_specifier.stop->getLine())+": 1th parameter's name not given in function definition of var\n");
			writeIntoparserLogFile("Error at line "+std::to_string($type_specifier.stop->getLine())+": 1th parameter's name not given in function definition of var\n");

			handleInsertFunction(
				new FunctionInfo(
					type,
					{},
					true
				),
				last_function_name,
				type,
				std::to_string($type_specifier.stop->getLine())
			);

			syntaxErrorCount++;
		} 
 		;





 		
compound_statement returns [std::string name_line, std::string line_number]
    : LCURL {
		curl += 1;
		if(!isFromFunction || curl != 1)
		{
			symbol_table.EnterScope(); 
			scope++;
		}


		
		
		 } f=statements RCURL
        {
            writeIntoparserLogFile("Line "+std::to_string($RCURL->getLine()) + ": compound_statement : LCURL statements RCURL\n");
            $name_line = $LCURL->getText() + "\n" + $f.name_line + "\n" + $RCURL->getText() + "\n";
            $line_number = std::to_string($RCURL->getLine());
            
            
            writeIntoparserLogFile($name_line + "\n\n\n");

			symbol_table.PrintAllScopeTable();
            symbol_table.ExitScope();
			scope--;
        }

 		    | LCURL RCURL
			{	
				curl += 1;
				if(!isFromFunction || curl != 1)
				{
					symbol_table.EnterScope(); 
					scope++;
				}

				writeIntoparserLogFile("Line "+std::to_string($RCURL->getLine()) + ": compound_statement : LCURL RCURL\n");
				$name_line = $LCURL->getText()  + $RCURL->getText() + "\n";
				$line_number = std::to_string($RCURL->getLine());
				writeIntoparserLogFile($name_line + "\n\n\n");

				symbol_table.PrintAllScopeTable();
				symbol_table.ExitScope();
				scope--;
			}
 		    ;
 		    
var_declaration returns [std::string name_line]
    : t=type_specifier dl=declaration_list sm=SEMICOLON  {




		writeIntoparserLogFile(
			"Line "+std::to_string($sm->getLine()) +": var_declaration : type_specifier declaration_list SEMICOLON\n"
			);

		if($t.name_line == "void")
		{ 
				writeIntoErrorFile("Error at line "+std::to_string($sm->getLine())+": Variable type cannot be void\n");
				writeIntoparserLogFile("Error at line "+std::to_string($sm->getLine())+": Variable type cannot be void\n");
				syntaxErrorCount++;	
		}


		writeIntoparserLogFile($t.name_line +" "+ $dl.var_list.get_list_as_string()+$sm->getText()+"\n");
		$name_line = $t.name_line +" "+ $dl.var_list.get_list_as_string()+$sm->getText();

		type.clear();




		



      }

    | t=type_specifier de=declaration_list_err sm=SEMICOLON {
        writeIntoErrorFile(
            std::string("Line# ") + std::to_string($sm->getLine()) +
            " with error name: " + $de.error_name +
            " - Syntax error at declaration list of variable declaration"
        );

        syntaxErrorCount++;
		
      }
    ;

declaration_list_err returns [std::string error_name]: {
        $error_name = "Error in declaration list";
    };

 		 
type_specifier returns [std::string name_line]	
        : INT {
            $name_line = $INT->getText();
			writeIntoparserLogFile("Line " + std::to_string($INT->getLine()) + ": type_specifier : " + "INT\n");
			
			writeIntoparserLogFile($INT->getText()+"\n");

			type = "int";

        }
 		| FLOAT {
            $name_line = $FLOAT->getText();

			writeIntoparserLogFile("Line " + std::to_string($FLOAT->getLine()) + ": type_specifier : " + "FLOAT\n");
			
			writeIntoparserLogFile($FLOAT->getText()+"\n");

			type = "float";
        }
 		| VOID {
            $name_line = $VOID->getText();

			writeIntoparserLogFile("Line " + std::to_string($VOID->getLine()) + ": type_specifier : " + "VOID\n");
			
			writeIntoparserLogFile($VOID->getText()+"\n");

			type = "void";
        }
 		;
 		
declaration_list returns [str_list var_list] : dl=declaration_list COMMA ID 
			{
				handle_insert($ID->getText(), type,std::to_string($ID->getLine()));


				$var_list.set_variables($dl.var_list.get_variables());
				$var_list.add($ID->getText());

				writeIntoparserLogFile("Line "+std::to_string($ID->getLine())+": declaration_list : declaration_list COMMA ID\n");
				writeIntoparserLogFile($var_list.get_list_as_string()+"\n");


			}
 		  | dll=declaration_list COMMA ID LTHIRD CONST_INT RTHIRD
			{
				handle_insert($ID->getText(), type +" "+$CONST_INT->getText(),std::to_string($ID->getLine()));

				writeIntoparserLogFile("Line "+std::to_string($ID->getLine())+": declaration_list : declaration_list COMMA ID LTHIRD CONST_INT RTHIRD\n");

				$var_list.set_variables($dll.var_list.get_variables());
				$var_list.add($ID->getText() + $LTHIRD->getText() + $CONST_INT->getText() + $RTHIRD->getText());
				writeIntoparserLogFile($var_list.get_list_as_string()+"\n");

			}
		  
		  |ID ADDOP variable
		  	{
				cout<<"------------------->>>>>>foundd---------------------------------------"<<endl;

				$var_list.add($ID->getText());

				removeLinesFromLogFile();

				writeIntoparserLogFile("Line "+std::to_string($ID->getLine())+": declaration_list : ID\n");
				writeIntoparserLogFile($ID->getText()+"\n");


				handle_insert($ID->getText(), type,std::to_string($ID->getLine()));

				writeIntoErrorFile("Error at line "+std::to_string($ID->getLine())+": syntax error\n");
				writeIntoparserLogFile("Error at line "+std::to_string($ID->getLine())+": syntax error\n");
				writeIntoparserLogFile($ID->getText()+"\n");
				writeIntoparserLogFile($ID->getText()+"\n");
				writeIntoparserLogFile($ID->getText()+"\n");

				syntaxErrorCount++;
			}
		  
		  
		  |ID 
		  	{
				handle_insert($ID->getText(), type,std::to_string($ID->getLine()));

				writeIntoparserLogFile("Line "+std::to_string($ID->getLine())+": declaration_list : "+ "ID\n");


				$var_list.add($ID->getText());

				writeIntoparserLogFile($ID->getText()+"\n");



			}
 		  | ID LTHIRD CONST_INT RTHIRD
		  {
				handle_insert($ID->getText(), type +" "+$CONST_INT->getText(),std::to_string($ID->getLine()));

				writeIntoparserLogFile("Line "+std::to_string($ID->getLine())+": declaration_list : ID LTHIRD CONST_INT RTHIRD\n");
				$var_list.add($ID->getText() + $LTHIRD->getText() + $CONST_INT->getText() + $RTHIRD->getText());
				writeIntoparserLogFile($var_list.get_list_as_string()+"\n");
		  }
		  |SUBOP
		  {
				writeIntoErrorFile("Error at line "+std::to_string($SUBOP->getLine())+": syntax error in declaration list\n");
				writeIntoparserLogFile("Line "+std::to_string($SUBOP->getLine())+": declaration_list : declaration_list SUBOP declaration_list\n");
				writeIntoparserLogFile("Error at line "+std::to_string($SUBOP->getLine())+": syntax error in declaration list\n");
				syntaxErrorCount++;
				
		}
 		  ;
 		  
statements returns [std:: string name_line]
		: f=statement
		{ 
			writeIntoparserLogFile("Line "+std::to_string($f.start->getLine()) + ": statements : statement\n");
			$name_line =$f.name_line;
			
			writeIntoparserLogFile($name_line + "\n\n");			
		}
	   | s=statements statement

	   {
			writeIntoparserLogFile("Line "+std::to_string($statement.stop->getLine()) + ": statements : statements statement\n");
			$name_line = $s.name_line + "\n" + $statement.name_line;
			writeIntoparserLogFile($name_line + "\n\n");
	   }
	   ;
	   
statement returns [std::string name_line]
		: var_declaration
		{
			writeIntoparserLogFile("Line "+std::to_string($var_declaration.start->getLine()) + ": statement : var_declaration\n");
			$name_line = $var_declaration.name_line;
			writeIntoparserLogFile($name_line + "\n\n");
		}
	  | expression_statement
		{
			writeIntoparserLogFile("Line "+std::to_string($expression_statement.start->getLine()) + ": statement : expression_statement\n");
			$name_line = $expression_statement.name_line;
			
			writeIntoparserLogFile($name_line + "\n\n");
		}
	  | compound_statement

	  {
			writeIntoparserLogFile("Line "+$compound_statement.line_number + ": statement : compound_statement\n");
			$name_line = $compound_statement.name_line;
			writeIntoparserLogFile($name_line + "\n");
	  }
	  | FOR LPAREN ex1=expression_statement ex2=expression_statement ex=expression RPAREN sts=statement
	  {
			writeIntoparserLogFile("Line "+std::to_string($sts.stop->getLine()) + ": statement : FOR LPAREN expression_statement expression_statement expression RPAREN statement\n");
			$name_line = $FOR->getText() + " " + $LPAREN->getText() + $ex1.name_line + $ex2.name_line + $ex.name_line + $RPAREN->getText() + $statement.name_line;
			writeIntoparserLogFile($name_line + "\n\n");
	  }
	  | IF LPAREN expression RPAREN smmm=statement
	  {
			writeIntoparserLogFile("Line "+std::to_string($smmm.stop->getLine()) + ": statement : IF LPAREN expression RPAREN statement\n");
			$name_line = $IF->getText()+" " + $LPAREN->getText() + $expression.name_line + $RPAREN->getText() + $statement.name_line;
			writeIntoparserLogFile($name_line + "\n\n");
	  }
	  | IF LPAREN expression RPAREN Hs=statement ELSE stat=statement
	  { 
			writeIntoparserLogFile("Line "+std::to_string($stat.stop->getLine()) + ": statement : IF LPAREN expression RPAREN statement ELSE statement\n");
			$name_line = $IF->getText()+" " + $LPAREN->getText() + $expression.name_line + $RPAREN->getText() + $Hs.name_line + $ELSE->getText()+"\n" + $stat.name_line;
			writeIntoparserLogFile($name_line + "\n\n");
	  }
	  | WHILE LPAREN expression RPAREN state=statement
	  { 
			writeIntoparserLogFile("Line "+std::to_string($state.stop->getLine()) + ": statement : WHILE LPAREN expression RPAREN statement\n");
			$name_line = $WHILE->getText()+" " + $LPAREN->getText() + $expression.name_line + $RPAREN->getText() + $statement.name_line;
			writeIntoparserLogFile($name_line + "\n\n");
	  }
	  | PRINTLN LPAREN ID RPAREN s=SEMICOLON 
		{ 
			writeIntoparserLogFile("Line "+std::to_string($ID->getLine()) + ": statement : PRINTLN LPAREN ID RPAREN SEMICOLON\n");
			$name_line = $PRINTLN->getText()+$LPAREN->getText()+$ID->getText()+$RPAREN->getText()+$s->getText();
			handle_type_mismatch($ID->getText(),std::to_string($ID->getLine()));
			writeIntoparserLogFile($name_line + "\n\n");
		}

	  | RETURN f=expression sm=SEMICOLON
	  { 
			writeIntoparserLogFile("Line "+std::to_string($f.start->getLine()) + ": statement : RETURN expression SEMICOLON\n");
			$name_line =$RETURN->getText()+" " +$f.name_line+$sm->getText();
			
			writeIntoparserLogFile($name_line + "\n\n");		
	  }
	  ;
	  
expression_statement returns [std::string name_line]
		 	: SEMICOLON	
			{
				writeIntoparserLogFile("Line "+std::to_string($SEMICOLON->getLine()) + ": expression_statement : SEMICOLON\n");
				$name_line = $SEMICOLON->getText();
				
				writeIntoparserLogFile($name_line + "\n");
			}		
			| expression SEMICOLON 
			{ 
				if($SEMICOLON->getText() != ";") {
					
					syntaxErrorCount++;
				}else{
									writeIntoparserLogFile("Line "+std::to_string($SEMICOLON->getLine()) + ": expression_statement : expression SEMICOLON\n");
				$name_line = $expression.name_line + $SEMICOLON->getText();
				
				writeIntoparserLogFile($name_line + "\n");
				}

			}		
			;
	  
variable returns [ std::string name_line ]
	: ID 
	{ 
		writeIntoparserLogFile("Line "+std::to_string($ID->getLine()) + ": variable : ID\n");

		handle_type_mismatch($ID->getText(),std::to_string($ID->getLine()));

		$name_line = $ID->getText();
		writeIntoparserLogFile($name_line +"\n");

	}		
	 | ID LTHIRD expression RTHIRD 
	 {
		writeIntoparserLogFile("Line "+std::to_string($ID->getLine()) + ": variable : ID LTHIRD expression RTHIRD\n");

		handle_array_errors($ID->getText(),$expression.name_line,std::to_string($RTHIRD->getLine()));

		$name_line = $ID->getText() + $LTHIRD->getText() + $expression.name_line + $RTHIRD->getText();
		writeIntoparserLogFile($name_line +"\n");
	 }
	 ;
	 
 expression returns [std::string name_line]
 		: f=logic_expression	
		{ 
			writeIntoparserLogFile("Line "+std::to_string($f.start->getLine()) + ": expression : logic expression\n");
			$name_line = $f.name_line;
			
			writeIntoparserLogFile($name_line + "\n");
		}
	   | variable ASSIGNOP simple_expression ADDOP ASSIGNOP 
	   {
			writeIntoparserLogFile("Error at line "+std::to_string($ASSIGNOP->getLine()) + ": syntax error\n");
			writeIntoErrorFile("Error at line "+std::to_string($ASSIGNOP->getLine()) + ": syntax error\n");
			syntaxErrorCount++;
			$name_line = "int x,z;\n";
	   }
	   
	   | v=variable ASSIGNOP l=logic_expression
		{ 
			writeIntoparserLogFile("Line "+std::to_string($ASSIGNOP->getLine()) + ": expression : variable ASSIGNOP logic_expression\n");
			$name_line = $v.name_line+$ASSIGNOP->getText()+$l.name_line;
			
			handle_void_function($l.name_line, std::to_string($ASSIGNOP->getLine()));
			handle_assign_mismatch($v.name_line, $l.name_line, std::to_string($ASSIGNOP->getLine()));

			writeIntoparserLogFile($name_line + "\n");
		} 	
	   ;
			
logic_expression returns [std::string name_line ]
		: f=rel_expression 	
		{ 
			writeIntoparserLogFile("Line "+std::to_string($f.start->getLine()) + ": logic_expression : rel_expression\n");
			$name_line = $f.name_line;
			
			writeIntoparserLogFile($name_line + "\n");			
		}
		 | r=rel_expression LOGICOP rel_expression 
		 {
			writeIntoparserLogFile("Line "+std::to_string($LOGICOP->getLine()) + ": logic_expression : rel_expression LOGICOP rel_expression\n");
			$name_line = $r.name_line + $LOGICOP->getText() + $rel_expression.name_line;
			writeIntoparserLogFile($name_line + "\n");
		 }	
		 ;
			
rel_expression returns [std::string name_line]
		: f=simple_expression 
		{ 
			writeIntoparserLogFile("Line "+std::to_string($f.start->getLine()) + ": rel_expression : simple_expression\n");
			$name_line = $f.name_line;
			
			writeIntoparserLogFile($name_line + "\n");			
		}
		| s=simple_expression RELOP simple_expression	
		{
			writeIntoparserLogFile("Line "+std::to_string($RELOP->getLine()) + ": rel_expression : simple_expression RELOP simple_expression\n");
			$name_line = $s.name_line + $RELOP->getText() + $simple_expression.name_line;
			
			writeIntoparserLogFile($name_line + "\n");
		}
		;
				
simple_expression returns [std::string name_line]
		: f=term 
		{ 
			writeIntoparserLogFile("Line "+std::to_string($f.stop->getLine()) + ": simple_expression : term\n");
			$name_line = $f.name_line;
			
			writeIntoparserLogFile($name_line + "\n");
		}

		
		  | s=simple_expression ADDOP t=term 

		  { 
			writeIntoparserLogFile("Line "+std::to_string($s.start->getLine()) + ": simple_expression : simple_expression ADDOP term\n");
			$name_line = $s.name_line+ $ADDOP->getText()+$t.name_line;
			
			writeIntoparserLogFile($name_line + "\n");
		  }
		  ;
					
term returns [ std:: string name_line ]
	:	f=unary_expression
	{
			writeIntoparserLogFile("Line "+std::to_string($f.start->getLine()) + ": term : unary_expression\n");
			$name_line = $f.name_line;
			
			writeIntoparserLogFile($name_line + "\n");
	}
     |  t=term MULOP u=unary_expression
	 { 
			writeIntoparserLogFile("Line "+std::to_string($t.start->getLine()) + ": term : term MULOP unary_expression\n");

			//modulo error
			if($MULOP->getText() == "%" ) {
				if($u.name_line.size() ==1 && $u.name_line[0] == '0'){
					writeIntoErrorFile("Error at line "+std::to_string($MULOP->getLine())+": Modulus by Zero\n");
					writeIntoparserLogFile("Error at line "+std::to_string($MULOP->getLine())+": Modulus by Zero\n");
					syntaxErrorCount++;
				}else if(which_type($u.name_line) != "int") {
					writeIntoErrorFile("Error at line "+std::to_string($MULOP->getLine())+": Non-Integer operand on modulus operator\n");
					writeIntoparserLogFile("Error at line "+std::to_string($MULOP->getLine())+": Non-Integer operand on modulus operator\n");
					syntaxErrorCount++;
				}else{
					handle_void_function($unary_expression.name_line, std::to_string($MULOP->getLine()));
				}

				
			}else{
				handle_void_function($unary_expression.name_line, std::to_string($MULOP->getLine()));
			}	

			

			$name_line = $t.name_line + $MULOP->getText() + $unary_expression.name_line;
			writeIntoparserLogFile($name_line + "\n");
	 }
     ;

unary_expression returns [ std::string name_line ]
		 : ADDOP unary_expression  
		 {
			writeIntoparserLogFile("Line "+std::to_string($ADDOP->getLine()) + ": unary_expression : ADDOP unary_expression\n");
			$name_line = $ADDOP->getText() + $unary_expression.name_line;
			writeIntoparserLogFile($name_line + "\n");
		 }
		 | NOT unary_expression 
		 {
			writeIntoparserLogFile("Line "+std::to_string($NOT->getLine()) + ": unary_expression : NOT unary_expression\n");
			$name_line = $NOT->getText() + $unary_expression.name_line;
			writeIntoparserLogFile($name_line + "\n");
		 }
		 | f=factor 
		 { 
			writeIntoparserLogFile("Line "+std::to_string($f.start->getLine()) + ": unary_expression : factor\n");
			$name_line = $f.name_line;
			
			writeIntoparserLogFile($name_line + "\n");
		 }
		 ;
	
factor	returns [ std::string name_line ] 
	: v=variable  
	{ 
		writeIntoparserLogFile("Line "+std::to_string($v.start->getLine()) + ": factor : variable\n");
		$name_line = $v.name_line;
		
		writeIntoparserLogFile($name_line + "\n");
	}
	| ID LPAREN a=argument_list RPAREN
	{ 
		writeIntoparserLogFile("Line "+std::to_string($ID->getLine()) + ": factor : ID LPAREN argument_list RPAREN\n");

		mismatch_error($ID->getText(),$a.name_line,std::to_string($RPAREN->getLine()));

		$name_line = $ID->getText() + $LPAREN->getText() + $argument_list.name_line + $RPAREN->getText();
		
		writeIntoparserLogFile($name_line + "\n");
	}
	| LPAREN expression RPAREN
	{ 
		writeIntoparserLogFile("Line "+std::to_string($LPAREN->getLine()) + ": factor : LPAREN expression RPAREN\n");
		$name_line = $LPAREN->getText() + $expression.name_line + $RPAREN->getText();
		
		writeIntoparserLogFile($name_line + "\n");
	}
	| CONST_INT 
	{ 
		writeIntoparserLogFile("Line "+std::to_string($CONST_INT->getLine()) + ": factor : CONST_INT\n");
		$name_line = $CONST_INT->getText();
		
		writeIntoparserLogFile($name_line + "\n");
	}
	| CONST_FLOAT
	{
		writeIntoparserLogFile("Line "+std::to_string($CONST_FLOAT->getLine()) + ": factor : CONST_FLOAT\n");
		$name_line = $CONST_FLOAT->getText()+"0";
		
		writeIntoparserLogFile($name_line + "\n");
	}
	| variable INCOP 
	{
		writeIntoparserLogFile("Line "+std::to_string($INCOP->getLine()) + ": factor : variable INCOP\n");
		$name_line = $variable.name_line + $INCOP->getText();
		
		writeIntoparserLogFile($name_line + "\n"); 
	}
	| variable DECOP
	{
		writeIntoparserLogFile("Line "+std::to_string($DECOP->getLine()) + ": factor : variable DECOP\n");
		$name_line = $variable.name_line + $DECOP->getText();
		
		writeIntoparserLogFile($name_line + "\n");
	}
	;
	
argument_list returns [std::string name_line]
			: arguments
			{
				writeIntoparserLogFile("Line "+std::to_string($arguments.start->getLine()) + ": argument_list : arguments\n");
				$name_line = $arguments.arg_list.get_list_as_string();
				writeIntoparserLogFile($name_line + "\n");	
			}
			  |
			  ;
	
arguments returns [str_list arg_list]
			: a=arguments COMMA logic_expression
			{ 
				$arg_list.set_variables($a.arg_list.get_variables());
				$arg_list.add($logic_expression.name_line);
				writeIntoparserLogFile("Line "+std::to_string($logic_expression.start->getLine()) + ": arguments : arguments COMMA logic_expression\n");
				writeIntoparserLogFile($arg_list.get_list_as_string() + "\n");
			}
				
	      | logic_expression
		  {
			writeIntoparserLogFile("Line "+std::to_string($logic_expression.start->getLine()) + ": arguments : logic_expression\n");
			$arg_list.add($logic_expression.name_line);
			writeIntoparserLogFile($arg_list.get_list_as_string() + "\n");
		  }
	      ;
