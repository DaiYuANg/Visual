subprojects {
    apply<FxProjectPlugin>()
    plugins.getPlugin(FxProjectPlugin::class).scope("compileOnly")
    dependencies {
        testImplementation(rootProject.libs.javafxUnitTest)
    }
}
