plugins {
    antlr
    application
}

dependencies {
    antlr("org.antlr:antlr4:4.5")
    implementation("com.vladsch.flexmark:flexmark-all:0.64.8")
}

sourceSets {
    main {
        antlr {
            srcDirs("src/main/antlr")
        }
    }
}

tasks.generateGrammarSource {
    maxHeapSize = "1024m"
    arguments = arguments + listOf("-visitor", "-long-messages")
}
