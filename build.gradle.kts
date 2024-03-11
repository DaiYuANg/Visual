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
  implementation(libs.guice)
  implementation(libs.guiceJMX)
  implementation(libs.guiceGrapher)
  implementation(libs.guiceThrowingproviders)
  implementation(libs.guiceAssistedinject)
  testImplementation(libs.guiceTestlib)
  implementation(libs.slf4jJulBridage)

  implementation(libs.picocli)
  implementation(libs.picocliJline)
  annotationProcessor(libs.picocliCodegen)

  implementation(libs.pcollections)

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