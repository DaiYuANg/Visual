/* (C)2024*/
package org.visual.model.jfa.appkit;

import com.sun.jna.ptr.ByReference;
import org.visual.model.jfa.annotation.NamedArg;
import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.ID;

@SuppressWarnings("unused")
public interface NSInvocation extends NSObject {
  static NSInvocation alloc() {
    return ObjcToJava.alloc(NSInvocation.class);
  }

  ID selector();

  NSObject target();

  void setTarget(NSObject target);

  void invoke();

  void invokeWithTarget(NSObject target);

  void setReturnValue(ByReference retLoc);

  void getArgument(ByReference argumentLocation, @NamedArg("atIndex") int idx);

  NSMethodSignature methodSignature();

  void setArgument(ByReference arg, @NamedArg("atIndex") int idx);
}
