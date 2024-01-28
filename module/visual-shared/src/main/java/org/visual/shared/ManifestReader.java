package org.visual.shared;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import lombok.val;
import org.visual.shared.mapper.ManifestMapper;
import org.visual.shared.pojo.BaseManifest;

@SuppressWarnings({"missing-explicit-ctor"})
public class ManifestReader {

  private static final Supplier<ManifestReader> INSTANCE = ManifestReader::new;

  public static ManifestReader INSTANCE() {
    return INSTANCE.get();
  }

  private static final String MANIFEST_PATH = "META-INF/MANIFEST.MF";

  private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

  public Manifest readManifest() throws IOException {
    val classLoader = ManifestReader.class.getClassLoader();
    val jarUrl = Optional.ofNullable(classLoader.getResource(MANIFEST_PATH)).orElseThrow();
    try (InputStream is = jarUrl.openStream()) {
      return new Manifest(is);
    }
  }

  public Map<Object, Object> readToMap() throws IOException {
    return readManifest().getMainAttributes().entrySet().stream()
        .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  public Map<String, String> loadManifestStrings() throws IOException {
    return readToMap().entrySet().stream()
        .map(
            objectObjectEntry ->
                Map.entry(
                    objectObjectEntry.getKey().toString(), objectObjectEntry.getValue().toString()))
        .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  public BaseManifest loadManifestObject() throws IOException {
    return ManifestMapper.INSTANCE.mapToManifest(loadManifestStrings());
  }
}
