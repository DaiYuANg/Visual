plugins {
    alias(libs.plugins.javafx)
}

group = "org.visual.model.ui.component"
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
    api(libs.apacheCommonLang3)
    api(libs.fontawesome5)
    api(libs.ikonliJavafx)
    api(libs.fluentuiIcon)
}

