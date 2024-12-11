plugins {
  alias(libs.plugins.asciidoctorPdf)
  alias(libs.plugins.asciidoctorJvm)
  alias(libs.plugins.asciidoctorEpub)
  alias(libs.plugins.asciidoctorEditconfig)
  alias(libs.plugins.asciidoctorGem)
  idea
}

repositories { ruby { gems() } }

asciidoctorj { modules { diagram.use() } }

tasks.asciidoctor {
  parallelMode = true
  languages("en", "zh")
  jvm {
    jvmArgs(
      "--add-opens",
      "java.base/sun.nio.ch=ALL-UNNAMED",
      "--add-opens",
      "java.base/java.io=ALL-UNNAMED",
    )
  }
}

idea {
  module {
    sourceDirs =
      project.tasks.asciidoctor
        .get()
        .sourceFileTree
        .toMutableSet()
  }
}