group = "org.visual.fonts"

version = "unspecified"

dependencies {}

tasks.jar {
  dependsOn("embedJetbrainsMono")
  from("build/jetbrainsMono") { into("/") }
}

task("embedJetbrainsMono") {
  doLast {
    if (!project.layout.buildDirectory.file("jetbrainsMono.zip").get().asFile.exists()) {
      ant.invokeMethod(
          "get",
          mapOf(
              "src" to "https://download.jetbrains.com/fonts/JetBrainsMono-2.304.zip",
              "dest" to "build/jetbrainsMono.zip"))

      ant.invokeMethod(
          "unzip", mapOf("src" to "build/jetbrainsMono.zip", "dest" to "build/jetbrainsMono"))
      println("download jetbrains mono")
    }
  }
}
