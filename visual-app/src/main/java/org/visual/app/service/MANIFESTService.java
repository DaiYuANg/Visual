package org.visual.app.service;

import io.vavr.control.Try;
import jakarta.inject.Singleton;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.visual.app.model.MANIFEST;
import org.visual.app.model.MANIFESTBuilder;

import java.util.Properties;

@Singleton
public class MANIFESTService {

  @SneakyThrows
  public MANIFEST read() {
    @Cleanup val inputStream = getClass().getResourceAsStream("/META-INF/MANIFEST.MF");
    return Try.of(Properties::new)
      .andThenTry((manifest) -> manifest.load(inputStream))
      .map(properties -> MANIFESTBuilder.builder()
        .manifestVersion(properties.getProperty("Manifest-Version"))
        .implementationTitle(properties.getProperty("Implementation-Title"))
        .implementationGroup(properties.getProperty("Implementation-Group"))
        .implementationVersion(properties.getProperty("Implementation-Version"))
        .builtBy(properties.getProperty("Built-By"))
        .builtHost(properties.getProperty("Built-Host"))
        .builtDate(properties.getProperty("Built-Date"))
        .builtOS(properties.getProperty("Built-OS"))
        .builtJDK(properties.getProperty("Built-JDK"))
        .scmRepository(properties.getProperty("SCM-Repository"))
        .scmBranch(properties.getProperty("SCM-Branch"))
        .scmCommitMessage(properties.getProperty("SCM-Commit-Message"))
        .scmCommitHash(properties.getProperty("SCM-Commit-Hash"))
        .scmCommitAuthor(properties.getProperty("SCM-Commit-Author"))
        .scmCommitDate(properties.getProperty("SCM-Commit-Date"))
        .build()
      ).get();
  }
}
