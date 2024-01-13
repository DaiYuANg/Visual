plugins {
    alias(libs.plugins.javafx)
    application
    java
    `kotlin-project`
    id("org.beryx.jlink") version "3.0.1"
    id("org.graalvm.buildtools.native") version "0.9.28"
}

group = "org.visual.model.app"

val mainClassPath = "${group}.VisualModelApplication"

val mainModule = group

javafx {
    version = "21"
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
    testImplementation(libs.guiceTestlib)
    testImplementation(libs.javafxUnitTest)
}

jlink {
    launcher {
        name = "visual-model"
        jvmArgs = listOf(
            "-XX:+UseZGC",
            "-XX:+ZGenerational",
            "-XX:+AlwaysPreTouch"
        )
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
