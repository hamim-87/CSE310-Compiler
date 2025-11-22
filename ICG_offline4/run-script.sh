antlr4 -v 4.13.2 -Dlanguage=Cpp C2105160Lexer.g4
antlr4 -v 4.13.2 -Dlanguage=Cpp C2105160Parser.g4
g++ -std=c++17 -w -I/usr/local/include/antlr4-runtime -Isymbol_table -c C2105160Lexer.cpp C2105160Parser.cpp Ctester.cpp symbol_table/2105160_scope_table.cpp symbol_table/2105160_symbol_table.cpp symbol_table/2105160_symbol_info.cpp
g++ -std=c++17 -w C2105160Lexer.o C2105160Parser.o Ctester.o 2105160_symbol_info.o 2105160_scope_table.o 2105160_symbol_table.o -L/usr/local/lib/ -lantlr4-runtime -o Ctester.out -pthread
LD_LIBRARY_PATH=/usr/local/lib ./Ctester.out $1

shopt -s extglob

# Loop through all files that do NOT match *.sh, *.g4, or Ctester.cpp
for file in !(*.sh|*.g4|Ctester.cpp); do
    # Only delete if it's a regular file
    if [[ -f "$file" ]]; then
        rm -f "$file"
    fi
done