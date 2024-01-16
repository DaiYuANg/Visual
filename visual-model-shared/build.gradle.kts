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
    implementation("org.jgrapht:jgrapht-core:1.5.2")
    implementation("it.unimi.dsi:fastutil:8.5.12")
}
