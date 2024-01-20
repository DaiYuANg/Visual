/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSNotification extends NSObject {
    static NSNotification alloc() {
        return ObjcToJava.alloc(NSNotification.class);
    }

    String name();
}
