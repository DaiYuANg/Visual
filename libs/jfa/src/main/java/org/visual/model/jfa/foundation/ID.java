/* (C)2000-2020*/
package org.visual.model.jfa.foundation;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

/**
 * Could be an address in memory (if pointer to a class or method) or a value (like 0 or 1)
 *
 * <p>User: spLeaner
 */
public class ID extends NativeLong {

  public static final ID NIL = new ID(0L);

  public ID() {}

  public ID(long peer) {
    super(peer);
  }

  public ID(Pointer peer) {
    super(Pointer.nativeValue(peer));
  }

  public Pointer toPointer() {
    return new Pointer(longValue());
  }

  public boolean booleanValue() {
    return intValue() != 0;
  }
}
