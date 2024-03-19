plugins {
  antlr
}

group = "org.visual.jdbc"

dependencies {
  implementation(libs.hikariCP)
  implementation(libs.jsqlparser)

  antlr(libs.antlr)
  implementation(libs.guice)

  testImplementation(libs.testcontainersMysql)
  testImplementation(libs.testcontainersMssqlserver)
  testImplementation(libs.testcontainersPostgresql)
  testImplementation(libs.mysql)
  testImplementation(libs.postgres)
  testImplementation(libs.mssql)
}

tasks.generateGrammarSource {
  arguments = arguments + listOf("-visitor", "-long-messages")
//  arguments = arguments + listOf("-package", group)
}