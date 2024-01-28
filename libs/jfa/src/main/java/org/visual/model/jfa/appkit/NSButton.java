/* (C)2024*/
package org.visual.model.jfa.appkit;

import com.sun.jna.Pointer;
import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.ID;

@SuppressWarnings("unused")
public interface NSButton extends NSObject {
  static NSButton alloc() {
    return ObjcToJava.alloc(NSButton.class);
  }

  NSImage image();

  void setImage(NSImage image);

  Pointer action();

  void setAction(Pointer selector);

  ID target();

  void setTarget(ID target);
}
