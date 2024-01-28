package org.visual.component.control.dialog;

import static org.visual.component.util.FXUtils.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.text.Font;
import org.visual.component.container.ClickableFusionPane;
import org.visual.component.container.FusionPane;
import org.visual.component.display.ThemeLabel;
import org.visual.component.font.FontManager;
import org.visual.component.font.FontUsages;
import org.visual.component.layout.VPadding;
import org.visual.component.manager.internal_i18n.InternalI18n;

public class StackTraceAlert extends ThemeAlertBase {
  private StackTraceAlert(String desc, Throwable throwable) {
    //        Logger.error(LogType.ALERT, "StackTraceAlert: " + desc, throwable);

    setTitle(InternalI18n.get().stacktraceAlertTitle());

    var headerText =
        new ThemeLabel(InternalI18n.get().stacktraceAlertHeaderText()) {
          {
            FontManager.get().setFont(FontUsages.alert, this);
          }
        };
    var descText =
        new ThemeLabel() {
          {
            FontManager.get().setFont(FontUsages.alert, this);
          }
        };
    if (desc != null && !desc.isBlank()) {
      descText.setText(desc);
    }

    var aboutStacktraceText =
        new ThemeLabel(InternalI18n.get().stacktraceAlertLabel()) {
          {
            FontManager.get().setFont(FontUsages.alert, this);
          }
        };

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    throwable.printStackTrace(pw);
    String exceptionText = sw.toString();

    var stacktracePane = new ClickableFusionPane();
    stacktracePane.setOnAction(
        e -> {
          Clipboard.getSystemClipboard().setContent(Map.of(DataFormat.PLAIN_TEXT, exceptionText));
        });
    stacktracePane.getNode().setPrefWidth(getStage().getWidth() - 2 * PADDING_H - 5);
    var stacktraceText =
        new ThemeLabel(exceptionText) {
          {
            setFont(new Font(FontManager.FONT_NAME_JetBrainsMono, 14));
            setWrapText(true);
            setPrefWidth(stacktracePane.getNode().getPrefWidth() - FusionPane.PADDING_H * 2);
          }
        };
    stacktracePane.getContentPane().getChildren().add(stacktraceText);
    observeHeight(stacktraceText, stacktracePane.getNode(), FusionPane.PADDING_V * 2);

    // this triggers height update
    runDelay(50, () -> stacktraceText.setMinHeight(stacktraceText.getHeight() + 1));

    alertMessagePane
        .getChildren()
        .addAll(
            headerText, descText, new VPadding(20), aboutStacktraceText, stacktracePane.getNode());
  }

  public static void show(Throwable throwable) {
    show("", throwable);
  }

  public static void show(String desc, Throwable throwable) {
    runOnFX(() -> new StackTraceAlert(desc, throwable).show());
  }

  public static void showAndWait(Throwable throwable) {
    showAndWait("", throwable);
  }

  public static void showAndWait(String desc, Throwable throwable) {
    new StackTraceAlert(desc, throwable).showAndWait();
  }
}
