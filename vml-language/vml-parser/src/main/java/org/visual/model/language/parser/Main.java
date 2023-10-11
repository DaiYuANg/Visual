package org.visual.model.language.parser;

import lombok.val;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {
	public static void main(String[] args) {
		val lexer = new org.visual.model.language.parser.antlr.VisualModelLexer(CharStreams.fromString("hello parrt"));
		val tokens = new CommonTokenStream(lexer);
		val parser = new org.visual.model.language.parser.antlr.VisualModelParser(tokens);
		System.err.println(parser.x1().toStringTree());
		// ParseTree tree = parser.();
		// System.out.println(tree.toStringTree(parser));

	}
}
