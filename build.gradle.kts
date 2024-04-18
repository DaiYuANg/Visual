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
apply<KotlinSetting>()

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

  implementation(libs.ikonliJavafx)
  implementation(libs.fontawesome5)

  implementation(libs.logback)

  implementation(libs.guice)
  implementation(libs.guiceJNDI)
  implementation(libs.guiceJMX)
  implementation(libs.guiceThrowingproviders)
  testImplementation(libs.guiceTestlib)

  implementation(libs.picocli)
  implementation(libs.picocliJline)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.pcollections)
  implementation(libs.slf4jJulBridage)

  implementation(libs.immutablesValue)
  implementation(libs.immutablesBuilder)
  annotationProcessor(libs.immutablesValue)
  implementation(libs.immutablesAnnotate)
  implementation(libs.immutablesSerial)
  testImplementation(libs.javafxUnitTest)
}

tasks.compileJava {
  options.compilerArgs.add("-Aimmutables.gradle.incremental")
}

subprojects { parent!!.path.takeIf { it != rootProject.path }?.let { evaluationDependsOn(it) } }