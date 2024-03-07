plugins {
  alias(libs.plugins.ebean)
}

group = "org.visual.local.store"

dependencies {
  implementation(libs.ebeanAPI)
  implementation(libs.ebeanCore)
  implementation(libs.ebeanAnnotation)
  implementation(libs.ebeanDataSource)
  implementation(libs.ebeanPlatformH2)
  implementation(libs.h2)
  implementation(libs.ebeanQueryBean)
  implementation(libs.directories)
  implementation(libs.avajeInject)
  implementation(libs.ebeanMigration)
  implementation(libs.ebeanDDLGenerator)
  annotationProcessor(libs.avajeInjectGenerator)
  annotationProcessor(libs.ebeanQueryBeanGenerator)
  testImplementation(libs.ebeanTest)
}

tasks.register<JavaExec>("executeMigration") {
  group = "build"
  javaLauncher.set(javaToolchains.launcherFor(java.toolchain))
  classpath = sourceSets["main"].runtimeClasspath
  mainClass.set("org.visual.local.store.DBMigration")
  args(projectDir.toString())
}

ebean {
  debugLevel = 1
}