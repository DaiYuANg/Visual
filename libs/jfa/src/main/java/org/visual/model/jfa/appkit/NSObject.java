/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.annotation.NamedArg;
import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.ID;

@SuppressWarnings({"unused", "EmptyMethod"})
public interface NSObject {
  static NSObject alloc() {
    return ObjcToJava.alloc(NSObject.class);
  }

  String description();

  String className();

  void dealloc();

  NSMethodSignature methodSignatureForSelector(ID selector);

  boolean respondsToSelector(ID aSelector);

  void performSelectorOnMainThread(
      ID aSelector, @NamedArg("withObject") ID arg, @NamedArg("waitUntilDone") boolean wait);
}
