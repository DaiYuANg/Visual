plugins {
    kotlin("jvm")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.visual.model.editor"
version = "unspecified"

javafx {
    modules("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation("dev.dirs:directories:26")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}