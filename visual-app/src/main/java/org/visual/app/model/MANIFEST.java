package org.visual.app.model;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record MANIFEST(
  String manifestVersion,
  String implementationTitle,
  String implementationGroup,
  String implementationVersion,
  String builtBy,
  String builtHost,
  String builtDate,
  String builtOS,
  String builtJDK,
  String scmRepository,
  String scmBranch,
  String scmCommitMessage,
  String scmCommitHash,
  String scmCommitAuthor,
  String scmCommitDate
) {
}
