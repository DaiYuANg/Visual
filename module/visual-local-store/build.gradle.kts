plugins {
  alias(libs.plugins.hibernate)
  alias(libs.plugins.flyway)
}

group = "org.visual.local.store"

dependencies {
  implementation(enforcedPlatform(libs.hibernatePlatform))
  implementation(libs.hibernateCore)
  implementation(libs.hibernateGraalvm)
  implementation(libs.hibernateHikaricp)
  implementation(libs.hibernateValidator)
  implementation(libs.h2)
  implementation(libs.directories)
  implementation(libs.jakartaPersistenceAPI)
  implementation(libs.guava)
  implementation(libs.guice)
  implementation(libs.guicePersist)
  annotationProcessor(libs.hibernateJpamodelgen)
  compileOnly("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  implementation("com.querydsl:querydsl-core:${libs.versions.queryDsl.get()}")
  implementation("com.querydsl:querydsl-jpa:${libs.versions.queryDsl.get()}:jakarta")
  implementation("com.querydsl:querydsl-guava:${libs.versions.queryDsl.get()}")
  implementation("com.querydsl:querydsl-collections:${libs.versions.queryDsl.get()}")
  implementation("com.querydsl:querydsl-spatial:${libs.versions.queryDsl.get()}")
  annotationProcessor("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  testImplementation(libs.hibernateTesting)
}

hibernate {
//  enhancement {
//    enableLazyInitialization.set(true)
//    enableDirtyTracking.set(true)
//    enableAssociationManagement.set(true)
//    enableExtendedEnhancement.set(true)
//  }
}