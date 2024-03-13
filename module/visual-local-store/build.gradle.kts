plugins {
  alias(libs.plugins.hibernate)
}

group = "org.visual.local.store"

dependencies {
  implementation(libs.guava)

  implementation(enforcedPlatform(libs.hibernatePlatform))
  implementation(libs.hibernateCore)
  implementation(libs.hibernateGraalvm)
  implementation(libs.hibernateHikaricp)
  implementation(libs.hibernateValidator)
  implementation(libs.h2)
  implementation(libs.jakartaPersistenceAPI)
//  annotationProcessor(libs.jakartaPersistenceAPI)
  annotationProcessor(libs.hibernateJpamodelgen)
  compileOnly("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  annotationProcessor("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  implementation("com.querydsl:querydsl-jpa:${libs.versions.queryDsl.get()}:jakarta")
  implementation(libs.queryDslCore)
  implementation(libs.queryDslGuava)
  implementation(libs.queryDslSpatial)
  implementation(libs.queryDslCollections)

  testImplementation(libs.hibernateTesting)

  implementation(libs.guice)
  implementation(libs.guicePersist)
  implementation(libs.directories)
}

hibernate {
}