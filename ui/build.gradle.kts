import org.openjfx.gradle.JavaFXOptions
import org.openjfx.gradle.JavaFXPlugin

subprojects {
  apply<JavaFXPlugin>()
  extensions.configure<JavaFXOptions> {
    version = rootProject.libs.versions.javafxVersion.get()
    modules(*javafxModules.toTypedArray())
    configurations =
      arrayOf(
        "compileOnly",
        "testImplementation",
      )
  }
  dependencies {
    testImplementation(rootProject.libs.javafxUnitTest)
  }
}