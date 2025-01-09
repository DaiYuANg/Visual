plugins { alias(libs.plugins.javafx) }

apply<ModulePlugin>()

group = "org.visual.editor"

version = "unspecified"

dependencies {
  compileOnly(libs.jetbrains.annotation)
  implementation(projects.visualDataStructure)
  implementation(libs.record.builder.core)
  implementation(libs.eclipse.collections.api)
  implementation(libs.eclipse.collections)
  annotationProcessor(libs.record.builder.processor)
  compileOnly(libs.immutables.value)
  annotationProcessor(libs.immutables.value)
}

javafx {
  modules(*javafxModules.toTypedArray())
  version = libs.versions.javafx.get()
  configurations =
    arrayOf(
      COMPILE_ONLY,
      TEST_IMPLEMENTATION,
    )
}