plugins {
  alias(libs.plugins.hibernate)
//  alias(libs.plugins.ebean)
}

apply<KotlinSetting>()

group = "org.visual.local.store"

dependencies {
  implementation(libs.guava)
  implementation(libs.h2)
  implementation(libs.guice)
  implementation(libs.directories)
  implementation(enforcedPlatform(libs.hibernatePlatform))
  implementation(libs.hibernateCore)
  implementation(libs.hibernateGraalvm)
  implementation(libs.hibernateHikaricp)
  implementation(libs.hibernateValidator)
  implementation(libs.hibernateJfr)
  implementation(libs.h2)
  annotationProcessor(libs.jakartaPersistenceAPI)
  annotationProcessor(libs.hibernateJpamodelgen)
  implementation(libs.guicePersist)
  compileOnly("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  annotationProcessor("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  implementation("com.querydsl:querydsl-jpa:${libs.versions.queryDsl.get()}:jakarta")
  implementation(libs.queryDslCore)
  implementation(libs.queryDslGuava)
  implementation(libs.queryDslSpatial)
  implementation(libs.queryDslCollections)
  testImplementation(libs.hibernateTesting)
}

// ebean {
//  debugLevel = 1
// }

tasks.register<JavaExec>("executeMigration") {
  group = "build"
  javaLauncher.set(javaToolchains.launcherFor(java.toolchain))
  classpath = sourceSets["main"].runtimeClasspath
  mainClass.set("org.visual.local.store.MigrationGenerator")
  args(projectDir.toString())
}