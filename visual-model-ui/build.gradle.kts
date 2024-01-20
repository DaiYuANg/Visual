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

tasks.jar {
    manifest {
        "JavaFxVersion" to libs.versions.javafxVersion.get()
    }
}

dependencies {
    api(libs.oshi)
    testImplementation(libs.javafxUnitTest)
    api(projects.visualModelShared)
    api(libs.fontawesome5)
    api(libs.ikonliJavafx)
    api(libs.fluentuiIcon)
    api(projects.visualModelAnnotation)
    api(libs.simpleicon)
    api("org.controlsfx:controlsfx:11.2.0")
    annotationProcessor(projects.visualModelCodegen)
    api(projects.libs.jfa)
    api("com.github.oshi:oshi-core-java11:6.4.11")
    api("io.github.mkpaz:atlantafx-base:2.0.1")
}