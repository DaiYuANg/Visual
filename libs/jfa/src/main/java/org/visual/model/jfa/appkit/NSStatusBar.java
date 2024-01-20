/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.Foundation;

@SuppressWarnings("unused")
public interface NSStatusBar extends NSObject {
    Foundation.CGFloat NSSquareStatusItemLength = new Foundation.CGFloat(-2.0);

    static NSStatusBar systemStatusBar() {
        return ObjcToJava.invokeStatic(NSStatusBar.class, "systemStatusBar");
    }

    NSStatusItem statusItemWithLength(Foundation.CGFloat length);

    void removeStatusItem(NSStatusItem item);
}
