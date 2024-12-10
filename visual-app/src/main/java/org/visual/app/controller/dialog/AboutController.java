package org.visual.app.controller.dialog;

import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.service.MANIFESTService;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Lazy
@Slf4j
@RequiredArgsConstructor
public class AboutController implements Initializable {

  private final MANIFESTService manifestService;
  @FXML
  private Label manifestVersion;
  @FXML
  private Label implementationTitle;
  @FXML
  private Label implementationGroup;
  @FXML
  private Label implementationVersion;
  @FXML
  private Label builtBy;
  @FXML
  private Label builtHost;
  @FXML
  private Label builtDate;
  @FXML
  private Label builtOS;
  @FXML
  private Label builtJdk;
  @FXML
  private Label scmRepository;
  @FXML
  private Label scmBranch;
  @FXML
  private Label scmCommitMessage;
  @FXML
  private Label scmCommitHash;
  @FXML
  private Label scmCommitAuthor;
  @FXML
  private Label scmCommitDate;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    val manifest = manifestService.read();
    log.atInfo().log("Current:{}", manifest);
    manifestVersion.setText("Manifest Version: " + manifest.manifestVersion());
    implementationTitle.setText("Implementation Title: " + manifest.implementationTitle());
    implementationGroup.setText("Implementation Group: " + manifest.implementationGroup());
    implementationVersion.setText("Implementation Version: " + manifest.implementationVersion());
    builtBy.setText("Built By: " + manifest.builtBy());
    builtHost.setText("Built Host: " + manifest.builtHost());
    builtDate.setText("Built Date: " + manifest.builtDate());
    builtOS.setText("Built OS: " + manifest.builtOS());
    builtJdk.setText("Built JDK: " + manifest.builtJDK());
    scmRepository.setText("SCM Repository: " + manifest.scmRepository());
    scmBranch.setText("SCM Branch: " + manifest.scmBranch());
    scmCommitMessage.setText("SCM Commit Message: " + manifest.scmCommitMessage());
    scmCommitHash.setText("SCM Commit Hash: " + manifest.scmCommitHash());
    scmCommitAuthor.setText("SCM Commit Author: " + manifest.scmCommitAuthor());
    scmCommitDate.setText("SCM Commit Date: " + manifest.scmCommitDate());
  }
}
