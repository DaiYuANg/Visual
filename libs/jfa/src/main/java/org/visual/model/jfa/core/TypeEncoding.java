/* (C)2024*/
package org.visual.model.jfa.core;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class TypeEncoding {

    public static String encode(Class<?>... classes) {
        return Arrays.stream(classes).map(TypeEncoding::toType).collect(Collectors.joining());
    }

    public static String encode(NamedType... namedTypes) {
        return Arrays.stream(namedTypes)
                .map(NamedType::getType)
                .map(TypeEncoding::toType)
                .collect(Collectors.joining());
    }

    public static @NotNull String toType(Class<?> clazz) {
        if (clazz == Void.class || clazz == void.class) {
            return "v";
        } else if (int.class == clazz) {
            return "i";
        } else if (long.class == clazz) {
            return "l";
        } else if (Object.class.isAssignableFrom(clazz)) {
            return "@";
        } else if (Boolean.class == clazz || boolean.class == clazz || char.class == clazz) {
            return "c";
        }

        return "?";
    }
}
