group = "org.visual.script"

dependencies {
  implementation(libs.groovy)
  implementation(libs.groovyJsr223)
  implementation(libs.groovyJMX)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)
  testAnnotationProcessor(libs.avajeInjectTestGenerator)
}