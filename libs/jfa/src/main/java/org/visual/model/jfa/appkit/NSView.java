/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSView extends NSObject {
  static NSView alloc() {
    return ObjcToJava.alloc(NSView.class);
  }
}
