plugins {
    id("java")
}

group = "org.visual.model.shared"

dependencies {
    api(libs.apacheCommonLang3)
//    implementation(libs.eclipseCollectionsAPI)
//    implementation(libs.eclipseCollections)
//    testImplementation(libs.eclipseCollectionsTestUtil)
//    implementation(libs.eclipseCollectionsForkjoin)
    api(libs.jgrapht)
    api(libs.fastutil)
    implementation("com.fasterxml.jackson.core:jackson-core:2.16.1")
}
