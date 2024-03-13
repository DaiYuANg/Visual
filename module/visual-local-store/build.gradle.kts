plugins {
  alias(libs.plugins.hibernate)
}

group = "org.visual.local.store"

dependencies {
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)
  testAnnotationProcessor(libs.avajeInjectTestGenerator)
  implementation(libs.directories)

  implementation(enforcedPlatform(libs.hibernatePlatform))
  implementation(libs.hibernateCore)
  implementation(libs.hibernateGraalvm)
  implementation(libs.hibernateHikaricp)
  implementation(libs.hibernateValidator)
  implementation(libs.h2)
  implementation(libs.jakartaPersistenceAPI)

  compileOnly("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  annotationProcessor("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  implementation("com.querydsl:querydsl-jpa:${libs.versions.queryDsl.get()}:jakarta")
  implementation(libs.queryDslCore)
  implementation(libs.queryDslGuava)
  implementation(libs.queryDslSpatial)
  implementation(libs.queryDslCollections)

  testImplementation(libs.hibernateTesting)

  implementation(libs.guava)
}

hibernate {
  enhancement {
  }
}