/* (C)2024*/
package org.visual.model.jfa.appkit;

import com.sun.jna.Structure;
import org.visual.model.jfa.foundation.CGFloat;

@Structure.FieldOrder({"x", "y"})
@SuppressWarnings("unused")
public class NSPoint extends Structure implements Structure.ByValue {
  public CGFloat x;
  public CGFloat y;
}
