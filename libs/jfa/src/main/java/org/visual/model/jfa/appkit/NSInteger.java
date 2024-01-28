/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;

public interface NSInteger extends NSNumber {
  static NSInteger alloc() {
    return ObjcToJava.alloc(NSInteger.class);
  }
}
