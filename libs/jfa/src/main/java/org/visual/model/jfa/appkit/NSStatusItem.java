/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSStatusItem extends NSObject {
  static NSStatusItem alloc() {
    return ObjcToJava.alloc(NSStatusItem.class);
  }

  NSMenu menu();

  void setMenu(NSMenu menu);

  NSStatusBarButton button();
}
