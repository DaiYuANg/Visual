package org.visual.model.shared.util;

import java.util.concurrent.atomic.AtomicLong;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AutoIncrement {

    private static final AtomicLong counter = new AtomicLong(0);

    public static synchronized long next() {
        return counter.getAndIncrement();
    }
}
