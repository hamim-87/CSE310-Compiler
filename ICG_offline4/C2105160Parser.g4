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
	using namespace std;

    extern std::ofstream parserLogFile;
    extern std::ofstream errorFile;

    extern int syntaxErrorCount;
	extern SymbolTable symbol_table;
}

@parser::members {
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

	string type = "";
	int stack_offset = 0;


	int label_counter = 0;
    
    string getNewLabel() {
        return "L" + to_string(label_counter++);
    }

	bool is_code = false;
	bool is_main = false;
	string f_name ="";




}


start : {
    writeIntoparserLogFile(".MODEL SMALL");
    writeIntoparserLogFile(".STACK 100H");
    writeIntoparserLogFile(".DATA");
    writeIntoparserLogFile("\tnumber DB 6 DUP(?)");  

}

program
{

    writeIntoparserLogFile("");
    writeIntoparserLogFile("new_line proc");
    writeIntoparserLogFile("\tpush ax");
    writeIntoparserLogFile("\tpush dx");
    writeIntoparserLogFile("\tmov ah,2");
    writeIntoparserLogFile("\tmov dl,0Dh");
    writeIntoparserLogFile("\tint 21h");
    writeIntoparserLogFile("\tmov ah,2");
    writeIntoparserLogFile("\tmov dl,0Ah");
    writeIntoparserLogFile("\tint 21h");
    writeIntoparserLogFile("\tpop dx");
    writeIntoparserLogFile("\tpop ax");
    writeIntoparserLogFile("\tret");
    writeIntoparserLogFile("new_line endp");
    writeIntoparserLogFile("");
    writeIntoparserLogFile("print_output proc");
    writeIntoparserLogFile("\tpush ax");
    writeIntoparserLogFile("\tpush bx");
    writeIntoparserLogFile("\tpush cx");
    writeIntoparserLogFile("\tpush dx");
    writeIntoparserLogFile("\tpush si");
    writeIntoparserLogFile("\tlea si,number");
    writeIntoparserLogFile("\tmov bx,10");
    writeIntoparserLogFile("\tadd si,4");
    writeIntoparserLogFile("\tmov byte ptr [si], '$'");  
    writeIntoparserLogFile("\tdec si");
    writeIntoparserLogFile("\tcmp ax,0");
    writeIntoparserLogFile("\tjl negate");              
    writeIntoparserLogFile("print:");
    writeIntoparserLogFile("\txor dx,dx");
    writeIntoparserLogFile("\tdiv bx");
    writeIntoparserLogFile("\tadd dl,'0'");             
    writeIntoparserLogFile("\tmov [si],dl");            
    writeIntoparserLogFile("\tdec si");
    writeIntoparserLogFile("\tcmp ax,0");
    writeIntoparserLogFile("\tjne print");
    writeIntoparserLogFile("\tinc si");
    writeIntoparserLogFile("\tlea dx,si");
    writeIntoparserLogFile("\tmov ah,9");
    writeIntoparserLogFile("\tint 21h");
    writeIntoparserLogFile("\tpop si");
    writeIntoparserLogFile("\tpop dx");
    writeIntoparserLogFile("\tpop cx");
    writeIntoparserLogFile("\tpop bx");
    writeIntoparserLogFile("\tpop ax");
    writeIntoparserLogFile("\tret");
    writeIntoparserLogFile("negate:");
    writeIntoparserLogFile("\tpush ax");
    writeIntoparserLogFile("\tmov ah,2");
    writeIntoparserLogFile("\tmov dl,'-'");
    writeIntoparserLogFile("\tint 21h");
    writeIntoparserLogFile("\tpop ax");
    writeIntoparserLogFile("\tneg ax");
    writeIntoparserLogFile("\tjmp print");
    writeIntoparserLogFile("print_output endp");
    writeIntoparserLogFile("END main");



}
	;

program : program unit 
	| unit
	;
	
unit : var_declaration
     | func_declaration
     | func_definition
     ;
     
func_declaration : type_specifier ID LPAREN parameter_list RPAREN SEMICOLON
		| type_specifier ID LPAREN RPAREN SEMICOLON
		;
		 
func_definition : type_specifier { stack_offset = 0; } ID {

		
        string code = "";
				f_name=$ID->getText();
        if(!is_code) {
            writeIntoparserLogFile(".CODE");
            is_code = true;
        }

        if($ID->getText() == "main") {
			is_main = true;
            code = "main PROC";
            writeIntoparserLogFile(code);
            writeIntoparserLogFile("\tMOV AX, @DATA");
            writeIntoparserLogFile("\tMOV DS, AX");
        } else {
            code = $ID->getText() + " PROC";
            writeIntoparserLogFile(code);
            writeIntoparserLogFile("\tPUSH BP");
            writeIntoparserLogFile("\tMOV BP, SP");
        }
    } LPAREN { symbol_table.EnterScope(); } p=parameter_list
	{
		auto para = $p.param_list.get_variables();
		int ops = 4;
		for(int i = para.size()-1;i>=0;i--) {
			auto sym_info = symbol_table.LookUp(para[i]);

			sym_info->offset = ops;
			ops+=2;
		}
	}
	
	 RPAREN compound_statement {

		
        symbol_table.ExitScope();
        if($ID->getText() != "main") {
            writeIntoparserLogFile($ID->getText() +"_end:");

			if(stack_offset>0) {
				 writeIntoparserLogFile("\tADD SP, " + to_string(stack_offset));
			}
            writeIntoparserLogFile("\tPOP BP");
            writeIntoparserLogFile("\tRET");
        }
        writeIntoparserLogFile($ID->getText() + " ENDP");
        writeIntoparserLogFile("");
    }
    | type_specifier { stack_offset = 0; } ID {
				f_name=$ID->getText();
        string code = "";
        if(!is_code) {
            writeIntoparserLogFile(".CODE");
            is_code = true;
        }

        if($ID->getText() == "main") {
			is_main= true;
            code = "main PROC";
            writeIntoparserLogFile(code);
            writeIntoparserLogFile("\tMOV AX, @DATA");
            writeIntoparserLogFile("\tMOV DS, AX");
			writeIntoparserLogFile("\tPUSH BP");
			writeIntoparserLogFile("\tMOV BP, SP\n");
        } else {
            code = $ID->getText() + " PROC";
            writeIntoparserLogFile(code);
            writeIntoparserLogFile("\tPUSH BP");
            writeIntoparserLogFile("\tMOV BP, SP");
        }
    } LPAREN { symbol_table.EnterScope(); } RPAREN compound_statement {
        symbol_table.ExitScope();
        if($ID->getText() != "main") {
			if(stack_offset>0) {
				 writeIntoparserLogFile("\tADD SP, " + to_string(stack_offset));
			}
            writeIntoparserLogFile("\tPOP BP");
            writeIntoparserLogFile("\tRET");
        }
        writeIntoparserLogFile($ID->getText() + " ENDP");
        writeIntoparserLogFile("");
    }
	;			


parameter_list returns [str_list param_list]
	: p=parameter_list COMMA type_specifier ID {

        symbol_table.Insert($ID->getText(), "param");
			$param_list.set_variables($p.param_list.get_variables());
			$param_list.add($ID->getText());
    }
    | parameter_list COMMA type_specifier
    | type_specifier ID {
        symbol_table.Insert($ID->getText(), "param");
		$param_list.add($ID->getText());
    }
    | type_specifier
    ;

 		
compound_statement : LCURL statements RCURL
 		    | LCURL RCURL
 		    ;
 		    
var_declaration 
    : t=type_specifier dl=declaration_list sm=SEMICOLON {
		vector<std::string> vars = $dl.var_list.get_variables();

		for(const auto &it: vars) {
			size_t loc = it.find("[");
			string name;
			string d_type;
			string sz;
			bool is_array = false;
			if(loc == string::npos) {
				name = it;
				d_type = $t.name_line;
			}else{
				name = it.substr(0,loc);
				d_type = "arr_";
				is_array = true;
				size_t lloc = it.find("]");

				sz = it.substr(loc+1,lloc-2);
				cout<<"--------------%%%%%%%%%%%%%%%%%%%%"<<sz<<" "<<lloc<<endl;
				d_type += sz;
			}

			symbol_table.Insert(name,d_type);

			string code;

			auto sym_info = symbol_table.LookUp(name);

			if(symbol_table.getScope() == "1") {
				if(is_array) {
					code = "\t"+name + " DW " + sz + " DUP (0)";
				}else{
					code = "\t"+name + " DW 0";
				}
				sym_info->is_global = true;
			}else if(is_array) {
				int s_depth = 2*stoi(sz);
				code = "\tSUB SP, "+ to_string(s_depth) ;

				sym_info->offset = s_depth + 2;

				stack_offset += s_depth ;

			}else{
				code = "\tSUB SP, 2";
				stack_offset +=2;
				sym_info->offset = stack_offset;
			}

			writeIntoparserLogFile(code);




		}
		writeIntoparserLogFile("\n");
	}

    | t=type_specifier de=declaration_list_err sm=SEMICOLON 
    ;

declaration_list_err returns [std::string error_name]: {
        $error_name = "Error in declaration list";
    };

 		 
type_specifier 	returns [std::string name_line]
        : INT { $name_line = "int"; }
 		| FLOAT { $name_line = "float";  }
 		| VOID { $name_line = "void"; }
 		;
 		
declaration_list returns [str_list var_list] : dl=declaration_list COMMA ID 
			{


				$var_list.set_variables($dl.var_list.get_variables());
				$var_list.add($ID->getText());

				
			}
 		  | dll=declaration_list COMMA ID LTHIRD CONST_INT RTHIRD
			{

				$var_list.set_variables($dll.var_list.get_variables());
				$var_list.add($ID->getText() + $LTHIRD->getText() + $CONST_INT->getText() + $RTHIRD->getText());


			}
		  
		  |ID 
		  	{

				$var_list.add($ID->getText());





			}
 		  | ID LTHIRD CONST_INT RTHIRD
		  {

				$var_list.add($ID->getText() + $LTHIRD->getText() + $CONST_INT->getText() + $RTHIRD->getText());

		  }
		  ;
 		  
statements : statement
	   | statements statement
	   ;
	   
statement : var_declaration
	  | expression_statement
	  | compound_statement
      | FOR LPAREN expression_statement {
          	string for_start = getNewLabel();
			string for_body = getNewLabel();
			string for_update=getNewLabel();
          	string for_end = getNewLabel();

          writeIntoparserLogFile(for_start + ":");
      } expression_statement {
          writeIntoparserLogFile("\tCMP AX, 0");
          writeIntoparserLogFile("\tJE " + for_end);
		  writeIntoparserLogFile("\tJMP "+for_body);
		  writeIntoparserLogFile(for_update+":");
      } expression {
		 writeIntoparserLogFile("\t JMP "+for_start);
	  }
		
		RPAREN {


          writeIntoparserLogFile(for_body + ":");
      } statement {
          writeIntoparserLogFile("\tJMP " + for_update);
          writeIntoparserLogFile(for_end + ":");
      }
      | IF LPAREN ex=expression RPAREN  {
          string endif_label = getNewLabel();
          writeIntoparserLogFile("\tCMP AX, 0");
          writeIntoparserLogFile("\tJE " + endif_label);

      } st1=statement {writeIntoparserLogFile(endif_label + ":");}
      | IF LPAREN ex=expression RPAREN
	  {
          	string else_label = getNewLabel();
          	string endif_label = getNewLabel();
          	writeIntoparserLogFile("\tCMP AX, 0");
          	writeIntoparserLogFile("\tJE " + else_label);

	  }
	   st1=statement{writeIntoparserLogFile("\tJMP " + endif_label);} ELSE {writeIntoparserLogFile(else_label + ":");} st2=statement {



          writeIntoparserLogFile(endif_label + ":");
      }
		| WHILE {
			string while_start = getNewLabel();
			string while_end = getNewLabel();
			writeIntoparserLogFile(while_start + ":");
		} LPAREN expression RPAREN {

			writeIntoparserLogFile("\tCMP AX, 0");
			writeIntoparserLogFile("\tJE " + while_end);
		} statement {
			writeIntoparserLogFile("\tJMP " + while_start);
			writeIntoparserLogFile(while_end + ":");
		}
	  | PRINTLN LPAREN ID RPAREN SEMICOLON
	  {
          auto sym_info = symbol_table.LookUp($ID->getText());
          if(sym_info) {
              if(sym_info->is_global) {
                  writeIntoparserLogFile("\tMOV AX, " + $ID->getText());
              } else {
					if(is_main) {
                  		writeIntoparserLogFile("\tMOV AX, [BP-" + to_string(sym_info->offset) + "]");
					}else{

						if(sym_info->getSymbolType() == "param") {
                  			writeIntoparserLogFile("\tMOV AX, [BP+" + to_string(sym_info->offset) + "]");
						}else{
                  			writeIntoparserLogFile("\tMOV AX, [BP-" + to_string(sym_info->offset) + "]");
						}
					}
              }
              writeIntoparserLogFile("\tCALL print_output");
              writeIntoparserLogFile("\tCALL new_line\n");
          }	
	  }
      | RETURN expression SEMICOLON {
		if(is_main){
          writeIntoparserLogFile("\tPOP BP");
          writeIntoparserLogFile("\tMOV AX, 4CH");
          writeIntoparserLogFile("\tMOV AH, 4CH");
           writeIntoparserLogFile("\tINT 21H");
		}else{
			writeIntoparserLogFile("\tJMP "+f_name+"_end:");
		}
      }
	  ;
	  
expression_statement 	: SEMICOLON			
			| expression SEMICOLON 
			;
	  
variable returns [string name_line] : ID {
		$name_line = $ID->getText();
	}
	 | ID L=LTHIRD ex=expression R=RTHIRD 
	 {
		$name_line = $ID->getText() ;
		writeIntoparserLogFile("\tPUSH AX");
	 }
	 ;
	 
 expression : logic_expression	
	   | vr=variable ASSIGNOP logic_expression {
			string name = $vr.name_line;

			auto sym_info = symbol_table.LookUp(name);


			bool is_arr = sym_info->getSymbolType().find("arr") != string::npos;

			string code="";

			if(is_arr) {

				if(sym_info->is_global) {
					code += "\tMOV CX, AX\n";
					code += "\tPOP AX\n";
					code +="\tMOV BX, 2\n";
					code+= "\tMUL BX\n";
					code+= "\tMOV BX, AX\n";
					code+="\tMOV "+sym_info->getSymbolName()+"[BX], CX\n";
				}
				else{
					code += "\tMOV CX, AX\n";
					code += "\tPOP AX\n";

					// error
					code += "\tPOP AX\n";

					code +="\tMOV BX, 2\n";
					code+= "\tMUL BX\n";
					code+= "\tMOV BX, AX\n";
					code+="\tMOV AX, "+to_string(stack_offset)+"\n";
					code+="\tSUB AX, BX\n";
					code+="\tMOV BX, AX\n";
					code+="\tNEG BX\n";
					code +="\tMOV SI, BX\n";
					code+="\tMOV [BP+SI], CX\n";
					code += "\tXOR SI, SI\n";

				}


			}else{
				if(sym_info->is_global) {

					code="\tMOV "+ sym_info->getSymbolName() +", AX";
				}else{
					if(is_main){
						code="\tMOV [BP-"+to_string(sym_info->offset)+"], AX";
					}else{
						if(sym_info->getSymbolType() == "param")
						{
							code="\tMOV [BP+"+to_string(sym_info->offset)+"], AX";
						}
						else{
							code="\tMOV [BP-"+to_string(sym_info->offset)+"], AX";
						}

					}
				}
			}

			code += " ; "+$vr.name_line +" is assigned..\n";
				
			writeIntoparserLogFile(code);
	
			

	   }
	   ;
			
logic_expression : rel_expression 	
         | l=rel_expression{ writeIntoparserLogFile("\tPUSH AX");} LOGICOP r=rel_expression {
             string op = $LOGICOP->getText();
			
			string logic_true = getNewLabel();
			string logic_false = getNewLabel();
			string logic_end = getNewLabel();
             if(op == "&&") {
                 writeIntoparserLogFile("\tPOP BX"); // Right operand
                 writeIntoparserLogFile("\tCMP AX, 0");
                 writeIntoparserLogFile("\tJE "+logic_false);
                 writeIntoparserLogFile("\tCMP BX, 0");
                 writeIntoparserLogFile("\tJE "+logic_false);
                 writeIntoparserLogFile("\tMOV AX, 1");
                 writeIntoparserLogFile("\tJMP "+logic_false);
                 writeIntoparserLogFile(logic_false+":");
                 writeIntoparserLogFile("\tMOV AX, 0");
                 writeIntoparserLogFile(logic_end+":");
             } else if(op == "||") {
                 writeIntoparserLogFile("\tPOP BX"); // Right operand
                 writeIntoparserLogFile("\tCMP AX, 0");
                 writeIntoparserLogFile("\tJNE "+logic_true);
                 writeIntoparserLogFile("\tCMP BX, 0");
                 writeIntoparserLogFile("\tJNE "+logic_true);
                 writeIntoparserLogFile("\tMOV AX, 0");
                 writeIntoparserLogFile("\tJMP "+logic_end);
                 writeIntoparserLogFile(logic_true+":");
                 writeIntoparserLogFile("\tMOV AX, 1");
                 writeIntoparserLogFile(logic_end+":");
             }
         }
		 ;
			
rel_expression	: simple_expression 
        | l=simple_expression {writeIntoparserLogFile("\tPUSH AX");}  RELOP r=simple_expression {
            string op = $RELOP->getText();
            writeIntoparserLogFile("\tPOP BX"); 
            writeIntoparserLogFile("\tCMP BX, AX");
            string true_label = getNewLabel();
            string end_label = getNewLabel();
            
            if(op == "<") {
                writeIntoparserLogFile("\tJL " + true_label);
            } else if(op == "<=") {
                writeIntoparserLogFile("\tJLE " + true_label);
            } else if(op == ">") {
                writeIntoparserLogFile("\tJG " + true_label);
            } else if(op == ">=") {
                writeIntoparserLogFile("\tJGE " + true_label);
            } else if(op == "==") {
                writeIntoparserLogFile("\tJE " + true_label);
            } else if(op == "!=") {
                writeIntoparserLogFile("\tJNE " + true_label);
            }
            
            writeIntoparserLogFile("\tMOV AX, 0");
            writeIntoparserLogFile("\tJMP " + end_label);
            writeIntoparserLogFile(true_label + ":");
            writeIntoparserLogFile("\tMOV AX, 1");
            writeIntoparserLogFile(end_label + ":");
        }
		;
				
simple_expression : term 
		  | simple_expression{writeIntoparserLogFile("\tPUSH AX");}  ADDOP term {
				string operation = $ADDOP->getText();

				writeIntoparserLogFile("\tPOP BX");
				if(operation == "+") {
					writeIntoparserLogFile("\tADD AX, BX");
				}else{
					writeIntoparserLogFile("\tSUB BX , AX");
					writeIntoparserLogFile("\tMOV AX, BX");
				}
		  }
		  ;
					
term :	unary_expression
     |  term {writeIntoparserLogFile("\tPUSH AX");}  MULOP unary_expression {
		string op = $MULOP->getText();
		

		if(op == "*") {
			writeIntoparserLogFile("\tPOP BX");
			writeIntoparserLogFile("\tMUL BX");
	
		}else if(op == "%") {
			writeIntoparserLogFile("\tPUSH AX");
			writeIntoparserLogFile("\tPOP BX");
			writeIntoparserLogFile("\tPOP AX");
            writeIntoparserLogFile("\tMOV DX, 0");
            writeIntoparserLogFile("\tDIV BX");
            writeIntoparserLogFile("\tMOV AX, DX");
		}
	 }
     ;

unary_expression : ADDOP unary_expression  
		{
			string op = $ADDOP->getText();
			if(op == "-") {
				writeIntoparserLogFile("\tNEG AX");
			}
		}
		 | NOT unary_expression 
		 | factor 
		 ;
	
factor	: vr=variable 
	{
		auto sym_info = symbol_table.LookUp($vr.name_line);

		bool is_arr = sym_info->getSymbolType().find("arr") != string::npos;

	
		if(sym_info->is_global) {
			if(is_arr)
			{
				writeIntoparserLogFile("\tMOV BX, 2");
				writeIntoparserLogFile("\tMUL BX");
				writeIntoparserLogFile("\tMOV BX, AX");
				writeIntoparserLogFile("\tMOV AX, "+sym_info->getSymbolName()+"[BX]");
			}
			else{

				writeIntoparserLogFile("\tMOV AX, "+sym_info->getSymbolName());
			}
		}else {
			if(is_arr) {

				writeIntoparserLogFile("\tMOV BX, 2");
				writeIntoparserLogFile("\tMUL BX");
				writeIntoparserLogFile("\tMOV BX, AX");
				writeIntoparserLogFile("\tMOV AX, "+to_string(stack_offset));
				writeIntoparserLogFile("\tSUB AX, BX");
				writeIntoparserLogFile("\tMOV BX, AX");
				writeIntoparserLogFile("\tNEG BX");
				writeIntoparserLogFile("\tMOV SI, BX");
				writeIntoparserLogFile("\tMOV AX, [BP+SI]");
				writeIntoparserLogFile("\tXOR SI, SI");
				



			}else{
				if(is_main){
					writeIntoparserLogFile("\tMOV AX, [BP-" +to_string(sym_info->offset) + "]");
				}else{
					if(sym_info->getSymbolType() == "param")
					{
						writeIntoparserLogFile("\tMOV AX, [BP+" +to_string(sym_info->offset) + "]");
					}else{
						writeIntoparserLogFile("\tMOV AX, [BP-" +to_string(sym_info->offset) + "]");
					}
				}
			}

		}

	}
	| ID LPAREN al=argument_list RPAREN
	{

		writeIntoparserLogFile("\tCALL "+$ID->getText()+"\n");
		auto it = $al.arg_list.get_variables();
		for(auto hehe: it) {
			writeIntoparserLogFile("\tADD SP, 2");
		}
	}
	| LPAREN expression RPAREN
	| CONST_INT 
	{
		writeIntoparserLogFile("\tMOV AX, "+ $CONST_INT->getText());
	}
	| CONST_FLOAT
	{
		writeIntoparserLogFile("\tMOV AX, "+ $CONST_FLOAT->getText());
	}
	| v=variable INCOP 
	{
		auto sym_info = symbol_table.LookUp($v.name_line);

		bool is_arr = sym_info->getSymbolType().find("arr") != string::npos;

		if(is_arr) {
			if(sym_info->is_global) {
				writeIntoparserLogFile("\tMOV BX, 2");
				writeIntoparserLogFile("\tMUL BX");
				writeIntoparserLogFile("\tMOV BX, AX");
				writeIntoparserLogFile("\tMOV AX, "+sym_info->getSymbolName()+"[BX]");
				writeIntoparserLogFile("\tPUSH AX");
				writeIntoparserLogFile("\tINC AX");
				writeIntoparserLogFile("\tMOV "+sym_info->getSymbolName()+"[BX], AX");
				writeIntoparserLogFile("\tPOP AX");

			}else{

			}
		}else {
			if(sym_info->is_global) {

				writeIntoparserLogFile("\tINC WORD PTR "+sym_info->getSymbolName()+"\n");

			}else{
				if(is_main){
					writeIntoparserLogFile("\tINC WORD PTR [BP-"+to_string(sym_info->offset)+"]\n");
				}else {
					if(sym_info->getSymbolType() == "param") {
						writeIntoparserLogFile("\tMOV AX, [BP+"+to_string(sym_info->offset)+"]");
						writeIntoparserLogFile("\tPUSH AX");
						writeIntoparserLogFile("\tINC AX");
						writeIntoparserLogFile("\tMOV [BP+"+to_string(sym_info->offset)+"], AX");
						writeIntoparserLogFile("\tPOP AX");
					}else{
						writeIntoparserLogFile("\tMOV AX, [BP-"+to_string(sym_info->offset)+"]");
						writeIntoparserLogFile("\tPUSH AX");
						writeIntoparserLogFile("\tINC AX");
						writeIntoparserLogFile("\tMOV [BP-"+to_string(sym_info->offset)+"], AX");
						writeIntoparserLogFile("\tPOP AX");
					}
				}
			}
		}

	}
	| vr=variable DECOP
	{
		auto sym_info = symbol_table.LookUp($vr.name_line);

		bool is_arr = sym_info->getSymbolType().find("arr") != string::npos;

		if(is_arr) {

		}else {
			if(sym_info->is_global) {
				writeIntoparserLogFile("\tMOV AX, "+sym_info->getSymbolName());
				writeIntoparserLogFile("DEC WORD PTR "+sym_info->getSymbolName()+"\n");

			}else{
				if(sym_info->getSymbolType() == "param") {
						writeIntoparserLogFile("\tMOV AX, [BP+"+to_string(sym_info->offset)+"]");
						writeIntoparserLogFile("\tPUSH AX");
						writeIntoparserLogFile("\tDEC AX");
						writeIntoparserLogFile("\tMOV [BP+"+to_string(sym_info->offset)+"], AX");
						writeIntoparserLogFile("\tPOP AX");
				}else{
						writeIntoparserLogFile("\tMOV AX, [BP-"+to_string(sym_info->offset)+"]");
						writeIntoparserLogFile("\tPUSH AX");
						writeIntoparserLogFile("\tDEC AX");
						writeIntoparserLogFile("\tMOV [BP-"+to_string(sym_info->offset)+"], AX");
						writeIntoparserLogFile("\tPOP AX");
				}
			}
		}
	}
	;
	
argument_list returns [str_list arg_list] : a=arguments
				{
					$arg_list.set_variables($a.arg_list.get_variables());
				}
			  | { 
				vector<string> v;
				$arg_list.set_variables(v);}
			  ;
	
arguments returns [str_list arg_list]

 : a=arguments COMMA logic_expression 
				{
					$arg_list.set_variables($a.arg_list.get_variables());
					$arg_list.add("hehe");
					writeIntoparserLogFile("\tPUSH AX");
				}
	      | logic_expression
		  	{
					$arg_list.add("hehe");
					writeIntoparserLogFile("\tPUSH AX");
			}
	      ;
