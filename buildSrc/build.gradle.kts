plugins{
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    `embedded-kotlin`
}

repositories{
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
}

dependencies{
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-lombok:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.9.22")

}