plugins {
  jacoco
  java
  alias(libs.plugins.dotenv)
}
apply<IdeaSetting>()
apply<FormatSetting>()
apply<RepositoriesSetting>()
apply<JavaFxSetting>()
apply<AllProjectSetting>()
apply<RootProjectSetting>()
apply<ReleaseSetting>()

dependencies {
  implementation(libs.logback)
  implementation(libs.fastutil)

  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)
  testAnnotationProcessor(libs.avajeInjectTestGenerator)

  implementation(libs.avajeValidaor)
  implementation(libs.avajeValidaorConstraints)
  annotationProcessor(libs.avajeValidaorCodegen)

  implementation(libs.picocli)
  implementation(libs.picocliJline)
  annotationProcessor(libs.picocliCodegen)

  implementation(libs.pcollections)
  implementation(libs.slf4jJulBridage)

  implementation(libs.furyCore)
  implementation(libs.furyFormat)

  implementation(projects.module.visualConfig)
  implementation(projects.serialize.visualSerializeApi)
  implementation(projects.serialize.visualSerializeJson)
  implementation(projects.module.visualScript)
  implementation(projects.module.visualLocalStore)
  implementation(projects.module.visualGit)
  implementation(projects.module.visualShared)
  implementation(projects.ui.visualDebugger)
  implementation(projects.ui.visualComponent)
  implementation(projects.ui.visualGraphEditor)
  implementation(projects.ui.visualCollaborative)
  implementation(projects.ui.visualI18n)
  compileOnly(projects.codegen.visualCodegen)
  annotationProcessor(projects.codegen.visualCodegen)

  testImplementation(libs.javafxUnitTest)
}