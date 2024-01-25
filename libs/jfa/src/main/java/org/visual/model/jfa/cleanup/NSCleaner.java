/* (C)2024*/
package org.visual.model.jfa.cleanup;

import java.lang.ref.Cleaner;

import lombok.NoArgsConstructor;
import org.visual.model.jfa.appkit.NSObject;
import org.visual.model.jfa.core.FoundationCallback;
import org.visual.model.jfa.core.FoundationCallbackRegistry;
import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.Foundation;
import org.visual.model.jfa.foundation.ID;

@SuppressWarnings("unused")
@NoArgsConstructor
public final class NSCleaner {
    public static final Cleaner CLEANER = Cleaner.create();

    public static void register(Object obj, NSObject nsObject) {
        CLEANER.register(obj, () -> Foundation.cfRelease(ObjcToJava.toID(nsObject)));
    }

    public static void register(Object obj, ID id) {
        CLEANER.register(obj, () -> Foundation.invoke(id, "dealloc"));
    }

    public static void register(Object obj, FoundationCallback callback) {
        CLEANER.register(obj, () -> FoundationCallbackRegistry.unregister(callback));
    }

    public static void register(Object obj, Runnable runnable) {
        CLEANER.register(obj, runnable);
    }
}
