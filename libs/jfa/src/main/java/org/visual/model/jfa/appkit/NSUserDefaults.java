/* (C)2024*/
package org.visual.model.jfa.appkit;

import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSUserDefaults extends NSObject {
    String AppleInterfaceStyle = "AppleInterfaceStyle";

    static NSUserDefaults standardUserDefaults() {
        return ObjcToJava.invokeStatic(NSUserDefaults.class, "standardUserDefaults");
    }

    String objectForKey(String key);
}
