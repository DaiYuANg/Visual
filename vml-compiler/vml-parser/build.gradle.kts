plugins {
    antlr
    `java-library`
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
    implementation("org.antlr:antlr4-runtime:4.13.1")
    implementation("org.eclipse.collections:eclipse-collections-api:11.1.0")
    implementation("org.eclipse.collections:eclipse-collections:11.1.0")
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
