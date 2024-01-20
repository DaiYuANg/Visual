/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.Foundation;
import org.visual.model.jfa.foundation.ID;

public interface NSBundle extends NSObject {
    static NSBundle mainBundle() {
        return ObjcToJava.invokeStatic(NSBundle.class, "mainBundle");
    }

    static NSBundle bundleWithPath(String path) {
        return ObjcToJava.invokeStatic(NSBundle.class, "bundleWithPath:", Foundation.nsString(path));
    }

    NSString bundleIdentifier();

    NSBundle initWithURL(NSURL url);

    NSBundle initWithPath(String url);

    NSURL bundleURL();

    String bundlePath();

    ID classNamed(String className);

    boolean load();
}
