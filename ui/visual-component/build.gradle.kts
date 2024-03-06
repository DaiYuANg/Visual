plugins {
  alias(libs.plugins.sass)
//  `kotlin-project`
}

group = "org.visual.component"

dependencies {
  implementation(libs.oshi)
  implementation(projects.module.visualShared)
  implementation(libs.fontawesome5)
  implementation(libs.ikonliJavafx)
  implementation(libs.fluentuiIcon)
  implementation(libs.simpleicon)
  implementation(libs.controlfx)
  implementation(libs.materialIcons)
  implementation(libs.atlantafx)
  implementation(libs.devicons)
  implementation(libs.apacheCommonPool)
  implementation(libs.animated)
  implementation(libs.flowless)
  implementation(projects.ui.visualI18n)
  implementation(libs.apacheCommonIO)
  implementation(libs.jSystemThemeDetector)
}

tasks.compileSass {
//  dependsOn(tasks.spotlessApply)
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