// Generated from /media/hamim/New Volume/DEVops/BUET/3-1/cse320-compiler/offline4/antlr4-resources/antlr4-skeletons/cpp/C2105160Parser.g4 by ANTLR 4.13.1

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class C2105160Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LINE_COMMENT=1, BLOCK_COMMENT=2, STRING=3, WS=4, IF=5, ELSE=6, FOR=7, 
		WHILE=8, PRINTLN=9, RETURN=10, INT=11, FLOAT=12, VOID=13, LPAREN=14, RPAREN=15, 
		LCURL=16, RCURL=17, LTHIRD=18, RTHIRD=19, SEMICOLON=20, COMMA=21, ADDOP=22, 
		SUBOP=23, MULOP=24, INCOP=25, DECOP=26, NOT=27, RELOP=28, LOGICOP=29, 
		ASSIGNOP=30, ID=31, CONST_INT=32, CONST_FLOAT=33;
	public static final int
		RULE_start = 0, RULE_program = 1, RULE_unit = 2, RULE_func_declaration = 3, 
		RULE_func_definition = 4, RULE_parameter_list = 5, RULE_compound_statement = 6, 
		RULE_var_declaration = 7, RULE_declaration_list_err = 8, RULE_type_specifier = 9, 
		RULE_declaration_list = 10, RULE_statements = 11, RULE_statement = 12, 
		RULE_expression_statement = 13, RULE_variable = 14, RULE_expression = 15, 
		RULE_logic_expression = 16, RULE_rel_expression = 17, RULE_simple_expression = 18, 
		RULE_term = 19, RULE_unary_expression = 20, RULE_factor = 21, RULE_argument_list = 22, 
		RULE_arguments = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "program", "unit", "func_declaration", "func_definition", "parameter_list", 
			"compound_statement", "var_declaration", "declaration_list_err", "type_specifier", 
			"declaration_list", "statements", "statement", "expression_statement", 
			"variable", "expression", "logic_expression", "rel_expression", "simple_expression", 
			"term", "unary_expression", "factor", "argument_list", "arguments"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'if'", "'else'", "'for'", "'while'", "'println'", 
			"'return'", "'int'", "'float'", "'void'", "'('", "')'", "'{'", "'}'", 
			"'['", "']'", "';'", "','", null, null, null, "'++'", "'--'", "'!'", 
			null, null, "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LINE_COMMENT", "BLOCK_COMMENT", "STRING", "WS", "IF", "ELSE", 
			"FOR", "WHILE", "PRINTLN", "RETURN", "INT", "FLOAT", "VOID", "LPAREN", 
			"RPAREN", "LCURL", "RCURL", "LTHIRD", "RTHIRD", "SEMICOLON", "COMMA", 
			"ADDOP", "SUBOP", "MULOP", "INCOP", "DECOP", "NOT", "RELOP", "LOGICOP", 
			"ASSIGNOP", "ID", "CONST_INT", "CONST_FLOAT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "C2105160Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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





	public C2105160Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{

			    writeIntoparserLogFile(".MODEL SMALL");
			    writeIntoparserLogFile(".STACK 100H");
			    writeIntoparserLogFile(".DATA");
			    writeIntoparserLogFile("\tnumber DB 6 DUP(?)");  


			setState(49);
			program(0);


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
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		return program(0);
	}

	private ProgramContext program(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ProgramContext _localctx = new ProgramContext(_ctx, _parentState);
		ProgramContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_program, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(53);
			unit();
			}
			_ctx.stop = _input.LT(-1);
			setState(59);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ProgramContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_program);
					setState(55);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(56);
					unit();
					}
					} 
				}
				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnitContext extends ParserRuleContext {
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public Func_declarationContext func_declaration() {
			return getRuleContext(Func_declarationContext.class,0);
		}
		public Func_definitionContext func_definition() {
			return getRuleContext(Func_definitionContext.class,0);
		}
		public UnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit; }
	}

	public final UnitContext unit() throws RecognitionException {
		UnitContext _localctx = new UnitContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_unit);
		try {
			setState(65);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				var_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				func_declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				func_definition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_declarationContext extends ParserRuleContext {
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public TerminalNode ID() { return getToken(C2105160Parser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(C2105160Parser.LPAREN, 0); }
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(C2105160Parser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(C2105160Parser.SEMICOLON, 0); }
		public Func_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_declaration; }
	}

	public final Func_declarationContext func_declaration() throws RecognitionException {
		Func_declarationContext _localctx = new Func_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_func_declaration);
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				type_specifier();
				setState(68);
				match(ID);
				setState(69);
				match(LPAREN);
				setState(70);
				parameter_list(0);
				setState(71);
				match(RPAREN);
				setState(72);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				type_specifier();
				setState(75);
				match(ID);
				setState(76);
				match(LPAREN);
				setState(77);
				match(RPAREN);
				setState(78);
				match(SEMICOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_definitionContext extends ParserRuleContext {
		public Token ID;
		public Parameter_listContext p;
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public TerminalNode ID() { return getToken(C2105160Parser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(C2105160Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(C2105160Parser.RPAREN, 0); }
		public Compound_statementContext compound_statement() {
			return getRuleContext(Compound_statementContext.class,0);
		}
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public Func_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_definition; }
	}

	public final Func_definitionContext func_definition() throws RecognitionException {
		Func_definitionContext _localctx = new Func_definitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_func_definition);
		try {
			setState(104);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				type_specifier();
				 stack_offset = 0; 
				setState(84);
				((Func_definitionContext)_localctx).ID = match(ID);


						
				        string code = "";
								f_name=((Func_definitionContext)_localctx).ID->getText();
				        if(!is_code) {
				            writeIntoparserLogFile(".CODE");
				            is_code = true;
				        }

				        if(((Func_definitionContext)_localctx).ID->getText() == "main") {
							is_main = true;
				            code = "main PROC";
				            writeIntoparserLogFile(code);
				            writeIntoparserLogFile("\tMOV AX, @DATA");
				            writeIntoparserLogFile("\tMOV DS, AX");
				        } else {
				            code = ((Func_definitionContext)_localctx).ID->getText() + " PROC";
				            writeIntoparserLogFile(code);
				            writeIntoparserLogFile("\tPUSH BP");
				            writeIntoparserLogFile("\tMOV BP, SP");
				        }
				    
				setState(86);
				match(LPAREN);
				 symbol_table.EnterScope(); 
				setState(88);
				((Func_definitionContext)_localctx).p = parameter_list(0);

						auto para = ((Func_definitionContext)_localctx).p.param_list.get_variables();
						int ops = 4;
						for(int i = para.size()-1;i>=0;i--) {
							auto sym_info = symbol_table.LookUp(para[i]);

							sym_info->offset = ops;
							ops+=2;
						}
					
				setState(90);
				match(RPAREN);
				setState(91);
				compound_statement();


						
				        symbol_table.ExitScope();
				        if(((Func_definitionContext)_localctx).ID->getText() != "main") {
				            writeIntoparserLogFile(((Func_definitionContext)_localctx).ID->getText() +"_end:");

							if(stack_offset>0) {
								 writeIntoparserLogFile("\tADD SP, " + to_string(stack_offset));
							}
				            writeIntoparserLogFile("\tPOP BP");
				            writeIntoparserLogFile("\tRET");
				        }
				        writeIntoparserLogFile(((Func_definitionContext)_localctx).ID->getText() + " ENDP");
				        writeIntoparserLogFile("");
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				type_specifier();
				 stack_offset = 0; 
				setState(96);
				((Func_definitionContext)_localctx).ID = match(ID);

								f_name=((Func_definitionContext)_localctx).ID->getText();
				        string code = "";
				        if(!is_code) {
				            writeIntoparserLogFile(".CODE");
				            is_code = true;
				        }

				        if(((Func_definitionContext)_localctx).ID->getText() == "main") {
							is_main= true;
				            code = "main PROC";
				            writeIntoparserLogFile(code);
				            writeIntoparserLogFile("\tMOV AX, @DATA");
				            writeIntoparserLogFile("\tMOV DS, AX");
							writeIntoparserLogFile("\tPUSH BP");
							writeIntoparserLogFile("\tMOV BP, SP\n");
				        } else {
				            code = ((Func_definitionContext)_localctx).ID->getText() + " PROC";
				            writeIntoparserLogFile(code);
				            writeIntoparserLogFile("\tPUSH BP");
				            writeIntoparserLogFile("\tMOV BP, SP");
				        }
				    
				setState(98);
				match(LPAREN);
				 symbol_table.EnterScope(); 
				setState(100);
				match(RPAREN);
				setState(101);
				compound_statement();

				        symbol_table.ExitScope();
				        if(((Func_definitionContext)_localctx).ID->getText() != "main") {
							if(stack_offset>0) {
								 writeIntoparserLogFile("\tADD SP, " + to_string(stack_offset));
							}
				            writeIntoparserLogFile("\tPOP BP");
				            writeIntoparserLogFile("\tRET");
				        }
				        writeIntoparserLogFile(((Func_definitionContext)_localctx).ID->getText() + " ENDP");
				        writeIntoparserLogFile("");
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Parameter_listContext extends ParserRuleContext {
		public str_list param_list;
		public Parameter_listContext p;
		public Token ID;
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public TerminalNode ID() { return getToken(C2105160Parser.ID, 0); }
		public TerminalNode COMMA() { return getToken(C2105160Parser.COMMA, 0); }
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public Parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_list; }
	}

	public final Parameter_listContext parameter_list() throws RecognitionException {
		return parameter_list(0);
	}

	private Parameter_listContext parameter_list(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Parameter_listContext _localctx = new Parameter_listContext(_ctx, _parentState);
		Parameter_listContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_parameter_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(107);
				type_specifier();
				setState(108);
				((Parameter_listContext)_localctx).ID = match(ID);

				        symbol_table.Insert(((Parameter_listContext)_localctx).ID->getText(), "param");
						_localctx.param_list.add(((Parameter_listContext)_localctx).ID->getText());
				    
				}
				break;
			case 2:
				{
				setState(111);
				type_specifier();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(125);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(123);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new Parameter_listContext(_parentctx, _parentState);
						_localctx.p = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_parameter_list);
						setState(114);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(115);
						match(COMMA);
						setState(116);
						type_specifier();
						setState(117);
						((Parameter_listContext)_localctx).ID = match(ID);


						                  symbol_table.Insert(((Parameter_listContext)_localctx).ID->getText(), "param");
						          			_localctx.param_list.set_variables(((Parameter_listContext)_localctx).p.param_list.get_variables());
						          			_localctx.param_list.add(((Parameter_listContext)_localctx).ID->getText());
						              
						}
						break;
					case 2:
						{
						_localctx = new Parameter_listContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_parameter_list);
						setState(120);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(121);
						match(COMMA);
						setState(122);
						type_specifier();
						}
						break;
					}
					} 
				}
				setState(127);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_statementContext extends ParserRuleContext {
		public TerminalNode LCURL() { return getToken(C2105160Parser.LCURL, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RCURL() { return getToken(C2105160Parser.RCURL, 0); }
		public Compound_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_statement; }
	}

	public final Compound_statementContext compound_statement() throws RecognitionException {
		Compound_statementContext _localctx = new Compound_statementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_compound_statement);
		try {
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				match(LCURL);
				setState(129);
				statements(0);
				setState(130);
				match(RCURL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				match(LCURL);
				setState(133);
				match(RCURL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_declarationContext extends ParserRuleContext {
		public Type_specifierContext t;
		public Declaration_listContext dl;
		public Token sm;
		public Declaration_list_errContext de;
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public Declaration_listContext declaration_list() {
			return getRuleContext(Declaration_listContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(C2105160Parser.SEMICOLON, 0); }
		public Declaration_list_errContext declaration_list_err() {
			return getRuleContext(Declaration_list_errContext.class,0);
		}
		public Var_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration; }
	}

	public final Var_declarationContext var_declaration() throws RecognitionException {
		Var_declarationContext _localctx = new Var_declarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_var_declaration);
		try {
			setState(145);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				((Var_declarationContext)_localctx).t = type_specifier();
				setState(137);
				((Var_declarationContext)_localctx).dl = declaration_list(0);
				setState(138);
				((Var_declarationContext)_localctx).sm = match(SEMICOLON);

						vector<std::string> vars = ((Var_declarationContext)_localctx).dl.var_list.get_variables();

						for(const auto &it: vars) {
							size_t loc = it.find("[");
							string name;
							string d_type;
							string sz;
							bool is_array = false;
							if(loc == string::npos) {
								name = it;
								d_type = ((Var_declarationContext)_localctx).t.name_line;
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
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				((Var_declarationContext)_localctx).t = type_specifier();
				setState(142);
				((Var_declarationContext)_localctx).de = declaration_list_err();
				setState(143);
				((Var_declarationContext)_localctx).sm = match(SEMICOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declaration_list_errContext extends ParserRuleContext {
		public std::string error_name;
		public Declaration_list_errContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_list_err; }
	}

	public final Declaration_list_errContext declaration_list_err() throws RecognitionException {
		Declaration_list_errContext _localctx = new Declaration_list_errContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaration_list_err);
		try {
			enterOuterAlt(_localctx, 1);
			{

			        ((Declaration_list_errContext)_localctx).error_name =  "Error in declaration list";
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type_specifierContext extends ParserRuleContext {
		public std::string name_line;
		public TerminalNode INT() { return getToken(C2105160Parser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(C2105160Parser.FLOAT, 0); }
		public TerminalNode VOID() { return getToken(C2105160Parser.VOID, 0); }
		public Type_specifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_specifier; }
	}

	public final Type_specifierContext type_specifier() throws RecognitionException {
		Type_specifierContext _localctx = new Type_specifierContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type_specifier);
		try {
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				match(INT);
				 ((Type_specifierContext)_localctx).name_line =  "int"; 
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(FLOAT);
				 ((Type_specifierContext)_localctx).name_line =  "float";  
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				match(VOID);
				 ((Type_specifierContext)_localctx).name_line =  "void"; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declaration_listContext extends ParserRuleContext {
		public str_list var_list;
		public Declaration_listContext dl;
		public Declaration_listContext dll;
		public Token ID;
		public Token LTHIRD;
		public Token CONST_INT;
		public Token RTHIRD;
		public TerminalNode ID() { return getToken(C2105160Parser.ID, 0); }
		public TerminalNode LTHIRD() { return getToken(C2105160Parser.LTHIRD, 0); }
		public TerminalNode CONST_INT() { return getToken(C2105160Parser.CONST_INT, 0); }
		public TerminalNode RTHIRD() { return getToken(C2105160Parser.RTHIRD, 0); }
		public TerminalNode COMMA() { return getToken(C2105160Parser.COMMA, 0); }
		public Declaration_listContext declaration_list() {
			return getRuleContext(Declaration_listContext.class,0);
		}
		public Declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_list; }
	}

	public final Declaration_listContext declaration_list() throws RecognitionException {
		return declaration_list(0);
	}

	private Declaration_listContext declaration_list(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Declaration_listContext _localctx = new Declaration_listContext(_ctx, _parentState);
		Declaration_listContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_declaration_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(158);
				((Declaration_listContext)_localctx).ID = match(ID);


								_localctx.var_list.add(((Declaration_listContext)_localctx).ID->getText());





							
				}
				break;
			case 2:
				{
				setState(160);
				((Declaration_listContext)_localctx).ID = match(ID);
				setState(161);
				((Declaration_listContext)_localctx).LTHIRD = match(LTHIRD);
				setState(162);
				((Declaration_listContext)_localctx).CONST_INT = match(CONST_INT);
				setState(163);
				((Declaration_listContext)_localctx).RTHIRD = match(RTHIRD);


								_localctx.var_list.add(((Declaration_listContext)_localctx).ID->getText() + ((Declaration_listContext)_localctx).LTHIRD->getText() + ((Declaration_listContext)_localctx).CONST_INT->getText() + ((Declaration_listContext)_localctx).RTHIRD->getText());

						  
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(180);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(178);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new Declaration_listContext(_parentctx, _parentState);
						_localctx.dl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_declaration_list);
						setState(167);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(168);
						match(COMMA);
						setState(169);
						((Declaration_listContext)_localctx).ID = match(ID);



						          				_localctx.var_list.set_variables(((Declaration_listContext)_localctx).dl.var_list.get_variables());
						          				_localctx.var_list.add(((Declaration_listContext)_localctx).ID->getText());

						          				
						          			
						}
						break;
					case 2:
						{
						_localctx = new Declaration_listContext(_parentctx, _parentState);
						_localctx.dll = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_declaration_list);
						setState(171);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(172);
						match(COMMA);
						setState(173);
						((Declaration_listContext)_localctx).ID = match(ID);
						setState(174);
						((Declaration_listContext)_localctx).LTHIRD = match(LTHIRD);
						setState(175);
						((Declaration_listContext)_localctx).CONST_INT = match(CONST_INT);
						setState(176);
						((Declaration_listContext)_localctx).RTHIRD = match(RTHIRD);


						          				_localctx.var_list.set_variables(((Declaration_listContext)_localctx).dll.var_list.get_variables());
						          				_localctx.var_list.add(((Declaration_listContext)_localctx).ID->getText() + ((Declaration_listContext)_localctx).LTHIRD->getText() + ((Declaration_listContext)_localctx).CONST_INT->getText() + ((Declaration_listContext)_localctx).RTHIRD->getText());


						          			
						}
						break;
					}
					} 
				}
				setState(182);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
	}

	public final StatementsContext statements() throws RecognitionException {
		return statements(0);
	}

	private StatementsContext statements(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatementsContext _localctx = new StatementsContext(_ctx, _parentState);
		StatementsContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_statements, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(184);
			statement();
			}
			_ctx.stop = _input.LT(-1);
			setState(190);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatementsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_statements);
					setState(186);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(187);
					statement();
					}
					} 
				}
				setState(192);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public ExpressionContext ex;
		public StatementContext st1;
		public StatementContext st2;
		public Token ID;
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public List<Expression_statementContext> expression_statement() {
			return getRuleContexts(Expression_statementContext.class);
		}
		public Expression_statementContext expression_statement(int i) {
			return getRuleContext(Expression_statementContext.class,i);
		}
		public Compound_statementContext compound_statement() {
			return getRuleContext(Compound_statementContext.class,0);
		}
		public TerminalNode FOR() { return getToken(C2105160Parser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(C2105160Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(C2105160Parser.RPAREN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode IF() { return getToken(C2105160Parser.IF, 0); }
		public TerminalNode ELSE() { return getToken(C2105160Parser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(C2105160Parser.WHILE, 0); }
		public TerminalNode PRINTLN() { return getToken(C2105160Parser.PRINTLN, 0); }
		public TerminalNode ID() { return getToken(C2105160Parser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(C2105160Parser.SEMICOLON, 0); }
		public TerminalNode RETURN() { return getToken(C2105160Parser.RETURN, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_statement);
		try {
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				var_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				expression_statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(195);
				compound_statement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				match(FOR);
				setState(197);
				match(LPAREN);
				setState(198);
				expression_statement();

				          	string for_start = getNewLabel();
							string for_body = getNewLabel();
							string for_update=getNewLabel();
				          	string for_end = getNewLabel();

				          writeIntoparserLogFile(for_start + ":");
				      
				setState(200);
				expression_statement();

				          writeIntoparserLogFile("\tCMP AX, 0");
				          writeIntoparserLogFile("\tJE " + for_end);
						  writeIntoparserLogFile("\tJMP "+for_body);
						  writeIntoparserLogFile(for_update+":");
				      
				setState(202);
				expression();

						 writeIntoparserLogFile("\t JMP "+for_start);
					  
				setState(204);
				match(RPAREN);



				          writeIntoparserLogFile(for_body + ":");
				      
				setState(206);
				statement();

				          writeIntoparserLogFile("\tJMP " + for_update);
				          writeIntoparserLogFile(for_end + ":");
				      
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(209);
				match(IF);
				setState(210);
				match(LPAREN);
				setState(211);
				((StatementContext)_localctx).ex = expression();
				setState(212);
				match(RPAREN);

				          string endif_label = getNewLabel();
				          writeIntoparserLogFile("\tCMP AX, 0");
				          writeIntoparserLogFile("\tJE " + endif_label);

				      
				setState(214);
				((StatementContext)_localctx).st1 = statement();
				writeIntoparserLogFile(endif_label + ":");
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(217);
				match(IF);
				setState(218);
				match(LPAREN);
				setState(219);
				((StatementContext)_localctx).ex = expression();
				setState(220);
				match(RPAREN);

				          	string else_label = getNewLabel();
				          	string endif_label = getNewLabel();
				          	writeIntoparserLogFile("\tCMP AX, 0");
				          	writeIntoparserLogFile("\tJE " + else_label);

					  
				setState(222);
				((StatementContext)_localctx).st1 = statement();
				writeIntoparserLogFile("\tJMP " + endif_label);
				setState(224);
				match(ELSE);
				writeIntoparserLogFile(else_label + ":");
				setState(226);
				((StatementContext)_localctx).st2 = statement();




				          writeIntoparserLogFile(endif_label + ":");
				      
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(229);
				match(WHILE);

							string while_start = getNewLabel();
							string while_end = getNewLabel();
							writeIntoparserLogFile(while_start + ":");
						
				setState(231);
				match(LPAREN);
				setState(232);
				expression();
				setState(233);
				match(RPAREN);


							writeIntoparserLogFile("\tCMP AX, 0");
							writeIntoparserLogFile("\tJE " + while_end);
						
				setState(235);
				statement();

							writeIntoparserLogFile("\tJMP " + while_start);
							writeIntoparserLogFile(while_end + ":");
						
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(238);
				match(PRINTLN);
				setState(239);
				match(LPAREN);
				setState(240);
				((StatementContext)_localctx).ID = match(ID);
				setState(241);
				match(RPAREN);
				setState(242);
				match(SEMICOLON);

				          auto sym_info = symbol_table.LookUp(((StatementContext)_localctx).ID->getText());
				          if(sym_info) {
				              if(sym_info->is_global) {
				                  writeIntoparserLogFile("\tMOV AX, " + ((StatementContext)_localctx).ID->getText());
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
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(244);
				match(RETURN);
				setState(245);
				expression();
				setState(246);
				match(SEMICOLON);

						if(is_main){
				          writeIntoparserLogFile("\tPOP BP");
				          writeIntoparserLogFile("\tMOV AX, 4CH");
				          writeIntoparserLogFile("\tMOV AH, 4CH");
				           writeIntoparserLogFile("\tINT 21H");
						}else{
							writeIntoparserLogFile("\tJMP "+f_name+"_end:");
						}
				      
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_statementContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(C2105160Parser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expression_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_statement; }
	}

	public final Expression_statementContext expression_statement() throws RecognitionException {
		Expression_statementContext _localctx = new Expression_statementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expression_statement);
		try {
			setState(255);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SEMICOLON:
				enterOuterAlt(_localctx, 1);
				{
				setState(251);
				match(SEMICOLON);
				}
				break;
			case LPAREN:
			case ADDOP:
			case NOT:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				expression();
				setState(253);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public string name_line;
		public Token ID;
		public Token L;
		public ExpressionContext ex;
		public Token R;
		public TerminalNode ID() { return getToken(C2105160Parser.ID, 0); }
		public TerminalNode LTHIRD() { return getToken(C2105160Parser.LTHIRD, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RTHIRD() { return getToken(C2105160Parser.RTHIRD, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variable);
		try {
			setState(265);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				((VariableContext)_localctx).ID = match(ID);

						((VariableContext)_localctx).name_line =  ((VariableContext)_localctx).ID->getText();
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				((VariableContext)_localctx).ID = match(ID);
				setState(260);
				((VariableContext)_localctx).L = match(LTHIRD);
				setState(261);
				((VariableContext)_localctx).ex = expression();
				setState(262);
				((VariableContext)_localctx).R = match(RTHIRD);

						((VariableContext)_localctx).name_line =  ((VariableContext)_localctx).ID->getText() ;
						writeIntoparserLogFile("\tPUSH AX");
					 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public VariableContext vr;
		public Logic_expressionContext logic_expression() {
			return getRuleContext(Logic_expressionContext.class,0);
		}
		public TerminalNode ASSIGNOP() { return getToken(C2105160Parser.ASSIGNOP, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expression);
		try {
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(267);
				logic_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(268);
				((ExpressionContext)_localctx).vr = variable();
				setState(269);
				match(ASSIGNOP);
				setState(270);
				logic_expression();

							string name = ((ExpressionContext)_localctx).vr.name_line;

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

							code += " ; "+((ExpressionContext)_localctx).vr.name_line +" is assigned..\n";
								
							writeIntoparserLogFile(code);
					
							

					   
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Logic_expressionContext extends ParserRuleContext {
		public Rel_expressionContext l;
		public Token LOGICOP;
		public Rel_expressionContext r;
		public List<Rel_expressionContext> rel_expression() {
			return getRuleContexts(Rel_expressionContext.class);
		}
		public Rel_expressionContext rel_expression(int i) {
			return getRuleContext(Rel_expressionContext.class,i);
		}
		public TerminalNode LOGICOP() { return getToken(C2105160Parser.LOGICOP, 0); }
		public Logic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_expression; }
	}

	public final Logic_expressionContext logic_expression() throws RecognitionException {
		Logic_expressionContext _localctx = new Logic_expressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_logic_expression);
		try {
			setState(282);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				rel_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				((Logic_expressionContext)_localctx).l = rel_expression();
				 writeIntoparserLogFile("\tPUSH AX");
				setState(278);
				((Logic_expressionContext)_localctx).LOGICOP = match(LOGICOP);
				setState(279);
				((Logic_expressionContext)_localctx).r = rel_expression();

				             string op = ((Logic_expressionContext)_localctx).LOGICOP->getText();
							
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
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Rel_expressionContext extends ParserRuleContext {
		public Simple_expressionContext l;
		public Token RELOP;
		public Simple_expressionContext r;
		public List<Simple_expressionContext> simple_expression() {
			return getRuleContexts(Simple_expressionContext.class);
		}
		public Simple_expressionContext simple_expression(int i) {
			return getRuleContext(Simple_expressionContext.class,i);
		}
		public TerminalNode RELOP() { return getToken(C2105160Parser.RELOP, 0); }
		public Rel_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rel_expression; }
	}

	public final Rel_expressionContext rel_expression() throws RecognitionException {
		Rel_expressionContext _localctx = new Rel_expressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_rel_expression);
		try {
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(284);
				simple_expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				((Rel_expressionContext)_localctx).l = simple_expression(0);
				writeIntoparserLogFile("\tPUSH AX");
				setState(287);
				((Rel_expressionContext)_localctx).RELOP = match(RELOP);
				setState(288);
				((Rel_expressionContext)_localctx).r = simple_expression(0);

				            string op = ((Rel_expressionContext)_localctx).RELOP->getText();
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
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_expressionContext extends ParserRuleContext {
		public Token ADDOP;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Simple_expressionContext simple_expression() {
			return getRuleContext(Simple_expressionContext.class,0);
		}
		public TerminalNode ADDOP() { return getToken(C2105160Parser.ADDOP, 0); }
		public Simple_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_expression; }
	}

	public final Simple_expressionContext simple_expression() throws RecognitionException {
		return simple_expression(0);
	}

	private Simple_expressionContext simple_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Simple_expressionContext _localctx = new Simple_expressionContext(_ctx, _parentState);
		Simple_expressionContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_simple_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(294);
			term(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(304);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Simple_expressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_simple_expression);
					setState(296);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					writeIntoparserLogFile("\tPUSH AX");
					setState(298);
					((Simple_expressionContext)_localctx).ADDOP = match(ADDOP);
					setState(299);
					term(0);

					          				string operation = ((Simple_expressionContext)_localctx).ADDOP->getText();

					          				writeIntoparserLogFile("\tPOP BX");
					          				if(operation == "+") {
					          					writeIntoparserLogFile("\tADD AX, BX");
					          				}else{
					          					writeIntoparserLogFile("\tSUB BX , AX");
					          					writeIntoparserLogFile("\tMOV AX, BX");
					          				}
					          		  
					}
					} 
				}
				setState(306);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public Token MULOP;
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode MULOP() { return getToken(C2105160Parser.MULOP, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(308);
			unary_expression();
			}
			_ctx.stop = _input.LT(-1);
			setState(318);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_term);
					setState(310);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					writeIntoparserLogFile("\tPUSH AX");
					setState(312);
					((TermContext)_localctx).MULOP = match(MULOP);
					setState(313);
					unary_expression();

					          		string op = ((TermContext)_localctx).MULOP->getText();
					          		

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
					} 
				}
				setState(320);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Unary_expressionContext extends ParserRuleContext {
		public Token ADDOP;
		public TerminalNode ADDOP() { return getToken(C2105160Parser.ADDOP, 0); }
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(C2105160Parser.NOT, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public Unary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expression; }
	}

	public final Unary_expressionContext unary_expression() throws RecognitionException {
		Unary_expressionContext _localctx = new Unary_expressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_unary_expression);
		try {
			setState(328);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADDOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				((Unary_expressionContext)_localctx).ADDOP = match(ADDOP);
				setState(322);
				unary_expression();

							string op = ((Unary_expressionContext)_localctx).ADDOP->getText();
							if(op == "-") {
								writeIntoparserLogFile("\tNEG AX");
							}
						
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(325);
				match(NOT);
				setState(326);
				unary_expression();
				}
				break;
			case LPAREN:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(327);
				factor();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public VariableContext vr;
		public Token ID;
		public Argument_listContext al;
		public Token CONST_INT;
		public Token CONST_FLOAT;
		public VariableContext v;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ID() { return getToken(C2105160Parser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(C2105160Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(C2105160Parser.RPAREN, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CONST_INT() { return getToken(C2105160Parser.CONST_INT, 0); }
		public TerminalNode CONST_FLOAT() { return getToken(C2105160Parser.CONST_FLOAT, 0); }
		public TerminalNode INCOP() { return getToken(C2105160Parser.INCOP, 0); }
		public TerminalNode DECOP() { return getToken(C2105160Parser.DECOP, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_factor);
		try {
			setState(355);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(330);
				((FactorContext)_localctx).vr = variable();

						auto sym_info = symbol_table.LookUp(((FactorContext)_localctx).vr.name_line);

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
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(333);
				((FactorContext)_localctx).ID = match(ID);
				setState(334);
				match(LPAREN);
				setState(335);
				((FactorContext)_localctx).al = argument_list();
				setState(336);
				match(RPAREN);


						writeIntoparserLogFile("\tCALL "+((FactorContext)_localctx).ID->getText()+"\n");
						auto it = ((FactorContext)_localctx).al.arg_list.get_variables();
						for(auto hehe: it) {
							writeIntoparserLogFile("\tADD SP, 2");
						}
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(339);
				match(LPAREN);
				setState(340);
				expression();
				setState(341);
				match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(343);
				((FactorContext)_localctx).CONST_INT = match(CONST_INT);

						writeIntoparserLogFile("\tMOV AX, "+ ((FactorContext)_localctx).CONST_INT->getText());
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(345);
				((FactorContext)_localctx).CONST_FLOAT = match(CONST_FLOAT);

						writeIntoparserLogFile("\tMOV AX, "+ ((FactorContext)_localctx).CONST_FLOAT->getText());
					
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(347);
				((FactorContext)_localctx).v = variable();
				setState(348);
				match(INCOP);

						auto sym_info = symbol_table.LookUp(((FactorContext)_localctx).v.name_line);

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
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(351);
				((FactorContext)_localctx).vr = variable();
				setState(352);
				match(DECOP);

						auto sym_info = symbol_table.LookUp(((FactorContext)_localctx).vr.name_line);

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
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Argument_listContext extends ParserRuleContext {
		public str_list arg_list;
		public ArgumentsContext a;
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public Argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument_list; }
	}

	public final Argument_listContext argument_list() throws RecognitionException {
		Argument_listContext _localctx = new Argument_listContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_argument_list);
		try {
			setState(361);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
			case ADDOP:
			case NOT:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				((Argument_listContext)_localctx).a = arguments(0);

									_localctx.arg_list.set_variables(((Argument_listContext)_localctx).a.arg_list.get_variables());
								
				}
				break;
			case RPAREN:
				enterOuterAlt(_localctx, 2);
				{
				 
								vector<string> v;
								_localctx.arg_list.set_variables(v);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public str_list arg_list;
		public ArgumentsContext a;
		public Logic_expressionContext logic_expression() {
			return getRuleContext(Logic_expressionContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(C2105160Parser.COMMA, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		return arguments(0);
	}

	private ArgumentsContext arguments(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, _parentState);
		ArgumentsContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_arguments, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(364);
			logic_expression();

								_localctx.arg_list.add("hehe");
								writeIntoparserLogFile("\tPUSH AX");
						
			}
			_ctx.stop = _input.LT(-1);
			setState(374);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArgumentsContext(_parentctx, _parentState);
					_localctx.a = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_arguments);
					setState(367);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(368);
					match(COMMA);
					setState(369);
					logic_expression();

					          					_localctx.arg_list.set_variables(((ArgumentsContext)_localctx).a.arg_list.get_variables());
					          					_localctx.arg_list.add("hehe");
					          					writeIntoparserLogFile("\tPUSH AX");
					          				
					}
					} 
				}
				setState(376);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return program_sempred((ProgramContext)_localctx, predIndex);
		case 5:
			return parameter_list_sempred((Parameter_listContext)_localctx, predIndex);
		case 10:
			return declaration_list_sempred((Declaration_listContext)_localctx, predIndex);
		case 11:
			return statements_sempred((StatementsContext)_localctx, predIndex);
		case 18:
			return simple_expression_sempred((Simple_expressionContext)_localctx, predIndex);
		case 19:
			return term_sempred((TermContext)_localctx, predIndex);
		case 23:
			return arguments_sempred((ArgumentsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean program_sempred(ProgramContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean parameter_list_sempred(Parameter_listContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean declaration_list_sempred(Declaration_listContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean statements_sempred(StatementsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean simple_expression_sempred(Simple_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean arguments_sempred(ArgumentsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001!\u017a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001:\b\u0001\n\u0001\f\u0001=\t\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002B\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"Q\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"i\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005q\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005|\b\u0005\n\u0005\f\u0005\u007f\t\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0087"+
		"\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0092\b\u0007\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u009c"+
		"\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u00a6\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0005\n\u00b3\b\n\n\n\f\n\u00b6\t\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00bd\b\u000b"+
		"\n\u000b\f\u000b\u00c0\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00fa\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0003\r\u0100\b\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003"+
		"\u000e\u010a\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u0112\b\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u011b"+
		"\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0003\u0011\u0124\b\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0005\u0012\u012f\b\u0012\n\u0012\f\u0012\u0132\t\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0005\u0013\u013d\b\u0013\n\u0013\f\u0013\u0140"+
		"\t\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0003\u0014\u0149\b\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0164"+
		"\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u016a"+
		"\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0175\b\u0017\n"+
		"\u0017\f\u0017\u0178\t\u0017\u0001\u0017\u0000\u0007\u0002\n\u0014\u0016"+
		"$&.\u0018\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.\u0000\u0000\u018a\u00000\u0001\u0000"+
		"\u0000\u0000\u00024\u0001\u0000\u0000\u0000\u0004A\u0001\u0000\u0000\u0000"+
		"\u0006P\u0001\u0000\u0000\u0000\bh\u0001\u0000\u0000\u0000\np\u0001\u0000"+
		"\u0000\u0000\f\u0086\u0001\u0000\u0000\u0000\u000e\u0091\u0001\u0000\u0000"+
		"\u0000\u0010\u0093\u0001\u0000\u0000\u0000\u0012\u009b\u0001\u0000\u0000"+
		"\u0000\u0014\u00a5\u0001\u0000\u0000\u0000\u0016\u00b7\u0001\u0000\u0000"+
		"\u0000\u0018\u00f9\u0001\u0000\u0000\u0000\u001a\u00ff\u0001\u0000\u0000"+
		"\u0000\u001c\u0109\u0001\u0000\u0000\u0000\u001e\u0111\u0001\u0000\u0000"+
		"\u0000 \u011a\u0001\u0000\u0000\u0000\"\u0123\u0001\u0000\u0000\u0000"+
		"$\u0125\u0001\u0000\u0000\u0000&\u0133\u0001\u0000\u0000\u0000(\u0148"+
		"\u0001\u0000\u0000\u0000*\u0163\u0001\u0000\u0000\u0000,\u0169\u0001\u0000"+
		"\u0000\u0000.\u016b\u0001\u0000\u0000\u000001\u0006\u0000\uffff\uffff"+
		"\u000012\u0003\u0002\u0001\u000023\u0006\u0000\uffff\uffff\u00003\u0001"+
		"\u0001\u0000\u0000\u000045\u0006\u0001\uffff\uffff\u000056\u0003\u0004"+
		"\u0002\u00006;\u0001\u0000\u0000\u000078\n\u0002\u0000\u00008:\u0003\u0004"+
		"\u0002\u000097\u0001\u0000\u0000\u0000:=\u0001\u0000\u0000\u0000;9\u0001"+
		"\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<\u0003\u0001\u0000\u0000"+
		"\u0000=;\u0001\u0000\u0000\u0000>B\u0003\u000e\u0007\u0000?B\u0003\u0006"+
		"\u0003\u0000@B\u0003\b\u0004\u0000A>\u0001\u0000\u0000\u0000A?\u0001\u0000"+
		"\u0000\u0000A@\u0001\u0000\u0000\u0000B\u0005\u0001\u0000\u0000\u0000"+
		"CD\u0003\u0012\t\u0000DE\u0005\u001f\u0000\u0000EF\u0005\u000e\u0000\u0000"+
		"FG\u0003\n\u0005\u0000GH\u0005\u000f\u0000\u0000HI\u0005\u0014\u0000\u0000"+
		"IQ\u0001\u0000\u0000\u0000JK\u0003\u0012\t\u0000KL\u0005\u001f\u0000\u0000"+
		"LM\u0005\u000e\u0000\u0000MN\u0005\u000f\u0000\u0000NO\u0005\u0014\u0000"+
		"\u0000OQ\u0001\u0000\u0000\u0000PC\u0001\u0000\u0000\u0000PJ\u0001\u0000"+
		"\u0000\u0000Q\u0007\u0001\u0000\u0000\u0000RS\u0003\u0012\t\u0000ST\u0006"+
		"\u0004\uffff\uffff\u0000TU\u0005\u001f\u0000\u0000UV\u0006\u0004\uffff"+
		"\uffff\u0000VW\u0005\u000e\u0000\u0000WX\u0006\u0004\uffff\uffff\u0000"+
		"XY\u0003\n\u0005\u0000YZ\u0006\u0004\uffff\uffff\u0000Z[\u0005\u000f\u0000"+
		"\u0000[\\\u0003\f\u0006\u0000\\]\u0006\u0004\uffff\uffff\u0000]i\u0001"+
		"\u0000\u0000\u0000^_\u0003\u0012\t\u0000_`\u0006\u0004\uffff\uffff\u0000"+
		"`a\u0005\u001f\u0000\u0000ab\u0006\u0004\uffff\uffff\u0000bc\u0005\u000e"+
		"\u0000\u0000cd\u0006\u0004\uffff\uffff\u0000de\u0005\u000f\u0000\u0000"+
		"ef\u0003\f\u0006\u0000fg\u0006\u0004\uffff\uffff\u0000gi\u0001\u0000\u0000"+
		"\u0000hR\u0001\u0000\u0000\u0000h^\u0001\u0000\u0000\u0000i\t\u0001\u0000"+
		"\u0000\u0000jk\u0006\u0005\uffff\uffff\u0000kl\u0003\u0012\t\u0000lm\u0005"+
		"\u001f\u0000\u0000mn\u0006\u0005\uffff\uffff\u0000nq\u0001\u0000\u0000"+
		"\u0000oq\u0003\u0012\t\u0000pj\u0001\u0000\u0000\u0000po\u0001\u0000\u0000"+
		"\u0000q}\u0001\u0000\u0000\u0000rs\n\u0004\u0000\u0000st\u0005\u0015\u0000"+
		"\u0000tu\u0003\u0012\t\u0000uv\u0005\u001f\u0000\u0000vw\u0006\u0005\uffff"+
		"\uffff\u0000w|\u0001\u0000\u0000\u0000xy\n\u0003\u0000\u0000yz\u0005\u0015"+
		"\u0000\u0000z|\u0003\u0012\t\u0000{r\u0001\u0000\u0000\u0000{x\u0001\u0000"+
		"\u0000\u0000|\u007f\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~\u000b\u0001\u0000\u0000\u0000\u007f}\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0005\u0010\u0000\u0000\u0081\u0082\u0003"+
		"\u0016\u000b\u0000\u0082\u0083\u0005\u0011\u0000\u0000\u0083\u0087\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0005\u0010\u0000\u0000\u0085\u0087\u0005"+
		"\u0011\u0000\u0000\u0086\u0080\u0001\u0000\u0000\u0000\u0086\u0084\u0001"+
		"\u0000\u0000\u0000\u0087\r\u0001\u0000\u0000\u0000\u0088\u0089\u0003\u0012"+
		"\t\u0000\u0089\u008a\u0003\u0014\n\u0000\u008a\u008b\u0005\u0014\u0000"+
		"\u0000\u008b\u008c\u0006\u0007\uffff\uffff\u0000\u008c\u0092\u0001\u0000"+
		"\u0000\u0000\u008d\u008e\u0003\u0012\t\u0000\u008e\u008f\u0003\u0010\b"+
		"\u0000\u008f\u0090\u0005\u0014\u0000\u0000\u0090\u0092\u0001\u0000\u0000"+
		"\u0000\u0091\u0088\u0001\u0000\u0000\u0000\u0091\u008d\u0001\u0000\u0000"+
		"\u0000\u0092\u000f\u0001\u0000\u0000\u0000\u0093\u0094\u0006\b\uffff\uffff"+
		"\u0000\u0094\u0011\u0001\u0000\u0000\u0000\u0095\u0096\u0005\u000b\u0000"+
		"\u0000\u0096\u009c\u0006\t\uffff\uffff\u0000\u0097\u0098\u0005\f\u0000"+
		"\u0000\u0098\u009c\u0006\t\uffff\uffff\u0000\u0099\u009a\u0005\r\u0000"+
		"\u0000\u009a\u009c\u0006\t\uffff\uffff\u0000\u009b\u0095\u0001\u0000\u0000"+
		"\u0000\u009b\u0097\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000"+
		"\u0000\u009c\u0013\u0001\u0000\u0000\u0000\u009d\u009e\u0006\n\uffff\uffff"+
		"\u0000\u009e\u009f\u0005\u001f\u0000\u0000\u009f\u00a6\u0006\n\uffff\uffff"+
		"\u0000\u00a0\u00a1\u0005\u001f\u0000\u0000\u00a1\u00a2\u0005\u0012\u0000"+
		"\u0000\u00a2\u00a3\u0005 \u0000\u0000\u00a3\u00a4\u0005\u0013\u0000\u0000"+
		"\u00a4\u00a6\u0006\n\uffff\uffff\u0000\u00a5\u009d\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a0\u0001\u0000\u0000\u0000\u00a6\u00b4\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a8\n\u0004\u0000\u0000\u00a8\u00a9\u0005\u0015\u0000\u0000\u00a9"+
		"\u00aa\u0005\u001f\u0000\u0000\u00aa\u00b3\u0006\n\uffff\uffff\u0000\u00ab"+
		"\u00ac\n\u0003\u0000\u0000\u00ac\u00ad\u0005\u0015\u0000\u0000\u00ad\u00ae"+
		"\u0005\u001f\u0000\u0000\u00ae\u00af\u0005\u0012\u0000\u0000\u00af\u00b0"+
		"\u0005 \u0000\u0000\u00b0\u00b1\u0005\u0013\u0000\u0000\u00b1\u00b3\u0006"+
		"\n\uffff\uffff\u0000\u00b2\u00a7\u0001\u0000\u0000\u0000\u00b2\u00ab\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b6\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u0015\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00b8\u0006"+
		"\u000b\uffff\uffff\u0000\u00b8\u00b9\u0003\u0018\f\u0000\u00b9\u00be\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bb\n\u0001\u0000\u0000\u00bb\u00bd\u0003\u0018"+
		"\f\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000"+
		"\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000"+
		"\u0000\u00bf\u0017\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000"+
		"\u0000\u00c1\u00fa\u0003\u000e\u0007\u0000\u00c2\u00fa\u0003\u001a\r\u0000"+
		"\u00c3\u00fa\u0003\f\u0006\u0000\u00c4\u00c5\u0005\u0007\u0000\u0000\u00c5"+
		"\u00c6\u0005\u000e\u0000\u0000\u00c6\u00c7\u0003\u001a\r\u0000\u00c7\u00c8"+
		"\u0006\f\uffff\uffff\u0000\u00c8\u00c9\u0003\u001a\r\u0000\u00c9\u00ca"+
		"\u0006\f\uffff\uffff\u0000\u00ca\u00cb\u0003\u001e\u000f\u0000\u00cb\u00cc"+
		"\u0006\f\uffff\uffff\u0000\u00cc\u00cd\u0005\u000f\u0000\u0000\u00cd\u00ce"+
		"\u0006\f\uffff\uffff\u0000\u00ce\u00cf\u0003\u0018\f\u0000\u00cf\u00d0"+
		"\u0006\f\uffff\uffff\u0000\u00d0\u00fa\u0001\u0000\u0000\u0000\u00d1\u00d2"+
		"\u0005\u0005\u0000\u0000\u00d2\u00d3\u0005\u000e\u0000\u0000\u00d3\u00d4"+
		"\u0003\u001e\u000f\u0000\u00d4\u00d5\u0005\u000f\u0000\u0000\u00d5\u00d6"+
		"\u0006\f\uffff\uffff\u0000\u00d6\u00d7\u0003\u0018\f\u0000\u00d7\u00d8"+
		"\u0006\f\uffff\uffff\u0000\u00d8\u00fa\u0001\u0000\u0000\u0000\u00d9\u00da"+
		"\u0005\u0005\u0000\u0000\u00da\u00db\u0005\u000e\u0000\u0000\u00db\u00dc"+
		"\u0003\u001e\u000f\u0000\u00dc\u00dd\u0005\u000f\u0000\u0000\u00dd\u00de"+
		"\u0006\f\uffff\uffff\u0000\u00de\u00df\u0003\u0018\f\u0000\u00df\u00e0"+
		"\u0006\f\uffff\uffff\u0000\u00e0\u00e1\u0005\u0006\u0000\u0000\u00e1\u00e2"+
		"\u0006\f\uffff\uffff\u0000\u00e2\u00e3\u0003\u0018\f\u0000\u00e3\u00e4"+
		"\u0006\f\uffff\uffff\u0000\u00e4\u00fa\u0001\u0000\u0000\u0000\u00e5\u00e6"+
		"\u0005\b\u0000\u0000\u00e6\u00e7\u0006\f\uffff\uffff\u0000\u00e7\u00e8"+
		"\u0005\u000e\u0000\u0000\u00e8\u00e9\u0003\u001e\u000f\u0000\u00e9\u00ea"+
		"\u0005\u000f\u0000\u0000\u00ea\u00eb\u0006\f\uffff\uffff\u0000\u00eb\u00ec"+
		"\u0003\u0018\f\u0000\u00ec\u00ed\u0006\f\uffff\uffff\u0000\u00ed\u00fa"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005\t\u0000\u0000\u00ef\u00f0\u0005"+
		"\u000e\u0000\u0000\u00f0\u00f1\u0005\u001f\u0000\u0000\u00f1\u00f2\u0005"+
		"\u000f\u0000\u0000\u00f2\u00f3\u0005\u0014\u0000\u0000\u00f3\u00fa\u0006"+
		"\f\uffff\uffff\u0000\u00f4\u00f5\u0005\n\u0000\u0000\u00f5\u00f6\u0003"+
		"\u001e\u000f\u0000\u00f6\u00f7\u0005\u0014\u0000\u0000\u00f7\u00f8\u0006"+
		"\f\uffff\uffff\u0000\u00f8\u00fa\u0001\u0000\u0000\u0000\u00f9\u00c1\u0001"+
		"\u0000\u0000\u0000\u00f9\u00c2\u0001\u0000\u0000\u0000\u00f9\u00c3\u0001"+
		"\u0000\u0000\u0000\u00f9\u00c4\u0001\u0000\u0000\u0000\u00f9\u00d1\u0001"+
		"\u0000\u0000\u0000\u00f9\u00d9\u0001\u0000\u0000\u0000\u00f9\u00e5\u0001"+
		"\u0000\u0000\u0000\u00f9\u00ee\u0001\u0000\u0000\u0000\u00f9\u00f4\u0001"+
		"\u0000\u0000\u0000\u00fa\u0019\u0001\u0000\u0000\u0000\u00fb\u0100\u0005"+
		"\u0014\u0000\u0000\u00fc\u00fd\u0003\u001e\u000f\u0000\u00fd\u00fe\u0005"+
		"\u0014\u0000\u0000\u00fe\u0100\u0001\u0000\u0000\u0000\u00ff\u00fb\u0001"+
		"\u0000\u0000\u0000\u00ff\u00fc\u0001\u0000\u0000\u0000\u0100\u001b\u0001"+
		"\u0000\u0000\u0000\u0101\u0102\u0005\u001f\u0000\u0000\u0102\u010a\u0006"+
		"\u000e\uffff\uffff\u0000\u0103\u0104\u0005\u001f\u0000\u0000\u0104\u0105"+
		"\u0005\u0012\u0000\u0000\u0105\u0106\u0003\u001e\u000f\u0000\u0106\u0107"+
		"\u0005\u0013\u0000\u0000\u0107\u0108\u0006\u000e\uffff\uffff\u0000\u0108"+
		"\u010a\u0001\u0000\u0000\u0000\u0109\u0101\u0001\u0000\u0000\u0000\u0109"+
		"\u0103\u0001\u0000\u0000\u0000\u010a\u001d\u0001\u0000\u0000\u0000\u010b"+
		"\u0112\u0003 \u0010\u0000\u010c\u010d\u0003\u001c\u000e\u0000\u010d\u010e"+
		"\u0005\u001e\u0000\u0000\u010e\u010f\u0003 \u0010\u0000\u010f\u0110\u0006"+
		"\u000f\uffff\uffff\u0000\u0110\u0112\u0001\u0000\u0000\u0000\u0111\u010b"+
		"\u0001\u0000\u0000\u0000\u0111\u010c\u0001\u0000\u0000\u0000\u0112\u001f"+
		"\u0001\u0000\u0000\u0000\u0113\u011b\u0003\"\u0011\u0000\u0114\u0115\u0003"+
		"\"\u0011\u0000\u0115\u0116\u0006\u0010\uffff\uffff\u0000\u0116\u0117\u0005"+
		"\u001d\u0000\u0000\u0117\u0118\u0003\"\u0011\u0000\u0118\u0119\u0006\u0010"+
		"\uffff\uffff\u0000\u0119\u011b\u0001\u0000\u0000\u0000\u011a\u0113\u0001"+
		"\u0000\u0000\u0000\u011a\u0114\u0001\u0000\u0000\u0000\u011b!\u0001\u0000"+
		"\u0000\u0000\u011c\u0124\u0003$\u0012\u0000\u011d\u011e\u0003$\u0012\u0000"+
		"\u011e\u011f\u0006\u0011\uffff\uffff\u0000\u011f\u0120\u0005\u001c\u0000"+
		"\u0000\u0120\u0121\u0003$\u0012\u0000\u0121\u0122\u0006\u0011\uffff\uffff"+
		"\u0000\u0122\u0124\u0001\u0000\u0000\u0000\u0123\u011c\u0001\u0000\u0000"+
		"\u0000\u0123\u011d\u0001\u0000\u0000\u0000\u0124#\u0001\u0000\u0000\u0000"+
		"\u0125\u0126\u0006\u0012\uffff\uffff\u0000\u0126\u0127\u0003&\u0013\u0000"+
		"\u0127\u0130\u0001\u0000\u0000\u0000\u0128\u0129\n\u0001\u0000\u0000\u0129"+
		"\u012a\u0006\u0012\uffff\uffff\u0000\u012a\u012b\u0005\u0016\u0000\u0000"+
		"\u012b\u012c\u0003&\u0013\u0000\u012c\u012d\u0006\u0012\uffff\uffff\u0000"+
		"\u012d\u012f\u0001\u0000\u0000\u0000\u012e\u0128\u0001\u0000\u0000\u0000"+
		"\u012f\u0132\u0001\u0000\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000"+
		"\u0130\u0131\u0001\u0000\u0000\u0000\u0131%\u0001\u0000\u0000\u0000\u0132"+
		"\u0130\u0001\u0000\u0000\u0000\u0133\u0134\u0006\u0013\uffff\uffff\u0000"+
		"\u0134\u0135\u0003(\u0014\u0000\u0135\u013e\u0001\u0000\u0000\u0000\u0136"+
		"\u0137\n\u0001\u0000\u0000\u0137\u0138\u0006\u0013\uffff\uffff\u0000\u0138"+
		"\u0139\u0005\u0018\u0000\u0000\u0139\u013a\u0003(\u0014\u0000\u013a\u013b"+
		"\u0006\u0013\uffff\uffff\u0000\u013b\u013d\u0001\u0000\u0000\u0000\u013c"+
		"\u0136\u0001\u0000\u0000\u0000\u013d\u0140\u0001\u0000\u0000\u0000\u013e"+
		"\u013c\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f"+
		"\'\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0141\u0142"+
		"\u0005\u0016\u0000\u0000\u0142\u0143\u0003(\u0014\u0000\u0143\u0144\u0006"+
		"\u0014\uffff\uffff\u0000\u0144\u0149\u0001\u0000\u0000\u0000\u0145\u0146"+
		"\u0005\u001b\u0000\u0000\u0146\u0149\u0003(\u0014\u0000\u0147\u0149\u0003"+
		"*\u0015\u0000\u0148\u0141\u0001\u0000\u0000\u0000\u0148\u0145\u0001\u0000"+
		"\u0000\u0000\u0148\u0147\u0001\u0000\u0000\u0000\u0149)\u0001\u0000\u0000"+
		"\u0000\u014a\u014b\u0003\u001c\u000e\u0000\u014b\u014c\u0006\u0015\uffff"+
		"\uffff\u0000\u014c\u0164\u0001\u0000\u0000\u0000\u014d\u014e\u0005\u001f"+
		"\u0000\u0000\u014e\u014f\u0005\u000e\u0000\u0000\u014f\u0150\u0003,\u0016"+
		"\u0000\u0150\u0151\u0005\u000f\u0000\u0000\u0151\u0152\u0006\u0015\uffff"+
		"\uffff\u0000\u0152\u0164\u0001\u0000\u0000\u0000\u0153\u0154\u0005\u000e"+
		"\u0000\u0000\u0154\u0155\u0003\u001e\u000f\u0000\u0155\u0156\u0005\u000f"+
		"\u0000\u0000\u0156\u0164\u0001\u0000\u0000\u0000\u0157\u0158\u0005 \u0000"+
		"\u0000\u0158\u0164\u0006\u0015\uffff\uffff\u0000\u0159\u015a\u0005!\u0000"+
		"\u0000\u015a\u0164\u0006\u0015\uffff\uffff\u0000\u015b\u015c\u0003\u001c"+
		"\u000e\u0000\u015c\u015d\u0005\u0019\u0000\u0000\u015d\u015e\u0006\u0015"+
		"\uffff\uffff\u0000\u015e\u0164\u0001\u0000\u0000\u0000\u015f\u0160\u0003"+
		"\u001c\u000e\u0000\u0160\u0161\u0005\u001a\u0000\u0000\u0161\u0162\u0006"+
		"\u0015\uffff\uffff\u0000\u0162\u0164\u0001\u0000\u0000\u0000\u0163\u014a"+
		"\u0001\u0000\u0000\u0000\u0163\u014d\u0001\u0000\u0000\u0000\u0163\u0153"+
		"\u0001\u0000\u0000\u0000\u0163\u0157\u0001\u0000\u0000\u0000\u0163\u0159"+
		"\u0001\u0000\u0000\u0000\u0163\u015b\u0001\u0000\u0000\u0000\u0163\u015f"+
		"\u0001\u0000\u0000\u0000\u0164+\u0001\u0000\u0000\u0000\u0165\u0166\u0003"+
		".\u0017\u0000\u0166\u0167\u0006\u0016\uffff\uffff\u0000\u0167\u016a\u0001"+
		"\u0000\u0000\u0000\u0168\u016a\u0006\u0016\uffff\uffff\u0000\u0169\u0165"+
		"\u0001\u0000\u0000\u0000\u0169\u0168\u0001\u0000\u0000\u0000\u016a-\u0001"+
		"\u0000\u0000\u0000\u016b\u016c\u0006\u0017\uffff\uffff\u0000\u016c\u016d"+
		"\u0003 \u0010\u0000\u016d\u016e\u0006\u0017\uffff\uffff\u0000\u016e\u0176"+
		"\u0001\u0000\u0000\u0000\u016f\u0170\n\u0002\u0000\u0000\u0170\u0171\u0005"+
		"\u0015\u0000\u0000\u0171\u0172\u0003 \u0010\u0000\u0172\u0173\u0006\u0017"+
		"\uffff\uffff\u0000\u0173\u0175\u0001\u0000\u0000\u0000\u0174\u016f\u0001"+
		"\u0000\u0000\u0000\u0175\u0178\u0001\u0000\u0000\u0000\u0176\u0174\u0001"+
		"\u0000\u0000\u0000\u0176\u0177\u0001\u0000\u0000\u0000\u0177/\u0001\u0000"+
		"\u0000\u0000\u0178\u0176\u0001\u0000\u0000\u0000\u001a;APhp{}\u0086\u0091"+
		"\u009b\u00a5\u00b2\u00b4\u00be\u00f9\u00ff\u0109\u0111\u011a\u0123\u0130"+
		"\u013e\u0148\u0163\u0169\u0176";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}