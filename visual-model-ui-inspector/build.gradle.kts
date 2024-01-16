plugins {
    alias(libs.plugins.javafx)
}

group = "org.visual.model.ui.inspector"
version = "unspecified"

javafx {
    version = libs.versions.javafxVersion.get()
    modules(
        "javafx.controls",
        "javafx.fxml",
        "javafx.graphics",
        "javafx.swing",
        "javafx.media",
        "javafx.web"
    )
    configurations = arrayOf("implementation", "testImplementation")
}

dependencies{
    implementation(projects.visualModelUi)
    implementation(projects.visualModelShared)
    implementation(libs.avajeInject)
    annotationProcessor(libs.avajeInjectGenerator)
}
