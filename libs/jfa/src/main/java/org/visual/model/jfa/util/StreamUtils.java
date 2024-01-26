/* (C)2024*/
package org.visual.model.jfa.util;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public final class StreamUtils {
    private StreamUtils() {
    }

    public static <T> Stream<IndexedValue<T>> zipWithIndex(T @NotNull [] values) {
        return IntStream.range(0, values.length).mapToObj(i -> new IndexedValue<>(i, values[i]));
    }

    public record IndexedValue<T>(int index, T value) {
    }
}
