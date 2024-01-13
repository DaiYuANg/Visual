package org.visual.model.app.module

import com.google.inject.AbstractModule

class SimpleModule :AbstractModule() {

    override fun configure() {
        bind(String::class.java).toInstance("test")
    }
}