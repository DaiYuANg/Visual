package org.visual.model.compiler;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;

public class Main {
    public static void main(String[] args) {
        new org.visual.model.compiler.SimpleExpressionParser(new TokenStream() {
            @Override
            public Token LT(int k) {
                return null;
            }

            @Override
            public Token get(int index) {
                return null;
            }

            @Override
            public TokenSource getTokenSource() {
                return null;
            }

            @Override
            public String getText(Interval interval) {
                return null;
            }

            @Override
            public String getText() {
                return null;
            }

            @Override
            public String getText(RuleContext ctx) {
                return null;
            }

            @Override
            public String getText(Token start, Token stop) {
                return null;
            }

            @Override
            public void consume() {

            }

            @Override
            public int LA(int i) {
                return 0;
            }

            @Override
            public int mark() {
                return 0;
            }

            @Override
            public void release(int marker) {

            }

            @Override
            public int index() {
                return 0;
            }

            @Override
            public void seek(int index) {

            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public String getSourceName() {
                return null;
            }
        });
    }
}
