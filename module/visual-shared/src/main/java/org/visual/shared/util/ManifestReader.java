package org.visual.shared.util;

import static java.util.Objects.requireNonNull;
import static java.util.jar.Attributes.Name.IMPLEMENTATION_TITLE;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.jar.Manifest;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

class ManifestReader {
    private static final ManifestReader INSTANCE = new ManifestReader();

    public static Map<String, String> loadManifestMapWithTitle(String title) {
        return INSTANCE.readManifestMapWithTitle(title);
    }

    public static Manifest loadManifestWithTitle(String title) {
        return INSTANCE.readManifestWithTitle(title);
    }

    private static final String MANIFEST_PATH = "META-INF/MANIFEST.MF";
    private final ClassLoader classloader;

    public ManifestReader() {
        this(Thread.currentThread().getContextClassLoader());
    }

    public ManifestReader(ClassLoader classloader) {
        requireNonNull(classloader);
        this.classloader = classloader;
    }

    public Map<String, String> readManifestMapWithTitle(String title) {
        requireNonNull(title);
        Manifest manifest = readManifestWithProperty(IMPLEMENTATION_TITLE.toString(), title);
        if (manifest == null) {
            return null;
        }
        return manifest.getMainAttributes().entrySet().stream()
                .collect(Collectors.toMap(it -> it.getKey().toString(), it -> it.getValue().toString()));
    }

    public Manifest readManifestWithTitle(String title) {
        requireNonNull(title);
        return readManifestWithProperty(IMPLEMENTATION_TITLE.toString(), title);
    }

    public Manifest readManifestWithProperty(String name, String value) {
        requireNonNull(name);
        return readManifest(candidate -> {
            String actual = candidate.getMainAttributes().getValue(name);
            return Objects.equals(actual, value);
        });
    }

    public Manifest readManifest(Predicate<Manifest> manifestPredicate) {
        requireNonNull(manifestPredicate);
        Enumeration<URL> manifestUrls = findManifestResources();
        while (manifestUrls.hasMoreElements()) {
            URL url = manifestUrls.nextElement();
            Manifest manifest = toManifest(url);
            if (manifestPredicate.test(manifest)) {
                return manifest;
            }
        }
        return null;
    }

    private Enumeration<URL> findManifestResources() {
        try {
            return classloader.getResources(MANIFEST_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Could not open " + MANIFEST_PATH, e);
        }
    }

    @Contract("_ -> new")
    private @NotNull Manifest toManifest(URL manifestUrl) {
        try (InputStream inputStream = manifestUrl.openConnection().getInputStream()) {
            return new Manifest(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to open manifest file: " + manifestUrl, e);
        }
    }
}
