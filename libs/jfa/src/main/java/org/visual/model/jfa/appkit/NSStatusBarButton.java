/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSStatusBarButton extends NSButton {
  static NSStatusBarButton alloc() {
    return ObjcToJava.alloc(NSStatusBarButton.class);
  }
}
