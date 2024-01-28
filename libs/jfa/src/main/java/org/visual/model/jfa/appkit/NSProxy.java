/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.ID;

@SuppressWarnings("unused")
public interface NSProxy extends NSObject {
  static NSProxy alloc() {
    return ObjcToJava.alloc(NSProxy.class);
  }

  void forwardInvocation(NSInvocation invocation);

  NSMethodSignature methodSignatureForSelector(ID sel);
}
