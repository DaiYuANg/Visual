plugins {
    alias(libs.plugins.javafx)
    `java-library`
    id("io.miret.etienne.sass") version ("1.5.0")
    kotlin("jvm")
}

group = "org.visual.model.ui"

version = "unspecified"

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

tasks.jar { manifest { "JavaFxVersion" to libs.versions.javafxVersion.get() } }

dependencies {
    api(libs.oshi)
    testImplementation(libs.javafxUnitTest)
    api(projects.visualModelShared)
    api(libs.fontawesome5)
    api(libs.ikonliJavafx)
    api(libs.fluentuiIcon)
    api(projects.visualModelAnnotation)
    api(libs.simpleicon)
    api(libs.controlfx)
    api(libs.materialIcons)
    annotationProcessor(projects.visualModelCodegen)
    api(projects.libs.jfa)
    api(libs.atlantafx)
    api(libs.devicons)
    api("eu.iamgio:animated:1.3.0")
    api("org.webjars.npm:fontsource__jetbrains-mono:4.5.11")
    implementation(kotlin("stdlib"))
}

tasks.compileSass {
    sourceDir = project.layout.projectDirectory.file("src/main/sass").asFile
    sourceMap = none
}

tasks.build {
    dependsOn(tasks.compileSass)
}

tasks.compileJava {
    dependsOn(tasks.compileSass)
    options.compilerArgumentProviders.add(CommandLineArgumentProvider {
        // Provide compiled Kotlin classes to javac – needed for Java/Kotlin mixed sources to work
        listOf("--patch-module", "${group}=${sourceSets["main"].output.asPath}")
    })
}

tasks.jar {
    dependsOn(tasks.compileSass)
}
