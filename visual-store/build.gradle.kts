group = "org.visual.store"

plugins {
  alias(libs.plugins.hibernate)
}

dependencies {
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)

  implementation(enforcedPlatform(libs.hibernatePlatform))
  runtimeOnly(libs.h2)
  implementation(libs.hibernateCore)
  implementation(libs.hibernateHikaricp)
  implementation(libs.hibernateJfr)
  implementation(libs.hibernateValidator)
  implementation(libs.slf4j)
  implementation(libs.hibernateGraalvm)
  testImplementation(libs.hibernateTesting)
  implementation("net.bytebuddy:byte-buddy")
  implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.1.0")
  implementation("jakarta.xml.bind:jakarta.xml.bind-api")
  implementation("org.jboss.logging:jboss-logging")

  implementation(libs.apacheCommonIO)
  implementation(libs.jakartaPersistenceAPI)

  annotationProcessor(libs.hibernateProcessor)
  annotationProcessor(libs.hibernateJpamodelgen)

  val queryDSLApt =
    variantOf(libs.queryDslApt) {
      classifier(jakarta)
    }
  // queryDSL
  compileOnly(queryDSLApt)
  annotationProcessor(queryDSLApt)
  implementation(libs.queryDslJPA)
  implementation(libs.queryDslCore)
  implementation(libs.queryDslGuava)
  implementation(libs.queryDslCollection)
  implementation(libs.queryDslSpatial)
  annotationProcessor(libs.jakartaPersistenceAPI)
}

hibernate {
  enhancement {
    enableLazyInitialization = true
    enableDirtyTracking = true
  }
}