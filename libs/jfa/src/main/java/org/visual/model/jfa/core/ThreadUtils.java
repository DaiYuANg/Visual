/* (C)2024*/
package org.visual.model.jfa.core;

import lombok.experimental.UtilityClass;
import org.visual.model.jfa.foundation.Foundation;
import org.visual.model.jfa.foundation.ID;

@SuppressWarnings("unused")
@UtilityClass
public final class ThreadUtils {

    public static void dispatch_sync(Runnable runnable) {
        dispatch(runnable, true);
    }

    public static void dispatch_async(Runnable runnable) {
        dispatch(runnable, false);
    }

    private static void dispatch(Runnable runnable, boolean waitUntilDone) {
        ID objcObject = JavaToObjc.map(runnable, Runnable.class);

        Foundation.invoke(
                objcObject,
                "performSelectorOnMainThread:withObject:waitUntilDone:",
                Foundation.createSelector("run"),
                ID.NIL,
                waitUntilDone);
    }
}
