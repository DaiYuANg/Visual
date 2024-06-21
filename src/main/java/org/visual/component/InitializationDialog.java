package org.visual.component;

import java.io.File;
import javafx.scene.control.Dialog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitializationDialog extends Dialog<File> {

  {
    setDialogPane(new InitializationPane());
    setTitle("Initialization");
    setResizable(true);
  }
}
