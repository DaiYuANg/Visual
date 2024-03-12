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
  implementation(libs.hikariCP)
  annotationProcessor(libs.hibernateJpamodelgen)
  compileOnly("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  annotationProcessor("com.querydsl:querydsl-apt:${libs.versions.queryDsl.get()}:jakarta")
  implementation("com.querydsl:querydsl-jpa:${libs.versions.queryDsl.get()}:jakarta")
  implementation(libs.queryDslCore)
  implementation(libs.queryDslGuava)
  implementation(libs.queryDslSpatial)
  implementation(libs.queryDslCollections)
  testImplementation(libs.hibernateTesting)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)
  testAnnotationProcessor(libs.avajeInjectTestGenerator)
//  implementation(libs.nitrite)
//  implementation(libs.nitriteJackson)
//  implementation(libs.nitriteMvstore)
//  implementation(libs.nitriteSpatial)
}

hibernate {
//  enhancement {
//    enableLazyInitialization.set(true)
//    enableDirtyTracking.set(true)
//    enableAssociationManagement.set(true)
//    enableExtendedEnhancement.set(true)
//  }
}