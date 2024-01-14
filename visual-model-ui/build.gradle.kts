plugins {
    alias(libs.plugins.javafx)
    `java-library`
    `kotlin-project`
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

dependencies {
    api(libs.oshi)
    testImplementation(libs.javafxUnitTest)
    api(projects.visualModelShared)
    api(libs.fontawesome5)
    api(libs.ikonliJavafx)
    api(libs.fluentuiIcon)
    implementation("org.jgrapht:jgrapht-core:1.5.2")
    implementation("it.unimi.dsi:fastutil:8.5.12")
}