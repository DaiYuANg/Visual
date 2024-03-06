import org.graalvm.buildtools.gradle.NativeImagePlugin
import org.graalvm.buildtools.gradle.dsl.GraalVMExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.JavaApplication
import org.gradle.kotlin.dsl.configure

class RootProjectSetting : Plugin<Project> {
  private val mainClassPath = "org.visual.VisualApplication"

  override fun apply(target: Project) {
    target.plugins.apply(ApplicationPlugin::class.java)
    target.plugins.apply(NativeImagePlugin::class.java)
    target.configure<JavaApplication> {
      mainClass.set(mainClassPath)
      applicationDefaultJvmArgs = commonJvmArgs
    }
    target.configure<GraalVMExtension> {
      toolchainDetection.set(true)
    }
    // jlink {
//  addExtraDependencies(
//    "javafx",
//    "kotlin",
//    "jackson",
//    "picocli",
//  )
//  options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
//  enableCds()
//  launcher {
//    noConsole = true
//    name = convertToCamelCase(project.name)
//    jvmArgs = commonJvmArgs
//  }
//  imageZip.set(project.file("${project.layout.buildDirectory}/image-zip/visual-model-image.zip"))
//  jpackage { appVersion = version.toString() }
//  customImage {
//    jdkModules = listOf("java.desktop", "java.xml", "jdk.unsupported")
//    appModules = listOf(group.toString())
//  }
//  mergedModule {
//    additive = true
//    excludeRequires(
//      "java.compiler",
//      "java.rmi",
//      "java.xml.bind",
//      "java.corba",
//      "org.jetbrains.annotations",
//      "java.xml.crypto",
//      "jdk.javadoc",
//      "org.junit.platform.launcher"
//    )
//  }
// }
  }
}