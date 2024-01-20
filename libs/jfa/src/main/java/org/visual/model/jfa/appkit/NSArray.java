/* (C)2024*/
package org.visual.model.jfa.appkit;

import java.util.Collection;
import org.visual.model.jfa.core.ObjcToJava;
import org.visual.model.jfa.foundation.ID;
import org.visual.model.jfa.foundation.VarArgs;

@SuppressWarnings("unused")
public interface NSArray<T> extends NSObject {
    @SuppressWarnings("unchecked")
    static <A> NSArray<A> alloc() {
        return ObjcToJava.alloc(NSArray.class);
    }

    @SuppressWarnings("unchecked")
    static <A> NSArray<A> of(A... values) {
        return NSArray.<A>alloc().initWithObjects(VarArgs.of(values));
    }

    static <A> NSArray<A> of(Collection<A> values) {
        return NSArray.<A>alloc().initWithObjects(VarArgs.of(values));
    }

    NSArray<T> initWithObjects(VarArgs<T> values);

    int count();

    ID objectAtIndex(int index);
}
