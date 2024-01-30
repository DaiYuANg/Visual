import io.avaje.inject.plugin.AvajeInjectPlugin

subprojects {
  apply<KotlinProjectPlugin>()
  apply<CommonPlugin>()
  apply<FatJarPlugin>()
  apply<FxProjectPlugin>()
  apply<AvajeInjectPlugin>()
  apply<JlinkProject>()
  dependencies {
    implementation(rootProject.libs.avajeInject)
    annotationProcessor(rootProject.libs.avajeInjectGenerator)
    implementation(rootProject.libs.gestaltConfig)
    implementation(rootProject.libs.gestaltToml)
    testImplementation(rootProject.libs.avajeInjectTest)
    implementation(rootProject.libs.picocli)
    annotationProcessor(rootProject.libs.picocliCodegen)
  }
}
