import org.jetbrains.kotlin.cli.jvm.main

plugins {
    alias(libs.plugins.javafx)
    application
    java
    `kotlin-project`
    id("org.beryx.jlink") version "3.0.1"
    id("org.graalvm.buildtools.native") version "0.9.28"
}

val jvmArgs = listOf(
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
    "-XX:SoftRefLRUPolicyMSPerMB=50"
)
group = "org.visual.model.app"

val mainClassPath = "${group}.VisualModelApplication"

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
    applicationDefaultJvmArgs = jvmArgs
}

dependencies {
    implementation(libs.directories)
    implementation(libs.guice)
    implementation(libs.guiceAssistedinject)
    implementation(libs.guiceThrowingproviders)
    implementation(libs.fontawesome5)
    implementation(libs.ikonliJavafx)
    implementation(libs.fluentuiIcon)
    implementation(projects.visualModelUi)
    implementation(libs.gestaltConfig)
    implementation(libs.gestaltToml)
    implementation(libs.gestaltGuice)
    implementation(libs.gestaltKotlin)
    implementation(projects.visualModelDatabase)
    implementation(projects.visualModelI18n)
    implementation(projects.visualModelGit)
    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
    kapt("info.picocli:picocli-codegen:4.7.5")
    testImplementation(libs.guiceTestlib)
    testImplementation(libs.javafxUnitTest)
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
    }
    customImage {
        jdkModules = listOf("java.desktop", "java.xml", "jdk.unsupported")
        appModules = listOf(group.toString())
    }
    mergedModule {
        excludeRequires(
            "java.compiler", "java.rmi",
            "java.xml.bind", "java.corba", "org.jetbrains.annotations", "java.xml.crypto", "jdk.javadoc"
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
        named("test") {
            buildArgs.add("-O0")
        }
    }
    binaries.all {
        buildArgs.add("--verbose")
    }
}

tasks.compileJava {
    options.compilerArgs = listOf("-Aproject=${project.group}/${project.name}")
}

kapt {
    arguments {
        arg("project", "${project.group}/${project.name}")
    }
}