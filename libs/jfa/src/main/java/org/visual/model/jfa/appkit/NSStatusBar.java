/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.CGFloat;

@SuppressWarnings("unused")
public interface NSStatusBar extends NSObject {
    CGFloat NSSquareStatusItemLength = new CGFloat(-2.0);

    static NSStatusBar systemStatusBar() {
        return ObjcToJava.invokeStatic(NSStatusBar.class, "systemStatusBar");
    }

    NSStatusItem statusItemWithLength(CGFloat length);

    void removeStatusItem(NSStatusItem item);
}
