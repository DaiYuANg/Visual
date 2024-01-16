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
    implementation(libs.jgrapht)
    implementation(libs.fastutil)
    implementation(libs.apacheCommonLang3)
}
