plugins {
    alias(libs.plugins.javafx)
    `java-library`
    kotlin("jvm")
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


java {
    modularity.inferModulePath.set(true)
}

tasks.compileJava{
    options.compilerArgumentProviders.add(CommandLineArgumentProvider {
        // Provide compiled Kotlin classes to javac – needed for Java/Kotlin mixed sources to work
        listOf("--patch-module", "org.visual.model.ui=${sourceSets["main"].output.asPath}")
    })
}
