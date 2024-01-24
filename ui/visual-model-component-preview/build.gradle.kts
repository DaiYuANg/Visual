plugins {
    alias(libs.plugins.javafx)
}

group = "org.visual.model.component.preview"
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

dependencies {
//    implementation("io.github.classgraph:classgraph:4.8.165")
    implementation(projects.ui.visualModelComponentAnnotation)
    implementation(projects.ui.visualModelComponent)
}
