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

    dependencies{
        val mapstructVersion: String by project
        val junitVersion: String by project
        val slf4jVersion: String by project
        val guiceVersion: String by project
        val guavaVersion: String by project
        val rxjavaVersion:String by project
        implementation("org.mapstruct:mapstruct:$mapstructVersion")
        annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
        implementation("com.google.guava:guava:$guavaVersion")
        implementation("com.google.inject:guice:$guiceVersion")
        implementation("com.google.inject.extensions:guice-grapher:$guiceVersion")
        testImplementation("com.google.inject.extensions:guice-testlib:$guiceVersion")
        implementation("io.reactivex.rxjava3:rxjava:$rxjavaVersion")
    }
}