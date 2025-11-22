%option noyywrap

%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STACK_SIZE 100

char *stack[MAX_STACK_SIZE];
int top = -1;

void push(char *tag) {
    if (top < MAX_STACK_SIZE - 1) {
        stack[++top] = tag;
    }
}

char *pop() {
    if (top >= 0) {
        return stack[top--];
    }
    return NULL;
}

int is_empty() {
    return top == -1;
}

int is_matching(char *opening, char *closing) {
    return (strcmp(opening, "<html>") == 0 && strcmp(closing, "</html>") == 0) ||
           (strcmp(opening, "<head>") == 0 && strcmp(closing, "</head>") == 0) ||
           (strcmp(opening, "<body>") == 0 && strcmp(closing, "</body>") == 0) ||
           (strcmp(opening, "<p>") == 0 && strcmp(closing, "</p>") == 0);
}

%}

%%

// Match opening tags
<html>   { push("<html>"); }
<head>   { push("<head>"); }
<body>   { push("<body>"); }
<p>      { push("<p>"); }

// Match closing tags
</html>  { 
              if (is_empty() || !is_matching(pop(), "</html>")) {
                  printf("Error: Unmatched </html>\n");
              }
          }
</head>  { 
              if (is_empty() || !is_matching(pop(), "</head>")) {
                  printf("Error: Unmatched </head>\n");
              }
          }
</body>  { 
              if (is_empty() || !is_matching(pop(), "</body>")) {
                  printf("Error: Unmatched </body>\n");
              }
          }
</p>     { 
              if (is_empty() || !is_matching(pop(), "</p>")) {
                  printf("Error: Unmatched </p>\n");
              }
          }

// Handle text and other characters
.        { /* Ignore other characters */ }

%%

int main(int argc, char* argv[]) {
    if (argc < 2) {
        printf("Usage: %s <input_file>\n", argv[0]);
        return 1;
    }

    FILE *fin = fopen(argv[1], "r");
    if (fin == NULL) {
        printf("Error: Can't open file %s\n", argv[1]);
        return 1;
    }

    yyin = fin;
    yylex();

    if (!is_empty()) {
        printf("Error: Unmatched opening tags remain.\n");
    }

    fclose(fin);
    return 0;
}