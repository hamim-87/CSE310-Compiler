%option noyywrap

%{
    #include <bits/stdc++.h>
    using namespace std;
    int ct = 0;

    void count(string s) {
        for(int i = 0; i <s.size();i++) {
            ct++;
        }
    }



%}

WHITESPACE  [ \t\f\r\v]
ALPHABETS [a-zA-Z]



%%

{WHITESPACE} {}
{ALPHABETS} {
                count(yytext);

            }



<<EOF>> {cout<<ct<<endl; return 0;}

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
	fclose(yyin);
	return 0;
}