plugins {
    kotlin("jvm")
    kotlin("plugin.lombok")
    alias(libs.plugins.javafx)
    application
}

group = "org.visual.model.editor"

javafx {
    modules("javafx.controls", "javafx.fxml", "javafx.web")
}

dependencies {
    implementation(libs.directories)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}