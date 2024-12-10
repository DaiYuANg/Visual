import name.remal.gradle_plugins.lombok.LombokPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class ModulePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    val rootLibs = rootLibs(target)
    target.plugins.apply(LombokPlugin::class)
    target.dependencies {
      add(COMPILE_ONLY, rootLibs.jetbrains.annotation)
      add(TEST_IMPLEMENTATION, enforcedPlatform(rootLibs.testcontainers.bom))
      add(TEST_IMPLEMENTATION, rootLibs.testcontainers)
      add(TEST_IMPLEMENTATION, rootLibs.testcontainers.junit)

      add(IMPLEMENTATION, rootLibs.slf4j)

      add(COMPILE_ONLY, rootLibs.record.builder.core)
      add(ANNOTATION_PROCESSOR, rootLibs.record.builder.processor)

      add(IMPLEMENTATION, rootLibs.avaje.validaor)
      add(IMPLEMENTATION, rootLibs.avaje.validaor.constraints)
      add(ANNOTATION_PROCESSOR, rootLibs.avaje.validaor.codegen)

      add(IMPLEMENTATION, rootLibs.mapstruct)
      add(ANNOTATION_PROCESSOR, rootLibs.mapstruct.processor)

      add(IMPLEMENTATION, rootLibs.vavr)

      add(TEST_IMPLEMENTATION, enforcedPlatform(rootLibs.junit.bom))
      add(TEST_IMPLEMENTATION, rootLibs.junit.api)
      add(TEST_IMPLEMENTATION, rootLibs.junit.juiter)
      add(TEST_IMPLEMENTATION, rootLibs.junit.platform.suite)
      add(TEST_IMPLEMENTATION, rootLibs.junit.perf)
      add(TEST_IMPLEMENTATION, rootLibs.junit.engine)
      add(TEST_IMPLEMENTATION, rootLibs.mockito.core)
      add(TEST_IMPLEMENTATION, rootLibs.mockito.junit)
    }
  }
}