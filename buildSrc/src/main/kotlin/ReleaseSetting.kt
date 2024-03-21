import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import org.beryx.jlink.JlinkPlugin
import org.beryx.jlink.data.JlinkPluginExtension
import org.graalvm.buildtools.gradle.NativeImagePlugin
import org.graalvm.buildtools.gradle.dsl.GraalVMExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jreleaser.gradle.plugin.JReleaserPlugin

class ReleaseSetting : Plugin<Project> {
  override fun apply(target: Project) {
    target.plugins.apply(JReleaserPlugin::class)
    target.plugins.apply(ShadowPlugin::class)
    target.plugins.apply(NativeImagePlugin::class)
    target.configure<GraalVMExtension> { toolchainDetection.set(true) }
    target.plugins.apply(JlinkPlugin::class)
    //    target.tasks.withType(ShadowJar::class.java) { minimize() }

    target.configure<JlinkPluginExtension> {
      addExtraDependencies("javafx", "kotlin", "jackson", "picocli", "jakarta")
      options.addAll("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
      //      options = listOf("--strip-debug", "--compress", "2", "--no-header-files",
      // "--no-man-pages")
      enableCds()
      launcher {
        noConsole = true
        name = convertToCamelCase(target.name)
        jvmArgs = devJvmArguments
      }

      imageZip.set(target.file("${target.layout.buildDirectory}/image-zip/visual-model-image.zip"))
      jpackage { appVersion = target.version.toString() }
      customImage {
        jdkModules = listOf("java.desktop", "java.xml", "jdk.unsupported")
        appModules = listOf("org.visual")
      }
      mergedModule {
        //          additive = true
        //          excludeRequires(
        //            "java.compiler",
        //            "java.rmi",
        //            "java.xml.bind",
        //            "java.corba",
        //            "org.jetbrains.annotations",
        //            "java.xml.crypto",
        //            "jdk.javadoc",
        //            "org.junit.platform.launcher"
        //          )
      }
    }
  }
}
