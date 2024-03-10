plugins {
  alias(libs.plugins.hibernate)
  alias(libs.plugins.extraJavaModule)
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
  implementation(libs.avajeInject)
  implementation(libs.jakartaPersistenceAPI)
  annotationProcessor(libs.avajeInjectGenerator)
  annotationProcessor(libs.hibernateJpamodelgen)
  testImplementation(libs.hibernateTesting)
}

hibernate {
  enhancement {
    enableLazyInitialization.set(true)
    enableDirtyTracking.set(true)
    enableAssociationManagement.set(true)
    enableExtendedEnhancement.set(true)
  }
}