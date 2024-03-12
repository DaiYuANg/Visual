const val IDENT_WIDTH = 2

val javafxModules =
  listOf(
    "javafx.base",
    "javafx.controls",
    "javafx.fxml",
    "javafx.graphics",
    "javafx.swing",
    "javafx.media",
    "javafx.web",
  )

val devJvmArguments =
  listOf(
    "-XX:+UseZGC",
    "-XX:+ZGenerational",
    "-XX:+UseCompressedClassPointers",
//    "-verbose:gc",
    "-Dcom.sun.management.jmxremote",
    "-XX:+UseStringDeduplication",
    "-XX:+OptimizeStringConcat",
//    "-Xlog:gc*",
//    "-Xlog:async",
//    "-Xlog:os",
//    "-Xlog:metaspace",
    "-Dcom.sun.management.jmxremote.port=9010",
    "-Dcom.sun.management.jmxremote.ssl=false",
    "-Dcom.sun.management.jmxremote.authenticate=false",
    "-XX:+UseCompressedOops",
    "-XX:MaxInlineLevel=32",
    "-XX:+AlwaysPreTouch",
    "-XX:+TieredCompilation",
    "-XX:SoftRefLRUPolicyMSPerMB=50",
    "-XX:+UseNUMA",
    "-Xmx256M",
    "-Dcom.sun.management.jmxremote",
  )

const val IMPLEMENTATION = "implementation"
const val TEST_IMPLEMENTATION = "testImplementation"
const val COMPILE_ONLY = "compileOnly"
const val ANNOTATION_PROCESSOR = "annotationProcessor"

const val VERSION_KEY = "Version"
const val GIT_HASH_KEY = "Git-Hash"
const val LATEST_TAG_KEY = "Last-Tag"
const val BRANCH_KEY = "Branch"
const val PLANTUML_SUFFIX = "puml"
const val MAIN_CLASS_KEY = "Main-Class"