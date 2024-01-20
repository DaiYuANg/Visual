plugins {
  antlr
  `java-library`
}

group = "org.visual.model.database"

dependencies {
  implementation(libs.hikariCP)
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
  antlr(libs.antlr)
}

tasks.generateGrammarSource {
  arguments = arguments + listOf("-visitor", "-long-messages")
  arguments = arguments + listOf("-package", "org.visual.model.database")
}
