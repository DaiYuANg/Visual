
group = "org.visual.config"

dependencies {
  implementation(libs.gestaltConfig)
  implementation(libs.gestaltToml)
  implementation(libs.gestaltYaml)
  implementation(libs.gestaltJSON)
  implementation(libs.directories)

  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)
  testAnnotationProcessor(libs.avajeInjectTestGenerator)
//  implementation(libs.guava)
//  implementation(libs.gestaltGuice) {
//    exclude(group = "com.google.guava", module = "guava")
//  }
}