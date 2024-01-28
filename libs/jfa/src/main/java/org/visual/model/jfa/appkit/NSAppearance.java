/* (C)2024*/
package org.visual.model.jfa.appkit;

import static org.visual.model.jfa.foundation.Foundation.*;

import org.jetbrains.annotations.NotNull;
import org.visual.model.jfa.core.ObjcToJava;

@SuppressWarnings("unused")
public interface NSAppearance extends NSObject {
  static NSAppearance appearanceNamed(@NotNull NSAppearanceName appearanceName) {
    return ObjcToJava.map(
        invoke(getObjcClass("NSAppearance"), "appearanceNamed:", nsString(appearanceName.name())),
        NSAppearance.class);
  }

  String name();

  enum NSAppearanceName {
    NSAppearanceNameAqua,
    NSAppearanceNameDarkAqua,
    NSAppearanceNameVibrantLight,
    NSAppearanceNameVibrantDark,
    NSAppearanceNameAccessibilityHighContrastAqua,
    NSAppearanceNameAccessibilityHighContrastDarkAqua,
    NSAppearanceNameAccessibilityHighContrastVibrantLight,
    NSAppearanceNameAccessibilityHighContrastVibrantDark
  }
}
