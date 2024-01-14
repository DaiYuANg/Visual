plugins {
    id("java")
    `kotlin-project`
}

group = "org.visual.model.shared"

dependencies {
    api(libs.apacheCommonLang3)
    implementation("org.apache.commons:commons-pool2:2.12.0")
//    implementation("org.eclipse.collections:eclipse-collections-api:11.1.0")
//    implementation("org.eclipse.collections:eclipse-collections:11.1.0")
//    testImplementation("org.eclipse.collections:eclipse-collections-testutils:11.1.0")
//    implementation("org.eclipse.collections:eclipse-collections-forkjoin:11.1.0")
}
