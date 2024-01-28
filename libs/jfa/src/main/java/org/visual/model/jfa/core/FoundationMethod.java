/* (C)2024*/
package org.visual.model.jfa.core;

import com.sun.jna.Pointer;
import java.util.function.Consumer;
import org.visual.model.jfa.appkit.NSInvocation;
import org.visual.model.jfa.appkit.NSMethodSignature;

public class FoundationMethod {
  private final Consumer<NSInvocation> handler;
  private final String methodName;
  private final Class<?> returnType;
  private final NamedType[] arguments;

  public FoundationMethod(
      Consumer<NSInvocation> handler,
      String methodName,
      Class<?> returnType,
      NamedType... arguments) {
    this.handler = handler;
    this.methodName = methodName;
    this.returnType = returnType;
    this.arguments = arguments;
  }

  public NSMethodSignature getMethodSignature() {
    return NSMethodSignature.signatureWithObjCTypes(
        TypeEncoding.toType(returnType) + "@:" + TypeEncoding.encode(arguments));
  }

  public Pointer getSelector() {
    return Selector.forNamedTypes(methodName, arguments);
  }

  public void invoke(NSInvocation invocation) {
    handler.accept(invocation);
  }
}
