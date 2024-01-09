plugins {
    alias(libs.plugins.javafx)
}

group = "org.visual.model.ui.component"
version = "unspecified"

repositories {
    mavenCentral()
}

javafx {
    modules(
        "javafx.controls",
        "javafx.fxml",
        "javafx.web",
        "javafx.graphics",
        "javafx.swing",
        "javafx.media",
    )
}

dependencies {
}

