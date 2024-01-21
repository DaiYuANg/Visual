plugins {
  alias(libs.plugins.javafx)
  application
  java
  id("org.beryx.jlink") version "3.0.1"
  id("org.graalvm.buildtools.native") version "0.9.28"
}

val jvmArgs =
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

group = "org.visual.model.app"

val mainClassPath = "${group}.core.VisualModelApplication"

val mainModule = group

javafx {
  version = libs.versions.javafxVersion.get()
  modules(
      "javafx.controls",
      "javafx.fxml",
      "javafx.graphics",
      "javafx.swing",
      "javafx.media",
  )
  configurations = arrayOf("implementation", "testImplementation")
}

application {
  mainClass.set(mainClassPath)
  mainModule.set(group.toString())
  applicationDefaultJvmArgs = jvmArgs + listOf("-Dprism.verbose=true", "-Djavafx.debug=true")
}

dependencies {
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  implementation(projects.ui.visualModelComponent)
  implementation(libs.gestaltConfig)
  implementation(libs.gestaltToml)
//  implementation(projects.visualModelDatabase)
  implementation(projects.visualModelI18n)
  implementation(projects.visualModelGit)
  implementation(projects.visualModelShared)
  testImplementation(libs.avajeInjectTest)
  testImplementation(libs.javafxUnitTest)
  implementation(projects.ui.visualModelDebugger)
  implementation(projects.visualModelAnnotation)
  implementation(projects.ui.visualModelGraphEditor)
  implementation(libs.picocli)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.jacksonCore)
  implementation(libs.jacksonDatabind)
  implementation(libs.jacksonAnnotations)
  implementation(libs.pcollections)
  implementation(libs.avajeValidaor)
  annotationProcessor(libs.avajeValidaorCodegen)
}

jlink {
  options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
  enableCds()
  launcher {
    noConsole = true
    name = "visual-model"
    jvmArgs = jvmArgs
  }
  imageZip.set(project.file("${project.layout.buildDirectory}/image-zip/visual-model-image.zip"))
  jpackage {
    appVersion = version.toString()
    //        icon = "../assets/logo.icns"
  }
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
        "jdk.javadoc")
  }
}

graalvmNative {
  toolchainDetection.set(true)
  binaries {
    named("main") {
      imageName.set(rootProject.name)
      mainClass.set(mainClassPath)
      buildArgs.add("-O4")
      sharedLibrary.set(false)
      useFatJar.set(true)
      resources {
        autodetection {
          enabled.set(true)
          restrictToProjectDependencies.set(false)
        }
      }
      quickBuild.set(true)
    }
    named("test") { buildArgs.add("-O0") }
  }
  binaries.all { buildArgs.add("--verbose") }
}

tasks.jar { manifest.attributes["JavaFx-Version"] = libs.versions.javafxVersion.get() }

java { modularity.inferModulePath = true }
