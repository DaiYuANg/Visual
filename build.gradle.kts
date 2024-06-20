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
  implementation(libs.mutiny)
  implementation(libs.dataFaker)
  implementation(libs.jgrapht)
  implementation(libs.eclipseCollections)
  implementation(libs.eclipseCollectionsAPI)
  implementation(libs.eclipseCollectionsForkjoin)
  implementation(libs.atlantafx)
  implementation(libs.guava)

  implementation(projects.visualEditor)

  implementation(libs.ikonliJavafx)
  implementation(libs.fontawesome5)

  implementation(libs.logback)

  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)

  implementation(libs.picocli)
  implementation(libs.picocliJline)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.pcollections)
  implementation(libs.slf4jJulBridage)
  implementation(libs.recordBuilderCore)
  annotationProcessor(libs.recordBuilderProcess)

  implementation(projects.visualStore)

  testImplementation(libs.javafxUnitTest)
}

subprojects { parent!!.path.takeIf { it != rootProject.path }?.let { evaluationDependsOn(it) } }