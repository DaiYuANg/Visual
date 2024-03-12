group = "org.visual.codegen"

dependencies {
  implementation(libs.bytebuddy)
  implementation(libs.javapoet)
  compileOnly(libs.prism)
  annotationProcessor(libs.prism)
}