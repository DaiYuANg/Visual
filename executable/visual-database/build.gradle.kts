plugins {
  application
  java
}

group = "org.visual.database"

version = "unspecified"

plugins.getPlugin(FxProjectPlugin::class.java).modules("javafx.media", "javafx.web")

application {
  mainClass = "${group}.VisualModelDatabase"
  mainModule.set(group.toString())
  applicationDefaultJvmArgs = commonJvmArgs
}

dependencies {
  implementation(libs.hikariCP)
  implementation(libs.mavenResloverAPI)
  implementation(libs.mavenResloverImpl)
  implementation(libs.mavenResloverJDK21)
  implementation(libs.mavenResloverSupplier)
  implementation(libs.mavenResloverUtil)
  implementation(projects.ui.visualGraphEditor)
  implementation(projects.ui.visualTextEditor)
  implementation(projects.ui.visualI18n)
  implementation(projects.module.visualShared)
  implementation(projects.ui.visualComponent)
  implementation(libs.avajeValidaor)
  implementation(libs.avajeValidaorCodegen)
  implementation(libs.picocli)
  implementation(projects.ui.visualCollaborative)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.jacksonCore)
  implementation(libs.jacksonDatabind)
  implementation(libs.jacksonAnnotations)
}
