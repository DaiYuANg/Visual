plugins {
    `kotlin-extra`
    alias(libs.plugins.javafx)
    application
//    id("org.javamodularity.moduleplugin") version "1.8.12"
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

application{
    mainClass.set("org.visual.model.ui.MainKt")
    mainModule.set("org.visual.model.ui")
}

dependencies {
    implementation(libs.directories)
    implementation(libs.kotlinCoroutines)
    implementation(enforcedPlatform(libs.koinBom))
    implementation(libs.koinCore)
//    implementation(projects.visualModelUiComponent)
}
java {
    modularity.inferModulePath.set(true)
}

tasks.compileJava {
//    options.compilerArgumentProviders.add(CommandLineArgumentProvider {
//        listOf("--patch-module", "org.visual.model.ui=${sourceSets["main"].output.asPath}")
//    })
}