group = "org.visual.model.fx.lombok"
version = "unspecified"



dependencies {
    implementation("com.google.auto.service:auto-service:1.1.1")
    implementation("com.squareup:javapoet:1.13.0")
    implementation(projects.visualModelAnntation)
    annotationProcessor("com.google.auto.service:auto-service:1.1.1")
    implementation("net.bytebuddy:byte-buddy:1.14.11")
}
