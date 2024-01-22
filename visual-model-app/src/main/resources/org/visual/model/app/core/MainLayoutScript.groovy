package org.visual.model.app.core

VisualModelAppContext.INSTANCE.shareData.put("test", "test")
VisualModelAppContext.INSTANCE.shareData.forEach { k, v ->
    {
        System.err.println(k)
        System.err.println(v)
    }
}