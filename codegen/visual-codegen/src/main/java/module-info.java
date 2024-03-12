module org.visual.codegen {
  requires java.compiler;
  requires static io.avaje.prism;
  requires net.bytebuddy;
  requires static lombok;

  exports org.visual.codegen.annotation;

  provides javax.annotation.processing.Processor with
      org.visual.codegen.processor.FxPropertyProcessor,
      org.visual.codegen.processor.LazyProcessor;
}
