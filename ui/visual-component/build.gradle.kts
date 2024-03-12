plugins {
  alias(libs.plugins.sass)
}

group = "org.visual.component"

dependencies {
  api(libs.oshi)
  api(projects.module.visualShared)
  api(libs.fontawesome5)
  api(libs.ikonliJavafx)
  api(libs.fluentuiIcon)
  api(libs.simpleicon)
  api(libs.controlfx)
  api(libs.materialIcons)
  api(libs.atlantafx)
  api(libs.devicons)
  api(libs.apacheCommonPool)
  api(libs.animated)
  api(libs.flowless)
  api(projects.ui.visualI18n)
  api(libs.apacheCommonIO)
  implementation(libs.guava)
  implementation(projects.codegen.visualCodegen)
  annotationProcessor(projects.codegen.visualCodegen)
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