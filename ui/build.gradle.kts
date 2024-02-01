subprojects {
    apply<FxProjectPlugin>()
    dependencies {
        testImplementation(rootProject.libs.javafxUnitTest)
    }
}
