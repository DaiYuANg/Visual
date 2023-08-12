plugins {
    id("org.javamodularity.moduleplugin")
    id("org.openjfx.javafxplugin")
    id("org.beryx.jlink")
    id("io.freefair.sass-java") version "8.2.2"
    id("org.zeroturnaround.gradle.jrebel") version "1.1.12"
}

dependencies {
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.github.almasb:fxgl:17.3")
    implementation("io.vertx:vertx-core:4.4.4")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("com.tangorabox:component-inspector-fx:1.1.0")
    implementation("io.github.mkpaz:atlantafx-base:2.0.1")
    implementation("net.synedra:validatorfx:0.4.2")
    implementation("com.google.jimfs:jimfs:1.3.0")
    implementation("com.dlsc.formsfx:formsfx-core:11.3.2")
    implementation("com.github.iAmGio:froxty:1.4.0")
    implementation("io.github.eckig.grapheditor:grapheditor-core:19.0.0")
    implementation("org.controlsfx:controlsfx:11.1.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.vladsch.flexmark:flexmark-all:0.64.8")
    implementation("com.dlsc.preferencesfx:preferencesfx-core:11.16.0")
    implementation("com.tangorabox:component-inspector-fx:1.1.0")
    implementation("com.google.inject:guice:7.0.0")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
}

application {
    mainModule.set("org.visual.model")
    mainClass.set("org.visual.model.VisualModelApplication")
}

javafx {
    version = "21"
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
    imageZip.set(project.file("${layout.buildDirectory}/distributions/app-${javafx.platform.classifier}.zip"))
    launcher { name = "VisualModeling" }
    group = "distribution"
}

