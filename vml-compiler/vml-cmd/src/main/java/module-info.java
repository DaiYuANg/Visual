module org.visual.model.compiler {
	opens org.visual.model.language.cmd;
	requires static lombok;
	requires org.slf4j;
	requires info.picocli;
}
