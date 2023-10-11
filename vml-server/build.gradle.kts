plugins {
    id("java")
}

group = "org.visual.model.lsp"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    val vertxVersion:String by project
    implementation("io.vertx:vertx-core:$vertxVersion")
}

tasks.test {
    useJUnitPlatform()
}