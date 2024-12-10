plugins {
  alias(libs.plugins.asciidoctorPdf)
  alias(libs.plugins.asciidoctorJvm)
  alias(libs.plugins.asciidoctorEpub)
  alias(libs.plugins.asciidoctorEditconfig)
  alias(libs.plugins.asciidoctorGem)
  idea
}

apply<ModulePlugin>()

group = "org.visual.document"
repositories { ruby { gems() } }

dependencies {
  implementation(libs.guava)
}

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

tasks.build { dependsOn(tasks.asciidoctor) }

idea {
  module {
    sourceDirs =
      project.tasks.asciidoctor
        .get()
        .sourceFileTree
        .toMutableSet()
  }
}

tasks.create("copyDocument", Copy::class) {
  group = "build"
  from(layout.buildDirectory.dir("docs"))
  destinationDir = layout.buildDirectory.dir("classes/java/main/org/visual/document").get().asFile
  dependsOn(tasks.asciidoctor)
}

tasks.jar {
  dependsOn("copyDocument")
}