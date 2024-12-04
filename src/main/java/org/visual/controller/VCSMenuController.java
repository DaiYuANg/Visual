package org.visual.controller;

import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.jgit.api.Git;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

@Slf4j
@RequiredArgsConstructor
@Singleton
@Lazy
public class VCSMenuController implements Initializable {
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  @SneakyThrows
  public void initGitRepository() {
    val path = Paths.get(FileUtils.getTempDirectoryPath(), RandomStringUtils.secure().next(10));
    @Cleanup val gitRepository = Git.init().setDirectory(path.toFile()).call();
    gitRepository.log().call().forEach(commit -> log.debug(commit.getFullMessage()));
  }
}
