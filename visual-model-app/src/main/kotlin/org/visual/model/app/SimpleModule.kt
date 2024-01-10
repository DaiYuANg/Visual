package org.visual.model.app

import com.google.inject.AbstractModule

class SimpleModule :AbstractModule() {

    override fun configure() {
        bind(String::class.java).toInstance("test")
    }
}