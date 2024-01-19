package org.visual.model.shared;

import lombok.SneakyThrows;
import lombok.val;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ManifestReader {

    private final HashMap<Object, Object> internal = new HashMap<>();

    @SneakyThrows
    public ManifestReader() {
        val classLoader = ManifestReader.class.getClassLoader();
        val jarUrl = Optional.ofNullable(classLoader.getResource("META-INF/MANIFEST.MF")).orElseThrow();
        try (InputStream is = jarUrl.openStream()) {
            val manifest = new Manifest(is);
            val attributes = manifest.getMainAttributes();
        }
    }
}
