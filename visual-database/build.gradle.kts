import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

group = "org.visual.database"

plugins { alias(libs.plugins.javamodularity) }

apply<ModulePlugin>()

dependencies {
  testImplementation(libs.testcontainers.mysql)
  testImplementation(libs.testcontainers.postgresql)
  testImplementation(libs.testcontainers.mssqlserver)

  implementation(projects.visualCore)
  implementation(libs.apache.common.text)
  implementation(libs.mysql)
  implementation(libs.sqlite)
  implementation(libs.postgres)

  implementation(libs.schemacrawler)
  implementation(libs.schemacrawler.sqlite)
  implementation(libs.schemacrawler.mySQL)
  implementation(libs.schemacrawler.postgreSQL)
}

tasks.compileJava {
  extensions.configure<CompileModuleOptions> { addModules = listOf("schemacrawler") }
}