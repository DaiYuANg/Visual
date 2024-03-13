plugins {
  alias(libs.plugins.sass)
}

apply<KotlinSetting>()

group = "org.visual.component"

dependencies {
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
  api(libs.apacheCommonIO)

  implementation(libs.guava)
  implementation(libs.fastutil)
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