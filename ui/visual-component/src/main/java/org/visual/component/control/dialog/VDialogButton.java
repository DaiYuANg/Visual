package org.visual.component.control.dialog;

import java.util.function.Supplier;
import lombok.Getter;
import org.visual.component.control.button.FusionButton;

public class VDialogButton<T> {
  public final String name;
  public final Supplier<T> provider;
  // will be null before setting into a VDialog
  @Getter FusionButton button;

  public VDialogButton(String name, T value) {
    this(name, () -> value);
  }

  public VDialogButton(String name, Supplier<T> provider) {
    this.name = name;
    this.provider = provider;
  }

  public VDialogButton(String name) {
    this.name = name;
    this.provider = null;
  }
}
