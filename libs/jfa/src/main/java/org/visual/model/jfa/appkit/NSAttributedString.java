/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.annotation.NamedArg;
import org.visual.model.jfa.core.ObjcToJava;

public interface NSAttributedString extends NSObject {
  static NSAttributedString alloc() {
    return ObjcToJava.alloc(NSAttributedString.class);
  }

  NSAttributedString initWithString(NSString str);

  NSAttributedString initWithString(
      NSString str,
      @NamedArg("attributes") NSDictionary<NSAttributedStringKey, ? extends NSObject> attrs);

  NSString string();

  int length();
}
