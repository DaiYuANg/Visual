plugins {
    alias(libs.plugins.javafx)
    application
    java
    groovy
    id("org.beryx.jlink") version "3.0.1"
    id("org.graalvm.buildtools.native") version "0.9.28"
    `kotlin-project`
}

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
        "--enable-preview"
    )

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
        "javafx.web"
    )
    configurations = arrayOf("implementation", "testImplementation")
}

application {
    mainClass.set(mainClassPath)
    mainModule.set(group.toString())
    applicationDefaultJvmArgs = commonJvmArgs + listOf("-Dprism.verbose=true", "-Djavafx.debug=true")
}

dependencies {
    implementation(libs.avajeInject)
    annotationProcessor(libs.avajeInjectGenerator)
    implementation(projects.ui.visualModelComponent)
    implementation(libs.gestaltConfig)
    implementation(libs.gestaltToml)
    implementation(projects.visualModelDatabase)
    implementation(projects.visualModelI18n)
    implementation(projects.visualModelGit)
    implementation(projects.visualModelShared)
    testImplementation(libs.avajeInjectTest)
    testImplementation(libs.javafxUnitTest)
    implementation(projects.visualModelDebugger)
    implementation(projects.visualModelAnnotation)
    implementation(projects.ui.visualModelGraphEditor)
    implementation(libs.picocli)
    annotationProcessor(libs.picocliCodegen)

    implementation(libs.pcollections)
    implementation(libs.avajeValidaor)
    implementation("org.apache.groovy:groovy-all:5.0.0-alpha-5") {
        exclude("org.apache.groovy", "groovy-groovydoc")
    }
    annotationProcessor(libs.avajeValidaorCodegen)
}

jlink {
    addExtraDependencies(
        "javafx",
        "kotlin",
        "jackson",
        "picocli",
    )
    options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
    enableCds()
    launcher {
        noConsole = true
        name = "visual-model"
        jvmArgs = commonJvmArgs
    }
    imageZip.set(project.file("${project.layout.buildDirectory}/image-zip/visual-model-image.zip"))
    jpackage {
        appVersion = version.toString()
    }
    customImage {
        jdkModules = listOf("java.desktop", "java.xml", "jdk.unsupported")
        appModules = listOf(group.toString())
    }
    mergedModule {
//        additive = true
        excludeRequires(
            "java.compiler",
            "java.rmi",
            "java.xml.bind",
            "java.corba",
            "org.jetbrains.annotations",
            "java.xml.crypto",
            "jdk.javadoc",
            "org.junit.platform.launcher"
        )
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
