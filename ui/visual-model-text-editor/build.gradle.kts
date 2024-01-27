plugins {
    alias(libs.plugins.javafx)
    `kotlin-project`
}

group = "org.visual.model.text.editor"
version = "unspecified"

dependencies {
    api(projects.ui.visualModelComponent)
    api(projects.module.visualModelShared)
    implementation(projects.ui.visualModelComponentAnnotation)
}

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