plugins {
    antlr
    `java-library`
    `kotlin-project`
}

group = "org.visual.model.sql"

dependencies {
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation(libs.mavenResloverAPI)
    implementation(libs.mavenResloverImpl)
    implementation(libs.mavenResloverJDK21)
    implementation(libs.mavenResloverSupplier)
    implementation(libs.mavenResloverUtil)
}
