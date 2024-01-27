import io.avaje.inject.plugin.AvajeInjectPlugin

subprojects{
    apply<KotlinProjectPlugin>()
    apply<CommonPlugin>()
    apply<FatJarPlugin>()
    apply<FxProjectPlugin>()
    apply<AvajeInjectPlugin>()
    apply<JlinkProject>()
    dependencies {
        implementation(rootProject.projects.ui.visualModelComponentAnnotation)
        implementation(rootProject.projects.ui.visualModelComponent)
        implementation(rootProject.libs.avajeInject)
        implementation(rootProject.libs.avajeInjectGenerator)
        implementation(rootProject.libs.gestaltConfig)
        implementation(rootProject.libs.gestaltToml)
        testImplementation(rootProject.libs.avajeInjectTest)
    }
}