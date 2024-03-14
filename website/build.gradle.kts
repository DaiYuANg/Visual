plugins {
  alias(libs.plugins.asciidoctorPdf)
  alias(libs.plugins.asciidoctorJvm)
  alias(libs.plugins.asciidoctorEpub)
  alias(libs.plugins.asciidoctorEditconfig)
  alias(libs.plugins.asciidoctorGem)
//  alias(libs.plugins.asciidoctorRevealjs)
}

repositories {
  ruby {
    gems()
  }
}

asciidoctorj {
  modules {
    diagram.use()
  }
}

dependencies {
  asciidoctorGems("asciidoctor-plantuml:0.1.1")
}

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

tasks.build {
  dependsOn(tasks.asciidoctor)
}