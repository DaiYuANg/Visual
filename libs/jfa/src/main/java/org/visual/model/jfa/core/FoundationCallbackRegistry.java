/* (C)2024*/
package org.visual.model.jfa.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.visual.model.jfa.foundation.Foundation;
import org.visual.model.jfa.foundation.ID;

public final class FoundationCallbackRegistry {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<ID, Object> REFERENCE_MAP = new ConcurrentHashMap<>();

    @Contract("_ -> new")
    public static @NotNull FoundationCallback registerCallback(Consumer<ID> callback) {
        ID objcObject = JavaToObjc.map(callback, Consumer.class);
        REFERENCE_MAP.put(objcObject, callback);
        return new FoundationCallback(objcObject, Foundation.createSelector("accept:"));
    }

    public static void unregister(@NotNull FoundationCallback callback) {
        REFERENCE_MAP.remove(callback.getTarget());
    }
}
