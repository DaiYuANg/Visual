group = "org.visual.codegen"

dependencies {
  compileOnly(libs.autoService)
  annotationProcessor(libs.autoService)
  implementation(libs.bytebuddy)
}