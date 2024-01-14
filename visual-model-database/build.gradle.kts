plugins {
//    antlr
    `java-library`
    `kotlin-project`
}

group = "org.visual.model.sql"

dependencies {
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation(libs.mavenResloverAPI)
    implementation(libs.mavenResloverImpl)
    implementation(libs.mavenResloverJDK21)
    implementation(libs.mavenResloverSupplier)
    implementation(libs.mavenResloverUtil)
    testImplementation(libs.testcontainersMysql)
    testImplementation(libs.testcontainersMssqlserver)
    testImplementation(libs.testcontainersPostgresql)
    testImplementation(libs.mysql)
    testImplementation(libs.postgres)
    testImplementation(libs.mssql)
//    antlr("org.antlr:antlr4:4.5")
}

//tasks.generateGrammarSource {
//    dependsOn(tasks.compileKotlin)
//}

//tasks.generateTestGrammarSource {
//    dependsOn("kaptGenerateStubsTestKotlin")
//}
java {
    modularity.inferModulePath = true
}