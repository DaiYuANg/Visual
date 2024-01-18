package org.visual.model.codegen.processor;

import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.annotation.FxProperty;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import java.util.Collections;
import java.util.Set;

@SupportedAnnotationTypes({"org.visual.model.annotation.FxProperty"})
@SupportedSourceVersion(SourceVersion.RELEASE_21)
@AutoService(Processor.class)
@SuppressWarnings("unused")
@Slf4j
public class FxPropertyProcessor extends AbstractProcessor {
    private Messager messager;
    private Trees trees;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        log.info("启动");
        messager = processingEnv.getMessager();
        messager.printError("测试");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, @NotNull RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(FxProperty.class).forEach(element -> {
            if (element instanceof VariableElement) {

            } else {
                messager.printMessage(Diagnostic.Kind.ERROR, "@FxProperty can only be applied to fields", element);
            }
        });
        return true;
    }

    @Override
    public Set<String> getSupportedOptions() {
        return Collections.singleton("org.gradle.annotation.processing.aggregating");
    }
}
