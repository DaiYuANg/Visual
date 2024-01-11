plugins {
    id("java")
    `kotlin-project`
}

group = "org.visual.model.shared"

dependencies {
    api(libs.apacheCommonLang3)
    api(libs.caffine)
}
