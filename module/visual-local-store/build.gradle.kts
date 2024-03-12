group = "org.visual.local.store"

dependencies {
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)
  testAnnotationProcessor(libs.avajeInjectTestGenerator)
  implementation(libs.directories)
  implementation(libs.guava)
  implementation(enforcedPlatform(libs.nitriteBom))
  implementation(libs.nitrite)
  implementation(libs.nitriteJackson)
  implementation(libs.nitriteMvstore)
}