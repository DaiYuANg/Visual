package org.visual.model.jfa.foundation;

import static org.visual.model.jfa.foundation.Foundation.invoke;

public class NSAutoreleasePool {
    private final ID myDelegate;

    public NSAutoreleasePool() {
        myDelegate = invoke(invoke("NSAutoreleasePool", "alloc"), "init");
    }

    public void drain() {
        invoke(myDelegate, "drain");
    }
}
