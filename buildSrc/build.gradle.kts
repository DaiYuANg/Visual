plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    `embedded-kotlin`
    `version-catalog`
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
}


dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.spotlessPlugin)
    implementation(libs.kotlinGradlePlugin)
    implementation(libs.kotlinGradleLombokPlugin)
    implementation(libs.kotlinGradleSerializationPlugin)
    implementation(libs.kotlinGradleNoArgPlugin)
    implementation(libs.kotlinGradleAllOpenPlugin)
    implementation("io.github.goooler.shadow:shadow-gradle-plugin:8.1.3")
}