group = "org.visual.database"

apply<ModulePlugin>()

dependencies {
  testImplementation(libs.testcontainers.mysql)
  testImplementation(libs.testcontainers.postgresql)
  testImplementation(libs.testcontainers.mssqlserver)

  //  implementation(projects.visualCore)
  implementation(libs.apache.common.text)
  implementation(libs.mysql)
  implementation(libs.sqlite)
  implementation(libs.postgres)

  implementation(libs.schemacrawler)
  implementation(libs.schemacrawler.sqlite)
  implementation(libs.schemacrawler.mySQL)
  implementation(libs.schemacrawler.postgreSQL)
  implementation(libs.schemacrawler.sql.server)
}