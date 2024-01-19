group = "org.visual.model.fx.lombok"
version = "unspecified"



dependencies {
    implementation("com.google.auto.service:auto-service:1.1.1")
    implementation("com.squareup:javapoet:1.13.0")
    implementation(projects.visualModelAnnotation)
    annotationProcessor("com.google.auto.service:auto-service:1.1.1")
    implementation("org.ow2.asm:asm:9.6")
}
