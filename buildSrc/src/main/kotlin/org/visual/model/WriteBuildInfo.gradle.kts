package org.visual.model

import java.util.Date

tasks.register("writeBuildInfo") {
doLast{
    val buildDate = Date();
    System.getProperty("java.version")
    System.getProperty("java.vendor")
}
}
//task writeBuildInfo {
//    doLast {
//        def buildDate = new Date().toString()
//        def javaVersion = System.getProperty('java.version')
//        def javaVendor = System.getProperty('java.vendor')
//        def javaHome = System.getProperty('java.home')
//
//        def properties = new Properties()
//        properties.put('build.date', buildDate)
//        properties.put('java.version', javaVersion)
//        properties.put('java.vendor', javaVendor)
//        properties.put('java.home', javaHome)
//
//        def outputFile = file("$buildDir/resources/main/build-info.properties")
//        outputFile.parentFile.mkdirs()
//        outputFile.withOutputStream { os ->
//            properties.store(os, 'Build Information')
//        }
//    }
//}
