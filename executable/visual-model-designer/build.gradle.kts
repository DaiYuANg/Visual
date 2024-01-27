import CommonPluginExtension.Companion.convertToCamelCase

plugins {
    application
    java
}

plugins.getPlugin(FxProjectPlugin::class.java).modules(
    "javafx.media",
    "javafx.web"
)

val commonJvmArgs =
    listOf(
        "-XX:+UseZGC",
        "-XX:+ZGenerational",
        "-XX:+UseCompressedClassPointers",
        "-verbose:gc",
        "-XX:+UseLargePages",
        "-XX:+UseStringDeduplication",
        "-XX:+OptimizeStringConcat",
        "-Xlog:gc*",
        "-XX:+UseCompressedOops",
        "-Xnoclassgc",
        "-XX:MaxInlineLevel=32",
        "-XX:+AlwaysPreTouch",
        "-XX:+TieredCompilation",
        "-XX:SoftRefLRUPolicyMSPerMB=50",
        "-XX:+UseNUMA",
        "--enable-preview"
    )

group = "org.visual.model.designer"

val mainClassPath = "${group}.VisualModelDesigner"

val mainModule = group

application {
    mainClass.set(mainClassPath)
    mainModule.set(group.toString())
    applicationDefaultJvmArgs = commonJvmArgs + listOf("-Dprism.verbose=true", "-Djavafx.debug=true")
}

dependencies {
    implementation(projects.module.visualModelDatabase)
    implementation(projects.module.visualModelI18n)
    implementation(projects.module.visualModelGit)
    implementation(projects.module.visualModelShared)
    implementation(projects.executable.visualModelDebugger)
    implementation(projects.ui.visualModelGraphEditor)
    implementation(libs.picocli)
    annotationProcessor(libs.picocliCodegen)
    implementation(libs.pcollections)
    implementation(libs.avajeValidaor)
    annotationProcessor(libs.avajeValidaorCodegen)
}

//jlink {
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
//    imageZip.set(project.file("${project.layout.buildDirectory}/image-zip/visual-model-image.zip"))
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
//}
