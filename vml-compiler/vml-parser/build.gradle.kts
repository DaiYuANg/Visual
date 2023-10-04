plugins {
    antlr
    `java-library`
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
}

sourceSets {
    main {
        antlr {
            srcDirs("src/main/antlr")

        }
    }
}

tasks.generateGrammarSource {
    maxHeapSize = "2048m"
    arguments = arguments + listOf("-visitor", "-long-messages")
}
