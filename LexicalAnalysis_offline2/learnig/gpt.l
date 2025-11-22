%option noyywrap

%x TAG_START
%x TAG_END

%{
#include <stack>
#include <iostream>
#include <string>
using namespace std;

stack<string> tag_stack;

void handle_push(const char* s) {
    tag_stack.push(s);
}

void handle_error() {
    cout << "invalid" << endl;
    exit(1);
}

void handle_pop(const char* s) {
    if(tag_stack.empty()) {
        handle_error();
    }
    string top = tag_stack.top();
    tag_stack.pop();
    
    // Convert closing tag to opening format for comparison
    string input_tag(s + 2, strlen(s) - 3); // Remove </ and >
    cout<<top<<endl;
    if(top != input_tag) {
        handle_error();
    }
}
%}



WHITESPACE     [ \t\r\n]
TAG_OPEN       "<(html|head|body|p)>"
TAG_CLOSE      "</(html|head|body|p)>"

%%

{WHITESPACE}   { /* ignore whitespace */ }

"<"            { BEGIN TAG_START; }
"</"             { BEGIN TAG_END; }

<TAG_START>{
{TAG_OPEN}    { 
        handle_push(yytext + 1); // +1 to skip '<'
        BEGIN(INITIAL); 
    }
.|\\n        { handle_error(); }
}

<TAG_END>{
{TAG_CLOSE}   { 
        handle_pop(yytext);
        BEGIN(INITIAL); 
    }
.|\\n        { handle_error(); }
}

.              { handle_error(); }

%%

int main(int argc, char* argv[]) {
    if(argc < 2) {
        cerr << "Usage: " << argv[0] << " <input-file>" << endl;
        return 1;
    }

    yyin = fopen(argv[1], "r");
    if(!yyin) {
        cerr << "Error opening file: " << argv[1] << endl;
        return 1;
    }

    yylex();
    
    // Check if all tags were properly closed
    if(!tag_stack.empty()) {
        handle_error();
    }
    
    cout << "valid" << endl;
    fclose(yyin);
    return 0;
}