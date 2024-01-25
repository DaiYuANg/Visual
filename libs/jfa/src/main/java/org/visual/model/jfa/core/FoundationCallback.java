/* (C)2024*/
package org.visual.model.jfa.core;

import com.sun.jna.Pointer;
import lombok.Getter;
import org.visual.model.jfa.foundation.ID;

public record FoundationCallback(ID target, Pointer selector) {
}
