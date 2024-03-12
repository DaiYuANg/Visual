package org.visual.codegen.processor;

import io.avaje.prism.GenerateAPContext;
import io.avaje.prism.GeneratePrism;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import org.visual.codegen.annotation.Lazy;

@SuppressWarnings("unused")
@GeneratePrism(Lazy.class)
@GenerateAPContext
@SupportedAnnotationTypes({"org.visual.codegen.api.Lazy"})
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class LazyProcessor extends AbstractProcessor {
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    return false;
  }
}
