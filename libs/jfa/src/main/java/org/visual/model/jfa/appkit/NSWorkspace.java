/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSWorkspace extends NSObject {
    static NSWorkspace sharedWorkspace() {
        return ObjcToJava.invokeStatic(NSWorkspace.class, "sharedWorkspace");
    }

    void hideOtherApplications();
}
