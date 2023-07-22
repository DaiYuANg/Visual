grammar VisualModel;
@header {
    package org.visual.model.compiler;
}

LPAREN : '(';
RPAREN : ')';
ADD    : '+';
SUB    : '-';
MUL    : '*';
DIV    : '/';
NUMBER : [0-9]+;

WS : [ \t\r\n]+ -> skip;

expression : additiveExpression;

additiveExpression : multiplicativeExpression ( (ADD | SUB) multiplicativeExpression )*;

multiplicativeExpression : primaryExpression ( (MUL | DIV) primaryExpression )*;

primaryExpression : LPAREN expression RPAREN | NUMBER;
