plugins {
  antlr
}

group = "org.visual.jdbc"

dependencies {
  implementation(libs.hikariCP)
  implementation(libs.jsqlparser)
  testImplementation(libs.testcontainersMysql)
  testImplementation(libs.testcontainersMssqlserver)
  testImplementation(libs.testcontainersPostgresql)
  testImplementation(libs.mysql)
  testImplementation(libs.postgres)
  testImplementation(libs.mssql)
  antlr(libs.antlr)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)
  testAnnotationProcessor(libs.avajeInjectTestGenerator)
}

tasks.generateGrammarSource {
  arguments = arguments + listOf("-visitor", "-long-messages")
//  arguments = arguments + listOf("-package", group)
}