plugins {
    alias(libs.plugins.javafx)
    `java-library`
    id("io.miret.etienne.sass") version ("1.5.0")
    `kotlin-project`
    //    kotlin("jvm")
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
    api(libs.simpleicon)
    api(libs.controlfx)
    api(libs.materialIcons)
    api(projects.libs.jfa)
    api(libs.atlantafx)
    api(libs.devicons)
    api(libs.apacheCommonPool)
    api(libs.animated)
    implementation(libs.jetbrainsMono)
    api(libs.flowless)
    api("com.github.kwhat:jnativehook:2.2.2")
}

tasks.compileSass {
    sourceDir = project.layout.projectDirectory.file("src/main/sass").asFile
    sourceMap = none
}

tasks.build { dependsOn(tasks.compileSass) }

tasks.compileJava { dependsOn(tasks.compileSass) }

tasks.jar { dependsOn(tasks.compileSass) }
