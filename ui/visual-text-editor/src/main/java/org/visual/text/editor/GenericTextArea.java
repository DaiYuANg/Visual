package org.visual.text.editor;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.layout.Region;

public class GenericTextArea extends Region {
  private final PseudoClass READ_ONLY = PseudoClass.getPseudoClass("readonly");
  private final PseudoClass HAS_CARET = PseudoClass.getPseudoClass("has-caret");
  private final PseudoClass FIRST_PAR = PseudoClass.getPseudoClass("first-paragraph");
  private final PseudoClass LAST_PAR = PseudoClass.getPseudoClass("last-paragraph");

  private final BooleanProperty editable =
      new SimpleBooleanProperty(this, "editable", true) {
        @Override
        protected void invalidated() {
          ((Region) getBean()).pseudoClassStateChanged(READ_ONLY, !get());
        }
      };
}
