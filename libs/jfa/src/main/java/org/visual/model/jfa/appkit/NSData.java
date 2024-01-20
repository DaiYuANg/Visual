/* (C)2024*/
package org.visual.model.jfa.appkit;

import com.sun.jna.Memory;
import org.visual.model.jfa.annotation.NamedArg;
import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSData extends NSObject {
    static NSData alloc() {
        return ObjcToJava.alloc(NSData.class);
    }

    NSData initWithBytes(Memory bytes, @NamedArg("length") int length);

    NSData initWithData(NSData data);

    String base64EncodedStringWithOptions(int options);

    int length();
}
