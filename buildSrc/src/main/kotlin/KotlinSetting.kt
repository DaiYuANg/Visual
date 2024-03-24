import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.*
import org.gradle.process.CommandLineArgumentProvider
import org.jetbrains.kotlin.allopen.gradle.AllOpenGradleSubplugin
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.lombok.gradle.LombokExtension
import org.jetbrains.kotlin.lombok.gradle.LombokSubplugin
import org.jetbrains.kotlin.noarg.gradle.NoArgGradleSubplugin
import org.jetbrains.kotlinx.serialization.gradle.SerializationGradleSubplugin

class KotlinSetting : Plugin<Project> {
  override fun apply(target: Project) {

    val rootProject = rootProject(target)
    val rootLib = rootLibs(target)
    val jdkVersion = rootLib.versions.jdk.get()

    target.apply<KotlinPluginWrapper>()
    target.apply<LombokSubplugin>()
    target.apply<AllOpenGradleSubplugin>()
    target.apply<NoArgGradleSubplugin>()
    target.apply<SerializationGradleSubplugin>()

    target.dependencies { add(IMPLEMENTATION, rootLib.kotlinLogging) }

    target.tasks.withType(KotlinCompile::class) {
      val compileJava: JavaCompile by target.tasks
      destinationDirectory.set(compileJava.destinationDirectory)
    }

    val sourceSets = target.extensions.getByType(SourceSetContainer::class)
    target.tasks.withType(JavaCompile::class).configureEach {
      options.compilerArgumentProviders.add(
          CommandLineArgumentProvider {
            listOf("--patch-module", "$group=${sourceSets["main"].output.asPath}")
          },
      )
    }

    target.extensions.configure<LombokExtension> {
      lombokConfigurationFile(rootProject.file("lombok.config"))
    }

    target.extensions.configure<KotlinJvmProjectExtension> { jvmToolchain(jdkVersion.toInt()) }
  }
}
