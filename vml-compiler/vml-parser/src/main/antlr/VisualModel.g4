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

expression : additiveExpression shape;

additiveExpression : multiplicativeExpression ( (ADD | SUB) multiplicativeExpression )*;

multiplicativeExpression : primaryExpression ( (MUL | DIV) primaryExpression )*;

primaryExpression : LPAREN expression RPAREN | NUMBER;

shape : circle | rectangle | line;
circle : 'circle' '(' radius ')';
rectangle : 'rectangle' '(' width ',' height ')';
line : 'line' '(' x1 ',' y1 ',' x2 ',' y2 ')';
radius : INT;
width : INT;
height : INT;
x1 : INT;
y1 : INT;
x2 : INT;
y2 : INT;
INT : [0-9]+;

