package org.visual.model.codegen.processor;

import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.*;
import org.visual.model.annotation.FxProperty;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import java.util.Collections;
import java.util.Set;

/**
 * @author
 */
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
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, @NotNull RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(FxProperty.class).forEach(element -> {
            if (element instanceof VariableElement) {
                String className = ((TypeElement) element.getEnclosingElement()).getQualifiedName().toString();
                String fieldName = element.getSimpleName().toString();

            } else {
                messager.printMessage(Diagnostic.Kind.NOTE, "@FxProperty can only be applied to fields", element);
            }
        });
        return true;
    }

    @SneakyThrows
    private void modifyBytecode(String className, String fieldName) {
        ClassReader classReader = new ClassReader(className);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        // 创建自定义的 ClassVisitor，重写 visitField 方法
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM9, classWriter) {
            @Override
            public FieldVisitor visitField(int access, @NotNull String name, String descriptor, String signature, Object value) {
                if (name.equals(fieldName)) {
                    // 在这里可以添加字节码操作，例如修改字段的访问修饰符
                    access |= Opcodes.ACC_PRIVATE; // 修改为私有字段
                }
                return super.visitField(access, name, descriptor, signature, value);
            }
        };

        // 通过 ClassReader 和 ClassVisitor 进行字节码修改
        classReader.accept(classVisitor, ClassReader.SKIP_FRAMES);

        // 获取修改后的字节码
        byte[] modifiedClass = classWriter.toByteArray();
        // 可以将修改后的字节码保存到文件或者重新加载类
        // 例如，使用 Instrumentation.redefineClasses 方法重新定义类
        // instrumentation.redefineClasses(new ClassDefinition(Class.forName(className), modifiedClass));
    }

    @Override
    public Set<String> getSupportedOptions() {
        return Collections.singleton("org.gradle.annotation.processing.aggregating");
    }
}
