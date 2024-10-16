// ExprLexer.g4
lexer grammar ExprLexer_og;
                    
Number: Digits ('.' Digits)? ;

fragment Digits: ([0-9])+ ;

PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
LPAREN: '(';
RPAREN: ')';

WS: [ \t\r\n]+ -> channel(HIDDEN);

