/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSWindow extends NSObject {
    static NSWindow alloc() {
        return ObjcToJava.alloc(NSWindow.class);
    }

    NSView contentView();

    NSAppearance appearance();

    void setAppearance(NSAppearance appearance);
}
