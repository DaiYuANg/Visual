package org.visual.app.pool.dialog;

import javafx.scene.control.Dialog;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class DialogPooledObject extends DefaultPooledObject<Dialog<?>> {
  public DialogPooledObject(Dialog<?> object) {
    super(object);
  }
}
