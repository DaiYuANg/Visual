plugins {
    antlr
    `java-library`
}

dependencies {
    antlr("org.antlr:antlr4:4.5")
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
