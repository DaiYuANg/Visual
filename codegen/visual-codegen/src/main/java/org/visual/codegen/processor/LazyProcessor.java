package org.visual.codegen.processor;

import com.google.auto.service.AutoService;
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
@SuppressWarnings("unused")
@SupportedAnnotationTypes({"org.visual.codegen.api.Lazy"})
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class LazyProcessor extends AbstractProcessor {
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    return false;
  }
}
