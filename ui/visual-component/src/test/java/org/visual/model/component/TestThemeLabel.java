package org.visual.model.component;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.function.Supplier;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

@Slf4j
@ExtendWith(ApplicationExtension.class)
public class TestThemeLabel extends ApplicationTest {

  private final Supplier<Label> themeLabel = () -> new Label("test");

  @Override
  public void start(@NotNull Stage stage) {
    val pane = new StackPane(themeLabel.get());
    val scene = new Scene(pane);
    scene.setUserAgentStylesheet("/theme.css");
    stage.setScene(scene);
    stage.show();
  }

  @Test
  void testThemeLabel() {
    scanResources();
  }

  @SneakyThrows
  private static void scanResources() {
    // 获取当前线程的ClassLoader
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    // 使用ClassLoader的getResources方法获取资源
    Enumeration<URL> resources = classLoader.getResources("");

    while (resources.hasMoreElements()) {
      URL resource = resources.nextElement();
      System.out.println("Resource: " + resource);

      // 将URL转换为文件路径
      String filePath = resource.getFile();
      listFiles(new File(filePath));
    }
  }

  private static void listFiles(File directory) {
    // 列出目录下的所有文件和子目录
    File[] files = directory.listFiles();

    if (files != null) {
      Arrays.stream(files)
          .parallel()
          .forEach(
              file -> {
                if (file.isDirectory()) {
                  // 如果是子目录，递归调用listFiles
                  listFiles(file);
                } else {
                  // 如果是文件，打印文件路径
                  log.info("File: " + file.getAbsolutePath());
                }
              });
    }
  }
}
