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
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}