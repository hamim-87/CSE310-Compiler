%option noyywrap

%x CONST_CHAR_STATE
%x SINGLE_LINE_COMMENT_STATE
%x MULTILINE_COMMENT_STATE
%x CONST_DOUBLE_STATE

%{
    #include <bits/stdc++.h>
    #include "headers/2105160_symbol_table.hpp"
    using namespace std;



    int current_line_number = 1;
    int line_count =1;
    int string_line=0;
    int error_count = 0;
    int multi_comment_line = 0;

    string current_char ="";
    string corrected_ascii="";

    string current_string ="";
    string converted_string = "";

    string comment = "";

    ofstream _log_file("2105160_log.txt");
    ofstream _token_file("2105160_tokens.txt");

    
    SymbolTable symbol_table(7,"SDBM",_log_file);

    void error(pair<string,string> msg) {
            //Error at line no 1: Too many decimal points 127.0.0.1
             _log_file<<"Error at line no "<<current_line_number-multi_comment_line<<": "<<msg.first<<" "<<msg.second<<endl<<endl<<endl;
             error_count++;
        }
        void error1(pair<string,string> msg) {
            //Error at line no 1: Too many decimal points 127.0.0.1
             _log_file<<"Error at line no "<<current_line_number-string_line-multi_comment_line<<": "<<msg.first<<" "<<msg.second<<endl<<endl;
             string_line = 0;
             error_count++;
        }

    //
 
    

    //HELPERS

    void char_reset() {
        current_char = "";
        corrected_ascii = "";
    }
   
    string upper_case(string s) {
        string st= "";
        for(char c: s){
            st += toupper(c);
        }
        return st;
    }

    void commet_reset() {
        comment = "";
        multi_comment_line = 0;
    }


    void write_to_log(pair<string,string> t) {
        //Line no 1: Token <INT> Lexeme int found
        _log_file<<"Line no "<<current_line_number<<": Token <"<<t.first<<"> Lexeme "<<t.second<<" found"<<endl<<endl;
    }

    void write_to_log(pair<string,string> t,pair<string,string> lex) {
        //Line no 3: Token <STRING> Lexeme "I own a dog." found --> <STRING, I own a dog.>
        _log_file<<"Line no "<<current_line_number<<": Token <"<<t.first<<"> Lexeme "<<t.second<<" found"<<" --> <"<<lex.first<<", "<<lex.second<<">"<<endl<<endl;
    }
    

    void write_to_token(pair<string,string> t) {
        _token_file<<"<"<<t.first<<", "<<t.second<<"> ";
    }
    void write_to_token(string t) {
        _token_file<<"<"<<t<<"> ";
    }


    void error() {}

    char ascii(string text) {
        char asc;
        char ch = text[1];
        switch(ch)
        {
            case 'f': asc = '\f'; break;
            case 'b': asc = '\b'; break;
            case 'r': asc = '\r'; break;
            case 'n': asc = '\n'; break;
            case 'a': asc = '\a'; break;
            case '\'': asc = '\''; break;
            case '\\': asc = '\\'; break;
            case 't': asc = '\t'; break;
            case 'v': asc = '\v'; break;
            case '\"': asc = '\"'; break;
            case '0': asc = '\0'; break;
            default : asc = '$';          
        }
        return asc;
    }

    void handle_const_char() {
        int len = corrected_ascii.length();

 
        if(len == 2) {
            error({"Empty character constant error",corrected_ascii});
        }
        else if(len > 3) {
            error({"Multi character constant error", current_char});
        }
        else 
        {
            char ch = corrected_ascii[1];
            if(ch == '$') {
                error();
            }
            else
            {
                string lexeme = string(1,ch);
                write_to_token({"CONST_CHAR",lexeme});

                string qlex = "'" + string(1, current_char[1]) +
                (current_char[2] == '\'' ? "" : string(1, current_char[2])) +
                "'";
                write_to_log({"CONST_CHAR", qlex},{"CONST_CHAR",lexeme}); 
                symbol_table.Insert(qlex,"CONST_CHAR");
            }
        }

        char_reset();
    }

    void string_reset() {
        current_string = "";
        converted_string = "";
        string_line = 0;
    }

    void handle_string() {

        write_to_log({"STRING",current_string},{"STRING",converted_string});
        write_to_token({"STRING",converted_string});
        string_reset();
    }


    void handle_comment() {
        write_to_log({"COMMENT",comment});

        commet_reset();
    }


    void inc_count() {
        for(char c: comment) {
            if(c == '\n'){
                line_count++;

            }
        }
        current_line_number = line_count;
    }


    

        
    

%}


DIGITS ([0-9]+)
EXPONENTS ([Ee][+-]?{DIGITS})
ALPHABET ([a-zA-Z])
ALPHANUMERIC ([a-zA-Z0-9_])
SINGLE_COMMENT \/\/.*(\\(\r)?\n.*)*
MULTI_COMMENT ([^\*]|\*[^\/])*\*\/
DECIMAL_FRACTION		\.{DIGITS}+
FLOAT_NUMBER			{DIGITS}*{DECIMAL_FRACTION}
ERROR_FLOAT {DIGITS}*(\.{DIGITS}*){2,}{EXPONENTS}?
FRACTIONAL_EXPONENT		[eE][+-]?{DIGITS}*{DECIMAL_FRACTION}+
ILL_FRAC	({DIGITS}+|{FLOAT_NUMBER}){FRACTIONAL_EXPONENT}
INVALID_ID ({DIGITS}|{FLOAT_NUMBER}){ID}


KEYWORDS "if"|"for"|"do"|"int"|"float"|"void"|"switch"|"default"|"else"|"while"|"break"|"char"|"double"|"return"|"case"|"continue"|"goto"|"long"|"short"|"static"|"unsigned"


CONST_INT {DIGITS}
CONST_FLOAT ({DIGITS}\.{DIGITS}{EXPONENTS}?|{DIGITS}{EXPONENTS}|\.{DIGITS}{EXPONENTS}?) 



ADDOP "+"|"-"
MULOP "*"|"/"|"%"
INCOP "++"|"--"
RELOP "<"|"<="|">"|">="|"=="|"!="
ASSIGNOP "="
LOGICOP "&&"|"||"
NOT "!"
LPAREN "("
RPAREN ")"
LCURL "{"
RCURL "}"
LTHIRD "["
RTHIRD "]"
COMMA ","
SEMICOLON ";"


ID ({ALPHABET}|"_")({ALPHANUMERIC})*


COMMENT ({SINGLE_COMMENT}|{MULTI_COMMENT})

WHITESPACE [ \t\v\f\r]+

NEWLINE [\n]



BACKSLASH [\\]
NON_BACKSLASH			[^\\]

SINGLE_QUOTE "'"
SPECIAL_CHAR "\\0"|"\\v"|"\\b"|"\\r"|"\\\\"|"\\t"|"\\n"|"\\\""|"\\f"|"\\a"|"\\'"
DOUBLE_QUOTE "\""

END_COMMENT "\\*/"





%%


{WHITESPACE} { }


{NEWLINE}   {
                line_count++;
                current_line_number = line_count;
            }

{KEYWORDS}  {
                write_to_log({upper_case(yytext),yytext});
                write_to_token(upper_case(yytext));   
            
            }

{CONST_INT} {
                write_to_log({"CONST_INT",yytext});
                write_to_token({"CONST_INT",yytext});
                symbol_table.Insert(yytext,"CONST_INT");
            }

{CONST_FLOAT}   { 
                    write_to_log({"CONST_FLOAT",yytext});
                    write_to_token({"CONST_FLOAT",yytext});
                    symbol_table.Insert(yytext,"CONST_FLOAT");
                }

{ERROR_FLOAT}   {
                    error({"Too many decimal points",yytext});
                }

{ILL_FRAC}  {
                error({"Ill formed number",yytext});
            }


{ADDOP}     {
                write_to_log({"ADDOP",yytext});
                write_to_token({"ADDOP",yytext});
            }

{MULOP}     {
                write_to_log({"MULOP",yytext});
                write_to_token({"MULOP",yytext});
            }


{INCOP}     {
                write_to_log({"INCOP",yytext});
                write_to_token({"INCOP",yytext});
            }


{RELOP}     {
                write_to_log({"RELOP",yytext});
                write_to_token({"RELOP",yytext});
            }

{ASSIGNOP}  {
                write_to_log({"ASSIGNOP",yytext});
                write_to_token({"ASSIGNOP",yytext});
            }


{LOGICOP}   {
                write_to_log({"LOGICOP",yytext});
                write_to_token({"LOGICOP",yytext});
            }

{NOT}   {
                write_to_log({"NOT",yytext});
                write_to_token({"NOT",yytext});
            }


{LPAREN}   {
                write_to_log({"LPAREN",yytext});
                write_to_token({"LPAREN",yytext});
            }

{RPAREN}   {
                write_to_log({"RPAREN",yytext});
                write_to_token({"RPAREN",yytext});
            }

{LCURL}     {
                write_to_log({"LCURL",yytext});
                write_to_token({"LCURL",yytext});
                symbol_table.EnterScope();
            }


{RCURL}     {
                write_to_log({"RCURL",yytext});
                write_to_token({"RCURL",yytext});
                symbol_table.ExitScope();
            }


{LTHIRD}   {
                write_to_log({"LTHIRD",yytext});
                write_to_token({"LTHIRD",yytext});
            }


{RTHIRD}   {
                write_to_log({"RTHIRD",yytext});
                write_to_token({"RTHIRD",yytext});
            }

{COMMA}     {
                write_to_log({"COMMA",yytext});
                write_to_token({"COMMA",yytext});
            }

{SEMICOLON} {
                write_to_log({"SEMICOLON",yytext});
                write_to_token({"SEMICOLON",yytext});
            }



{INVALID_ID} {
                error1({"Invalid prefix on ID or invalid suffix on Number",yytext});
            }   

{ID}        {
                write_to_log({"ID",yytext});
                write_to_token({"ID",yytext});
                symbol_table.Insert(yytext,"ID");
            }

{SINGLE_QUOTE}  {
                    BEGIN CONST_CHAR_STATE;
                    current_char +=yytext;
                    corrected_ascii += yytext;

                    
                }

<CONST_CHAR_STATE>{

{SINGLE_QUOTE}  {
                        current_char+=yytext;
                        corrected_ascii += yytext;
                        handle_const_char();
                        
                        BEGIN INITIAL;
                    }
    
{NEWLINE}   {

                    error({"Unterminated character",current_char});
                    char_reset();
                    line_count++;
                    current_line_number = line_count;
                    BEGIN INITIAL;
                }
<<EOF>> {
                error();
                BEGIN INITIAL;

            }

{SPECIAL_CHAR}  {
                        corrected_ascii += ascii(yytext);
                        current_char += yytext;
                    }

{NON_BACKSLASH}               {

                        current_char+=yytext;
                        corrected_ascii += yytext;
                        error();
                    }

}


{DOUBLE_QUOTE}  {

                    BEGIN CONST_DOUBLE_STATE;
                    current_string +=yytext;
                    
                }

<CONST_DOUBLE_STATE>{
{DOUBLE_QUOTE}	    {
						current_string += yytext;

						handle_string();
                        
						BEGIN INITIAL;
					}


{BACKSLASH}{NEWLINE} {
					    current_string += yytext;
						line_count++;
                        current_line_number = line_count;
                        string_line++;
						
					}
{NEWLINE}	    {
					error1({"Unterminated string",current_string});
                    string_reset();
					line_count++;
                    current_line_number = line_count;
                    
					BEGIN INITIAL;
				}
<<EOF>>		    {
					error();
					BEGIN INITIAL;
				}
{SPECIAL_CHAR}	    {
							converted_string += ascii(yytext);
							current_string += yytext;
						}
. 	{
			converted_string += yytext;
            current_string += yytext;
	}



}


\/\/    {
            BEGIN SINGLE_LINE_COMMENT_STATE;
            comment += yytext;
        }

<SINGLE_LINE_COMMENT_STATE>{

{BACKSLASH}{NEWLINE} {
					    comment += yytext;
						line_count++;
                        current_line_number  = line_count;
						
					}

{NEWLINE}           {
                        line_count++;
                        handle_comment();
                        current_line_number = line_count;
                        BEGIN INITIAL;
                    }
<<EOF>>     {
                handle_comment();
                BEGIN INITIAL;
            }


.   {
        comment += yytext;
    }

}  

\/\*  {
        comment += yytext;
        BEGIN MULTILINE_COMMENT_STATE;
}
<MULTILINE_COMMENT_STATE>{
\*\/	{
				comment += yytext;

				handle_comment();
				BEGIN INITIAL;
		}
{NEWLINE}	{
					comment += yytext;
					line_count++;
                    current_line_number = line_count;
                    multi_comment_line++;
				}
<<EOF>>		{
					error1({"Unterminated comment", comment});
                    commet_reset();
					BEGIN INITIAL;
				}
. 	{
		comment += yytext;
	}

}


. {
    error1({"Unrecognized character",yytext});
}     



			

%%

int main(int argc,char *argv[]){
	
		if(argc!=2){
		printf("Please provide input file name and try again\n");
		return 0;
	}
	
	FILE *fin=fopen(argv[1],"r");
	if(fin==NULL){
		printf("Cannot open specified file\n");
		return 0;
	}
	

	yyin= fin;
	yylex();

    symbol_table.PrintAllScopeTable();
    _log_file<<"Total lines: "<<line_count<<endl;
	_log_file<< "Total errors: "<<error_count<<endl;
	fclose(yyin);
	return 0;
}
