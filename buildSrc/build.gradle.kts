plugins{
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    `embedded-kotlin`
    `version-catalog`
}

repositories{
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
}

dependencies{
    implementation(libs.kotlinGradlePlugin)
    implementation(libs.kotlinGradleLombokPlugin)
    implementation(libs.kotlinGradleSerializationPlugin)
    implementation(libs.kotlinGradleNoArgPlugin)
    implementation(libs.kotlinGradleAllOpenPlugin)
    implementation(libs.kotlinGradleSpringPlugin)
}