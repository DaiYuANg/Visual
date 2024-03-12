import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.repositories

class RepositoriesSetting : Plugin<Project> {
  override fun apply(target: Project) {
    target.allprojects {
      apply<JavaLibraryPlugin>()
      repositories {
        mavenLocal()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://repository.apache.org/snapshots/") }
        gradlePluginPortal()
        google()
      }
    }
  }
}