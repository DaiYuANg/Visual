plugins {
    id("org.javamodularity.moduleplugin")
    id("org.openjfx.javafxplugin")
    id("org.beryx.jlink")
}

repositories{
    mavenCentral()
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.almasb:fxgl:17.3")
    implementation("io.github.palexdev:materialfx:11.16.1")
    implementation("io.vertx:vertx-core:4.4.4")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
    implementation("io.github.mkpaz:atlantafx-base:2.0.1")
    implementation("com.github.Dansoftowner:jSystemThemeDetector:3.6")
    implementation("net.synedra:validatorfx:0.4.2")
}

application {
    mainModule.set("org.visual.model")
    mainClass.set("org.visual.model.VisualModelApplication")
}

javafx {
    version = "20"
    modules = listOf(
        "javafx.controls",
        "javafx.fxml",
        "javafx.media",
        "javafx.media",
        "javafx.web",
        "javafx.swing"
    )
}

jlink {
    imageZip.set(project.file("$buildDir/distributions/app-${javafx.platform.classifier}.zip"))
    launcher { name = "VisualModeling" }
    group = "distribution"
}