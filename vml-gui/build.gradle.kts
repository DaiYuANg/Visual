plugins {
    id("org.openjfx.javafxplugin")
    id("io.freefair.sass-java") version "8.2.2"
}
val javafxVersion:String by project
subprojects {
    apply {
        plugin("org.openjfx.javafxplugin")
        plugin("io.freefair.sass-java")
    }

    javafx {
        version = javafxVersion
        modules = listOf(
            "javafx.controls",
            "javafx.fxml",
            "javafx.media",
            "javafx.media",
            "javafx.web",
            "javafx.swing"
        )
    }
}