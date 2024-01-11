plugins {
    alias(libs.plugins.javafx)
    `java-library`
    `kotlin-project`
}

group = "org.visual.model.ui"
version = "unspecified"

javafx {
    version = "21"
    modules(
        "javafx.controls",
        "javafx.fxml",
        "javafx.web",
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
}