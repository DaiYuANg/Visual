package org.visual.model.shared;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class ManifestReader {

    public Map<Object, Object> loadManifest() throws IOException {
        val classLoader = ManifestReader.class.getClassLoader();
        val jarUrl = Optional.ofNullable(classLoader.getResource("META-INF/MANIFEST.MF")).orElseThrow();
        try (InputStream is = jarUrl.openStream()) {
            val manifest = new Manifest(is);
            val attributes = manifest.getMainAttributes();
            return attributes.entrySet().stream()
                    .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }

    public Map<String, String> loadManifestStrings() throws IOException {
        return loadManifest().entrySet().stream()
                .map(objectObjectEntry -> Map.entry(objectObjectEntry.getKey().toString(), objectObjectEntry.getValue().toString()))
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
