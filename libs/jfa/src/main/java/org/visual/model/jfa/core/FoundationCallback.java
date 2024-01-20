/* (C)2024*/
package org.visual.model.jfa.core;

import com.sun.jna.Pointer;
import lombok.Getter;
import org.visual.model.jfa.foundation.ID;

@Getter
public class FoundationCallback {
    private final ID target;
    private final Pointer selector;

    public FoundationCallback(ID target, Pointer selector) {
        this.target = target;
        this.selector = selector;
    }
}
