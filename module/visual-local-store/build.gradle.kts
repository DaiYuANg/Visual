plugins {
//  alias(libs.plugins.hibernate)
  alias(libs.plugins.ebean)
}

apply<KotlinSetting>()

group = "org.visual.local.store"

dependencies {
  implementation(libs.guava)
  implementation(libs.h2)

  implementation(libs.ebeanAPI)
  implementation(libs.ebeanCore)
  implementation(libs.ebeanAnnotation)
  implementation(libs.ebeanDataSource)
  implementation(libs.ebeanPlatformH2)
  implementation(libs.ebeanQueryBean)
  implementation(libs.ebeanMigration)
  implementation(libs.ebeanDDLGenerator)
  annotationProcessor(libs.ebeanQueryBeanGenerator)
  testImplementation(libs.ebeanTest)

  implementation(libs.guice)

  implementation(libs.directories)
}

ebean {
  debugLevel = 1
}

tasks.register<JavaExec>("executeMigration") {
  group = "build"
  javaLauncher.set(javaToolchains.launcherFor(java.toolchain))
  classpath = sourceSets["main"].runtimeClasspath
  mainClass.set("org.visual.local.store.MigrationGenerator")
  args(projectDir.toString())
}