
**CSE310 Compiler Assignments**

- **Overview:** This repository contains four course-offline
folders (lexical, syntax/semantic, symbol table, and intermediate code generation) developed for a CSE310 compiler course. Each folder includes source files, supporting headers, and helper scripts to build and run that part of the compiler pipeline.

**Repository Structure:**
- **`ICG_offline4`**: Intermediate Code Generation / integration with ANTLR4 (C++ target). Contains ANTLR grammars, `Ctester.cpp`, a `symbol_table` subfolder, and `run-script.sh` to generate parser/lexer, compile and run the test harness.
- **`Syntax&SemanticAnalysis_offline3`**: ANTLR-based parser + semantic checks. Contains grammars, `Ctester.cpp`, `symbol_table` subfolder and `run-script.sh` (similar build/run to `ICG_offline4`).
- **`LexicalAnalysis_offline2`**: Flex-based lexer implementation. Includes `2105160.l`, a `headers/` folder with supporting symbol table headers/implementations, `run.sh` to build and run the lexer, and sample input/output files.
- **`SymbolTable_offline1`**: Standalone symbol table implementation and test harness. Contains `2105160_main.cpp`, symbol table source/header files, `run.sh` to build and run the CLI test program, and sample input.

**Quick Requirements (dependencies):**
- **C++ compiler:** `g++` (C++17 recommended).
- **ANTLR4 (for C++ target):** ANTLR 4.13.2 CLI and the C++ runtime library installed (headers in `/usr/local/include/antlr4-runtime` and library in `/usr/local/lib`).
- **flex (lex):** for `LexicalAnalysis_offline2` (and `-lfl` linker flag).
- **pthread** and standard system libraries.

**Per-folder build & run (copy commands into the folder and run in bash):**

- `ICG_offline4` (ANTLR4 C++ pipeline)
	- Generate C++ lexer/parser and build:
		```bash
		antlr4 -v 4.13.2 -Dlanguage=Cpp C2105160Lexer.g4
		antlr4 -v 4.13.2 -Dlanguage=Cpp C2105160Parser.g4
		g++ -std=c++17 -w -I/usr/local/include/antlr4-runtime -Isymbol_table -c C2105160Lexer.cpp C2105160Parser.cpp Ctester.cpp symbol_table/2105160_scope_table.cpp symbol_table/2105160_symbol_table.cpp symbol_table/2105160_symbol_info.cpp
		g++ -std=c++17 -w C2105160Lexer.o C2105160Parser.o Ctester.o 2105160_symbol_info.o 2105160_scope_table.o 2105160_symbol_table.o -L/usr/local/lib/ -lantlr4-runtime -o Ctester.out -pthread
		LD_LIBRARY_PATH=/usr/local/lib ./Ctester.out <input-file>
		```
	- Notes: `run-script.sh` also removes other generated files (keeps only necessary sources) after running.

- `Syntax&SemanticAnalysis_offline3` (ANTLR4 C++ parser + semantic)
	- Build & run (same flow as `ICG_offline4`):
		```bash
		antlr4 -v 4.13.2 -Dlanguage=Cpp C2105160Lexer.g4
		antlr4 -v 4.13.2 -Dlanguage=Cpp C2105160Parser.g4
		g++ -std=c++17 -w -I/usr/local/include/antlr4-runtime -Isymbol_table -c C2105160Lexer.cpp C2105160Parser.cpp Ctester.cpp symbol_table/2105160_scope_table.cpp symbol_table/2105160_symbol_table.cpp symbol_table/2105160_symbol_info.cpp
		g++ -std=c++17 -w C2105160Lexer.o C2105160Parser.o Ctester.o 2105160_symbol_info.o 2105160_scope_table.o 2105160_symbol_table.o -L/usr/local/lib/ -lantlr4-runtime -o Ctester.out -pthread
		LD_LIBRARY_PATH=/usr/local/lib ./Ctester.out <input-file>
		```

- `LexicalAnalysis_offline2` (Flex-based lexer)
	- Build & run:
		```bash
		flex -o hehe.cpp 2105160.l
		g++ hehe.cpp headers/*.cpp -lfl -o hehe.o
		./hehe.o <input-file>
		```
	- Notes: example usage in repository runs `./hehe.o hehe.txt` and writes tokens to `2105160_tokens.txt` and logs to `2105160_log.txt`.

- `SymbolTable_offline1` (Standalone symbol table)
	- Build & run (test harness):
		```bash
		g++ -g -fsanitize=address -o puzzle 2105160_main.cpp 2105160_scope_table.cpp 2105160_symbol_info.cpp 2105160_symbol_table.cpp
		./puzzle sample_input.txt
		```
	- Notes: The program reads a bucket number and then command lines from input (see `sample_input.txt`). Output is redirected to `output.txt` when `run.sh` is used.

**What each folder does (short):**
- `ICG_offline4`: Integration-level project that generates parser/lexer with ANTLR, links a C++ runtime and symbol table implementation, and runs `Ctester.cpp` as driver.
- `Syntax&SemanticAnalysis_offline3`: Parser and semantic analyzer built with ANTLR; logic is similar to ICG project but focused on syntax/semantic checks.
- `LexicalAnalysis_offline2`: A full lexer implemented in `2105160.l` with logging (`2105160_log.txt`) and token output (`2105160_tokens.txt`). Symbol table is used for identifiers and constants.
- `SymbolTable_offline1`: A command-driven symbol table implementation (insert/lookup/remove/enter/exit scope, print current/all scopes). Useful as a standalone component and for linking into the lexer/parser.

**Notes & recommendations:**
- To build ANTLR-based parts you must have the ANTLR4 toolchain and C++ runtime installed and available on PATH (or use absolute paths to `antlr4`), and the runtime library in a standard library path or set `LD_LIBRARY_PATH=/usr/local/lib` when running.
- For the Flex lexer, install `flex` and link with `-lfl` as shown.
- If you encounter linking errors with ANTLR's C++ runtime, verify that `/usr/local/lib` contains `libantlr4-runtime.*` and that headers are available under `/usr/local/include/antlr4-runtime`.
- Test inputs are in each folder's `problem_defination` / `input` directories; adapt command-line arguments accordingly.

**Next steps (suggested):**
- Add small `Makefile` or `CMakeLists.txt` in each folder to make building easier.
- Consolidate common symbol-table sources into a shared `libs/` folder and update include paths.

If you'd like, I can: add `Makefile`s for each folder, create a top-level build script, or run one of the `run.sh` scripts here to verify outputs — tell me which you'd prefer.
