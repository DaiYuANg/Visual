/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSURL extends NSObject {
  static NSURL alloc() {
    return ObjcToJava.alloc(NSURL.class);
  }

  NSURL initWithString(String url);
}
