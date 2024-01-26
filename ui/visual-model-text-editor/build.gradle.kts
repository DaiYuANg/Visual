plugins {
    alias(libs.plugins.javafx)
}

group = "org.visual.model.text.editor"
version = "unspecified"

dependencies {
    api(projects.ui.visualModelComponent)
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