/**
  * This file is part of CorrectOz - a feedback provider for the Oz language.
  * See the LICENSE and COPYRIGHTS files for
  * more informations concerning the license and the copyrights
  *
  * Author : Magrofuoco Nathan and Paquot Arthur
  * Supervisor : Van Roy Peter
  * Universite Catholique de Louvain
  * Year : 2015-2016
  * Git : bitbucket.org/paquota/correctoz
  */

grammar OzGrammar;

/**
 *  - This grammar was translated, adapted and relaxed from the original grammar
 *  found in [1] for ANTLR4 and CorrectOz convenience
 *  - The translation respects the following translation rules:
 *	(book)   (english)     (g4)
 *	  []     '0 OR 1'		    ?
 *	  {}     '0 OR more' 	  *
 *	  {}+    '1 OR more'		+
 *	  ()     'grouped words'
 *
 *  [1] Peter Van Roy and Seif Haridi. Concepts, Techniques, and Models of
 *  Computer Programming. The MIT Press, 1st Edition, 2004.
 **/

// PARSER RULES

initRule
  : interStatement EOF
  ;

// Interactive statements, see table C.1, p834
interStatement
  : statement
  | 'declare' declarationPart+ interStatement?
  | 'declare' declarationPart+ 'in' interStatement
  ;

// Statements & expressions, see table C.2, p834
statement
  : nestConStatement
  | nestDecVariable
  | 'skip'
  | ';'
  | statement statement
  | 'declare' statement
  ;

expression
  : '('+ expression ')'+
  | nestConExpression
  | nestDecAnonym
  | unaryOp expression
  | expression '--'
  | expression '++'
  | expression pointOp expression
  | expression arithOpFirst expression
  | expression arithOpSecond expression
  | expression arithOpThird expression
  | expression consBinOp expression
  | expression evalBinOp expression
  | '$'
  | term
  | 'self'
  | '{'+ expression expression+ '}'+
  | '{'+ expression expression* '}'+
  ;

inStatement
  : (declarationPart+ 'in')? statement
  | (declarationPart+ 'in')? expression
  ;

inExpression
  : (declarationPart+ 'in')? statement? expression
  | (declarationPart+ 'in')? statement
  ;

// Nestable constructs, see table C.3, p835
nestConStatement
  : 'local' declarationPart+ 'in'? statement* 'end'?
  | '(' inStatement ')'
  | 'if' expression 'then'? inStatement
    ('elseif' expression 'then'? inStatement)*
    ('else' inStatement)?
    'end'?
  | 'case' expression 'of' pattern? ('andthen' expression)? 'then'? inStatement //TODO Erroneous : see http://mozart2.org/mozart-v1/doc-1.4.0/notation/node3.html#chapter.context-free (case statement clause)
    ('[]'? pattern? ('andthen' expression)? 'then'? inStatement)*
    ('else' inStatement)?
    'end'?
  | 'for' (loopDec)+ 'do'? inStatement 'end'?
  | 'try' inStatement
    ('catch' pattern 'then' inStatement
    '[]' pattern 'then' inStatement
    )?
    ('finally' inStatement)?
    'end'
  | 'raise' inExpression 'end'
  | 'thread' inStatement 'end'? //TODO why is 'end' facultative here ?
  | 'lock' (expression 'then')? inStatement 'end'
  | '{'+ expression expression* '}'+
  | expression ('='|':='|',') expression
  ;

nestConExpression
  : 'local' declarationPart+ 'in'? statement? expression 'end'?
  | '(' inExpression ')'
  | 'if' expression 'then'? inExpression
    ('elseif' expression 'then'? inExpression)*
    ('else' inExpression)?
    'end'?
  | 'case' expression 'of'? pattern ('andthen' expression)? 'then'? inExpression
    ('[]' pattern ('andthen' expression)? 'then'? inExpression)*
    ('else' inExpression)?
    'end'?
  | 'for' (loopDec)+ 'do'? inExpression 'end'?
  | 'try' inExpression
    ('catch' pattern 'then' inExpression
    '[]' pattern 'then' inExpression
    )?
    ('finally' inStatement)?
    'end'
  | 'raise' inExpression 'end'
  | 'thread' inExpression 'end'?
  | 'lock' (expression 'then')? inExpression 'end'
  ;

// Nestable declarations, see table C.4, p835
nestDecVariable
  : 'proc' '{'? (Variable | Atom) pattern* '}'? inStatement 'end'?
  | 'fun' 'lazy'? '{'? (Variable | Atom) pattern* '}'? inExpression 'end'?
  | 'fun' 'lazy'? '(' (Variable | Atom) pattern* ')' inExpression 'end'?
  | 'functor' Variable?
    ('import' (Variable ('at' Atom)?
              | Variable '(' (Atom | Int (':' Variable)?)+ ')'
              )+
    )?
    ('export' ((Atom | Int ':')? Variable)+ )?
    'define' declarationPart+ ('in' statement)? 'end'
  | 'class' (Variable | Atom) classDescriptor*
    ('meth' methHead ('=' Variable)? (inExpression | inStatement) 'end'?)*
    'end'?
  ;

nestDecAnonym
  : 'proc' '{'? '$' pattern* '}'? inStatement 'end'?
  | 'fun' 'lazy'? '{'? '$' pattern* '}'? inExpression 'end'?
  | 'fun' 'lazy'? '(' '$' pattern* ')' inExpression 'end'?
  | 'functor' Variable
    ('import' (Variable ('at' Atom)?
              | Variable '(' (Atom | Int (':' Variable)?)+ ')'
              )+
    )?
    ('export' ((Atom | Int ':')? Variable)+ )?
    'define' declarationPart+ ('in' statement)? 'end'
  | 'class'? '$' classDescriptor*
    ('meth'? methHead ('=' Variable)? (inExpression | inStatement) 'end'?)*
    'end'?
  ;

// Terms & patterns, see table C.5, p836
term
  : '!'? Variable
  | label '(' ((feature ':')? expression)* ')'
  | Int
  | Float
  | Char
  | Atom
  | String
  | 'unit'
  | 'true'
  | 'false'
  | '[' expression* ']'?
  | 'nil'
  | 'leaf'
  | 'bottom'
  | '_'
  ;

pattern
  : Atom
  | Int
  | Float
  | Char
  | '!'? Variable
  | String
  | 'unit'
  | 'true'
  | 'false'
  | label '(' ((feature ':')? pattern)* '...'? ')'
  | pattern consBinOp pattern
  | ('(' pattern ')')+
  | '(' pattern ')'
  | '[' pattern* ']'
  | 'nil'
  | 'leaf'
  | 'bottom'
  | '_'
  ;

// Other nonterminals, see table C.6, p837
declarationPart
  : pattern '=' expression
  | Variable
  | Atom
  | statement
  ;

loopDec
  : Variable+ 'in'? expression ';' expression ';' expression
  | Variable+ 'in'? expression ('..' expression)? (';' expression)?
  | Variable 'in'? expression ';' expression ';' expression
  | Variable 'in'? expression ('..' expression)? (';' expression)?
  | 'break' ':'? Variable
  | 'continue' ':'? Variable
  | 'return' ':'? Variable
  | 'default' ':'? expression
  | 'collect' ':'? Variable
  ;

unaryOp
  : '~'
  | '@'
  | '!!'
  | 'return'
  ;

consBinOp
  : '#'
  | '|'
  ;

pointOp
  : '.'
  ;

arithOpFirst
  : '*'
  | 'div'
  | '/'
  | 'mod'
  | '^'
  | '**'
  | 'modulo'
  ;

arithOpSecond
  : '+'
  | '-'
  ;

arithOpThird
  : '=='
  | '\\='
  | '<'
  | '=<'
  | '=>' //not acceptable
  | '>'
  | '>='
  | '<=' //not acceptable
  | '!=' //TODO what is this ?
  ;

evalBinOp
  : 'andthen'
  | 'orelse'
  | 'and'
  | 'And'
  | 'or'
  | 'Or'
  | '&&'
  | '||'
	| ':='
	| ','
	| '='
	| '!='
	| '::'
	| '=:'
	| '\\=:'
	| '=<:'
	;

label
  : 'unit'
  | 'true'
  | 'false'
  | Variable
  | Atom
  ;

feature
  : 'unit'
  | 'true'
  | 'false'
  | Variable
  | Atom
  | Int
  | 'leaf'
  | 'bottom'
  | 'nil'
  ;

classDescriptor
  : 'from' expression+
  | 'prop' expression+
  | 'attr' attrInit+
  ;

attrInit
  : ('!'? Variable | Atom | 'unit' | 'true' | 'false') (':' expression)?
  ;

methHead
  : ('!'? Variable | Atom | 'unit' | 'true' | 'false')
    ('(' methArg* '...'? ')')?
    ('=' Variable)?
  ;

methArg
  : (feature ':')? (Variable | '_' | '$') ('<=' expression)?
  ;

// LEXER RULES

// Keywords, see table C.8, p839
ANDTHEN 	: 'andthen';
AT 			  : 'at';
ATTR 		  : 'attr';
BREAK 		: 'break';
CASE      : 'case';
CATCH     : 'catch';
CHOICE		: 'choice';
CLASS     : 'class';
COLLECT		: 'collect';
COND      : 'cond';
CONTINUE	: 'continue';
DECLARE		: 'declare';
DEFAULT 	: 'default';
DEFINE 		: 'define';
DIS 		  : 'dis';
DIV 	 	  : 'div';
DO        : 'do';
ELSE		  : 'else';
ELSECASE 	: 'elsecase';
ELSEIF		: 'elseif';
ELSEOF		: 'elseof';
END			  : 'end';
EXPORT		: 'export';
FAIL		  : 'fail';
FALSE 		: 'false';
FEAT      : 'feat';
FINALLY 	: 'finally';
FOR 		  : 'for';
FROM		  : 'from';
FUN			  : 'fun';
FUNCTOR		: 'functor';
IF        : 'if';
IMPORT		: 'import';
IN			  : 'in';
LAZY		  : 'lazy';
LOCAL     : 'local';
LOCK      : 'lock';
METH      : 'meth';
MOD       : 'mod';
NOT 		  : 'not';
OF        : 'of';
OR        : 'or';
ORELSE		: 'orelse';
OTHERWISE	: 'otherwise';
PREPARE 	: 'prepare';
PROC		  : 'proc';
PROP		  : 'prop';
RAISE     : 'raise';
REQUIRE 	: 'require';
RETURN 		: 'return';
SELF      : 'self';
SKIPPY		: 'skip';
THEN      : 'then';
THREAD		: 'thread';
TRUE		  : 'true';
TRY			  : 'try';
UNIT 	  	: 'unit';
WHILE     : 'while';

Variable
  : UppercaseChar AlphanumericChar*
  | '`' (AlphanumericChar | SpecialChar)* '`'
  ;

Atom
  : LowercaseChar AlphanumericChar*
  | '\'' (AlphanumericChar | SpecialChar)* '\''
  ;

String
  : '"' (AlphanumericChar | SpecialChar)* '"'
  ;

Int
  : '~'? Digit
  | '~'? NonZeroDigit Digit*
  | '~'? '0' OctalDigit+
  | '~'? ('0x' | '0X') HexDigit+
  | '~'? ('0b' | '0B') BinDigit+
  ;

Char
  : '&' (AlphanumericChar | SpecialChar)
  ;

Float
  : '~'? Digit+ '.' (Digit+|(('e' | 'E') '~'? Digit+)) //TODO erroneous imho
  ;

fragment SpecialChar
  : '_'
  | '+'
  | '-'
  | '*'
  | '/'
  | '='
  | ':'
  | '\\'
  | '>'
  | '<'
  | '.'
  | ','
  | '!'
  | '?'
  | '#'
  | '@'
  | 'ÃƒÂ¨'
  | 'ÃƒÂ©'
  | 'Ãƒ '
  | '&'
  | ' '
  ;

fragment AlphanumericChar
  : UppercaseChar
  | LowercaseChar
  | Digit
  ;

fragment LowercaseChar
  : [a-z]
  ;

fragment UppercaseChar
  : [A-Z]
  ;

fragment Digit
  : [0-9]
  ;

fragment NonZeroDigit
  : [1-9]
  ;

fragment OctalDigit
  : [0-7]
  ;

fragment HexDigit
  : [0-9a-fA-F]
  ;

fragment BinDigit
  : [0-1]
  ;

WS
  : [ \t\r\n]+ -> skip
	;

COMMENT
  : '%' ~[\r\n]* -> skip
  ;

MULTILINE_COMMENT
  : '/*' .*? '*/' -> skip
  ;
