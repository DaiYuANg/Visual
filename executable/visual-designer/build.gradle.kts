import io.avaje.inject.plugin.AvajeInjectPlugin

plugins {
  application
  java
}

apply<FxProjectPlugin>()
plugins.getPlugin(FxProjectPlugin::class.java).modules("javafx.media", "javafx.web")
apply<KotlinProjectPlugin>()
apply<CommonPlugin>()
apply<FatJarPlugin>()
apply<AvajeInjectPlugin>()
apply<JlinkProject>()
group = "org.visual.designer"

val mainClassPath = "${group}.VisualModelDesigner"

val mainModule = group

application {
  mainClass.set(mainClassPath)
  mainModule.set(group.toString())
  applicationDefaultJvmArgs = commonJvmArgs + listOf("-Dprism.verbose=true", "-Djavafx.debug=true")
}

dependencies {
  implementation(projects.ui.visualI18n)
  implementation(projects.module.visualGit)
  implementation(projects.module.visualShared)
  implementation(projects.executable.visualDebugger)
  implementation(projects.ui.visualComponent)
  implementation(projects.ui.visualGraphEditor)
  implementation(libs.picocli)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.pcollections)
  implementation(libs.avajeValidaor)
  annotationProcessor(libs.avajeValidaorCodegen)
  implementation(rootProject.libs.avajeInject)
  annotationProcessor(rootProject.libs.avajeInjectGenerator)
  implementation(rootProject.libs.gestaltConfig)
  implementation(rootProject.libs.gestaltToml)
  testImplementation(rootProject.libs.avajeInjectTest)
  implementation(rootProject.libs.picocli)
  annotationProcessor(rootProject.libs.picocliCodegen)
}

// jlink {
//    addExtraDependencies(
//        "javafx",
//        "kotlin",
//        "jackson",
//        "picocli",
//    )
//    options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
//    enableCds()
//    launcher {
//        noConsole = true
//        name = convertToCamelCase(project.name)
//        jvmArgs = commonJvmArgs
//    }
//
// imageZip.set(project.file("${project.layout.buildDirectory}/image-zip/visual-model-image.zip"))
//    jpackage { appVersion = version.toString() }
//    customImage {
//        jdkModules = listOf("java.desktop", "java.xml", "jdk.unsupported")
//        appModules = listOf(group.toString())
//    }
//    mergedModule {
//        //        additive = true
//        excludeRequires(
//            "java.compiler",
//            "java.rmi",
//            "java.xml.bind",
//            "java.corba",
//            "org.jetbrains.annotations",
//            "java.xml.crypto",
//            "jdk.javadoc",
//            "org.junit.platform.launcher"
//        )
//    }
// }
