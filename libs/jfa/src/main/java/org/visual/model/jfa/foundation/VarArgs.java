/* (C)2024*/
package org.visual.model.jfa.foundation;

import java.util.Arrays;
import java.util.Collection;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
public class VarArgs<T> {
    private final Collection<T> args;

    private VarArgs(Collection<T> args) {
        this.args = args;
    }

    @Contract("_ -> new")
    @SafeVarargs
    public static <A> @NotNull VarArgs<A> of(A... values) {
        return of(Arrays.asList(values));
    }

    @Contract(value = "_ -> new", pure = true)
    public static <A> @NotNull VarArgs<A> of(Collection<A> values) {
        return new VarArgs<>(values);
    }
}
