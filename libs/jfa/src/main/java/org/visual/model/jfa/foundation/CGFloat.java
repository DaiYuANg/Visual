package org.visual.model.jfa.foundation;

import com.sun.jna.FromNativeContext;
import com.sun.jna.Native;
import com.sun.jna.NativeMapped;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CGFloat implements NativeMapped {
  private final double value;

  @SuppressWarnings("UnusedDeclaration")
  public CGFloat() {
    this(0);
  }

  @Override
  public Object fromNative(Object o, FromNativeContext fromNativeContext) {
    return switch (Native.LONG_SIZE) {
      case 4 -> new CGFloat((Float) o);
      case 8 -> new CGFloat((Double) o);
      default -> throw new IllegalStateException();
    };
  }

  @Override
  public Object toNative() {
    return switch (Native.LONG_SIZE) {
      case 4 -> (float) value;
      case 8 -> value;
      default -> throw new IllegalStateException();
    };
  }

  @Override
  public Class<?> nativeType() {
    return switch (Native.LONG_SIZE) {
      case 4 -> Float.class;
      case 8 -> Double.class;
      default -> throw new IllegalStateException();
    };
  }
}
