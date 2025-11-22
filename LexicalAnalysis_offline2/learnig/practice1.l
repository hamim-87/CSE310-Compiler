%option noyywrap


%x START_L
%x END_R

%{

    #include <bits/stdc++.h>
    using namespace std;

    stack<string> st;


    string txt = "";


    void handle_push(string s) {
        cout<<"push "<<s<<endl;
        st.push(s);
    }

    void handle_error() {
        cout<<"invalid"<<endl;
        exit(1);

    }

    void handle_pop(string s) {
        string top = st.top();

        cout<<"pop: "<<s<<endl;

        if(top == s) {
            st.pop();
        }else{
            handle_error();
        }

        
    }




%}

TAG "html>"|"p>"|"body>"|"head>"
L_START "<"
WHITESPACE ([ \t\r\v\f]+)
L_END "</"
TEXT  [^<>\n]+


%%

{WHITESPACE} {}

{L_START}   {
                printf("open tag\n");
                BEGIN START_L;

            }


<START_L>{

{TAG}   { 
            
            handle_push(yytext+1);
            BEGIN INITIAL;
        }
.       {
            printf("%s",yytext);
            handle_error();
            
        }

}



{L_END}  {
            BEGIN END_R;
        }

<END_R>{
{TAG}   {
            handle_pop(yytext + 1);
            BEGIN INITIAL;
        }

.       {
            printf("error\n");
             handle_error();
        }
}

{TEXT} {}

<<EOF>> {
    cout<<"valid"<<endl;
    exit(0);
}



%% 

int main(int argc, char* argv[]) {

    if(argc < 2 ) {
        printf("give the input file\n");
        return 0;
    }

    FILE *fin = fopen(argv[1],"r");

    if(fin == NULL) {
        printf("can't open file");
        return 0;
    }

    yyin = fin;
    yylex();

    fclose(fin);
    return 0;
}