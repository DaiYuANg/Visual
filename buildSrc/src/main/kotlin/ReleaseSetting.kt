import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jreleaser.gradle.plugin.JReleaserPlugin

class ReleaseSetting : Plugin<Project> {
  override fun apply(target: Project) {
    target.plugins.apply(JReleaserPlugin::class.java)
    target.plugins.apply(ShadowPlugin::class.java)

    target.tasks.withType(ShadowJar::class.java) { minimize() }
    //    target.plugins.apply()
    //    target.plugins.apply(JlinkPlugin::class.java)
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
    //
    // imageZip.set(project.file("${project.layout.buildDirectory}/image-zip/visual-model-image.zip"))
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
