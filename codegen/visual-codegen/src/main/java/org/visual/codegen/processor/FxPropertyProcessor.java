package org.visual.codegen.processor;

import io.avaje.prism.GenerateAPContext;
import io.avaje.prism.GeneratePrism;
import io.avaje.prism.GenerateUtils;
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import lombok.NonNull;
import lombok.val;
import org.visual.codegen.annotation.FxProperty;

@SuppressWarnings("unused")
@GenerateAPContext
@GeneratePrism(FxProperty.class)
@SupportedAnnotationTypes({"org.visual.codegen.api.FxProperty"})
@SupportedSourceVersion(SourceVersion.RELEASE_21)
@GenerateUtils
public class FxPropertyProcessor extends AbstractProcessor {
  @Override
  public synchronized void init(ProcessingEnvironment env) {
    super.init(env);
    APContext.init(env);
  }

  @Override
  public boolean process(
      Set<? extends TypeElement> annotations, @NonNull RoundEnvironment roundEnv) {
    val elements = roundEnv.getElementsAnnotatedWith(FxProperty.class);
    elements.stream()
        .parallel()
        .peek(
            a -> {
              APContext.logNote("Get Element" + a.getClass());
            })
        .filter(e -> e.getKind().isField())
        .forEach(this::processElement);
    return true;
  }

  private void processElement(@NonNull Element element) {
    APContext.logNote("Get Element" + element.getClass());
  }
}
