plugins {
  antlr
//  `kotlin-project`
}

group = "org.visual.model.database"

dependencies {
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
  arguments = arguments + listOf("-package", "org.visual.jdbc")
}
