plugins {
  `java-library`
  application
}

group = "org.visual.debugger"

val mainClassPath = "org.visual.debugger.VisualModelDebugger"

version = "unspecified"

application {
  mainClass.set(mainClassPath)
  mainModule.set(group.toString())
  applicationDefaultJvmArgs = commonJvmArgs + listOf("-Dprism.verbose=true", "-Djavafx.debug=true")
}

//javafx {
//  version = libs.versions.javafxVersion.get()
//  modules(
//      "javafx.controls",
//      "javafx.fxml",
//      "javafx.graphics",
//      "javafx.swing",
//      "javafx.media",
//      "javafx.web")
//  configurations = arrayOf("implementation", "testImplementation")
//}

dependencies {
  implementation("io.github.classgraph:classgraph:4.8.165")
  implementation(projects.ui.visualComponentAnnotation)
    implementation(projects.ui.visualComponent)
  implementation(projects.module.visualShared)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  implementation(libs.gestaltConfig)
  implementation(libs.pcollections)
  implementation(projects.ui.visualI18n)
  implementation(projects.libs.event)
  implementation("net.bytebuddy:byte-buddy:1.14.11")
  implementation(projects.ui.visualTextEditor)
  implementation("tools.profiler:async-profiler:3.0")
}

tasks.jar {
  manifest { attributes("Premain-Class" to "org.visual.model.debugger.VisualModelDebugger") }
}

//jlink {
//  addExtraDependencies(
//      "javafx",
//      "kotlin",
//      "jackson",
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
//    excludeRequires(
//        "java.compiler",
//        "java.rmi",
//        "java.xml.bind",
//        "java.corba",
//        "org.jetbrains.annotations",
//        "java.xml.crypto",
//        "jdk.javadoc",
//        "org.junit.platform.launcher")
//  }
//}
