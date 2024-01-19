plugins {
    alias(libs.plugins.javafx)
    `java-library`
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
    api(projects.visualModelAnnotation)
    annotationProcessor(projects.visualModelCodegen)
    compileOnly(projects.visualModelCodegen)
}