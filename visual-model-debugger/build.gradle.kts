plugins {
  alias(libs.plugins.javafx)
  `java-library`
  application
  `kotlin-project`
  alias(libs.plugins.jlink)
}

group = "org.visual.model.debugger"

val mainClassPath = "org.visual.model.debugger.VisualModelDebugger"

version = "unspecified"

val commonJvmArgs =
    listOf(
        "-XX:+UseZGC",
        "-XX:+ZGenerational",
        "-XX:+UseCompressedClassPointers",
        "-verbose:gc",
        "-XX:+UseStringDeduplication",
        "-XX:+OptimizeStringConcat",
        "-XX:+PrintGCDetails",
        "-XX:+UseCompressedOops",
        "-Xnoclassgc",
        "-XX:MaxInlineLevel=32",
        "-XX:+AlwaysPreTouch",
        "-XX:+TieredCompilation",
        "-XX:SoftRefLRUPolicyMSPerMB=50",
        "-XX:+UseNUMA",
        "--enable-preview")

application {
  mainClass.set(mainClassPath)
  mainModule.set(group.toString())
  applicationDefaultJvmArgs = commonJvmArgs + listOf("-Dprism.verbose=true", "-Djavafx.debug=true")
}

javafx {
  version = libs.versions.javafxVersion.get()
  modules(
      "javafx.controls",
      "javafx.fxml",
      "javafx.graphics",
      "javafx.swing",
      "javafx.media",
      "javafx.web")
  configurations = arrayOf("implementation", "testImplementation")
}

dependencies {
  implementation(projects.ui.visualModelComponent)
  implementation(projects.visualModelShared)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  implementation(libs.gestaltConfig)
  implementation(libs.pcollections)
  implementation(projects.visualModelI18n)
  implementation(projects.libs.event)
}

tasks.jar {
  manifest { attributes("Premain-Class" to "org.visual.model.debugger.VisualModelDebugger") }
}

jlink {
  addExtraDependencies(
      "javafx",
      "kotlin",
      "jackson",
  )
  options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
  enableCds()
  launcher {
    noConsole = true
    name = "visual-model"
    jvmArgs = commonJvmArgs
  }
  imageZip.set(project.file("${project.layout.buildDirectory}/image-zip/visual-model-image.zip"))
  jpackage { appVersion = version.toString() }
  customImage {
    jdkModules = listOf("java.desktop", "java.xml", "jdk.unsupported")
    appModules = listOf(group.toString())
  }
  mergedModule {
    excludeRequires(
        "java.compiler",
        "java.rmi",
        "java.xml.bind",
        "java.corba",
        "org.jetbrains.annotations",
        "java.xml.crypto",
        "jdk.javadoc",
        "org.junit.platform.launcher")
  }
}
