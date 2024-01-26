plugins {
  alias(libs.plugins.javafx)
  `java-library`
  id("io.miret.etienne.sass") version ("1.5.0")
  `kotlin-project`
}

group = "org.visual.model.component"

version = "unspecified"

javafx {
  version = libs.versions.javafxVersion.get()
  modules(
      "javafx.controls",
      "javafx.fxml",
      "javafx.graphics",
      "javafx.swing",
      "javafx.media",
  )
  configurations = arrayOf("implementation", "testImplementation")
}

tasks.jar { manifest { "JavaFxVersion" to libs.versions.javafxVersion.get() } }

dependencies {
  api(libs.oshi)
  testImplementation(libs.javafxUnitTest)
  api(projects.module.visualModelShared)
  api(libs.fontawesome5)
  api(libs.ikonliJavafx)
  api(libs.fluentuiIcon)
  api(libs.simpleicon)
  api(libs.controlfx)
  api(libs.materialIcons)
  api(projects.libs.jfa)
  api(libs.atlantafx)
  api(libs.devicons)
  api(libs.apacheCommonPool)
  api(libs.animated)
  api(libs.flowless)
  implementation(projects.libs.fonts)
  testImplementation(projects.libs.fonts)
  api("com.github.kwhat:jnativehook:2.2.2")
  implementation("org.fxmisc.richtext:richtextfx:0.11.2")
  implementation(projects.ui.visualModelComponentAnnotation)
}

tasks.compileSass {
  dependsOn(tasks.spotlessApply)
  sourceDir = project.layout.projectDirectory.file("src/main/sass").asFile
  sourceMap = none
}

tasks.test {
  tasks.compileSass.get().outputDir = project.file("build/resources/test")
  dependsOn(tasks.compileSass)
  dependsOn(tasks.processTestResources)
}

tasks.jar {
  dependsOn(tasks.processTestResources)
  dependsOn(tasks.compileSass)
  from(tasks.compileSass.get().outputDir) { into("/") }
}
