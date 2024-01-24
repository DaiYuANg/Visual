package org.visual.model.component.control.globalscreen;

import com.github.kwhat.jnativehook.GlobalScreen;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import javafx.application.Platform;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.component.control.dialog.StackTraceAlert;
import org.visual.model.component.manager.internal_i18n.InternalI18n;

@Slf4j
public class GlobalScreenUtils {
    private GlobalScreenUtils() {
    }

    @SneakyThrows
    public static void releaseJNativeHookNativeToLibraryPath(InputStream inputStream) {
        releaseJNativeHookNativeToLibraryPath(inputStream, Stream::findAny);
    }

    public static void releaseJNativeHookNativeToLibraryPath(InputStream inputStream,
                                                             Function<Stream<File>, Optional<File>> selectFunc) throws IOException {
        String suffix;
        if (org.visual.model.shared.Platform.platform == org.visual.model.shared.Platform.WINDOWS) {
            suffix = ".dll";
        } else if (org.visual.model.shared.Platform.platform == org.visual.model.shared.Platform.MAC) {
            suffix = ".dylib";
        } else {
            suffix = ".so";
        }

        var javaLibraryPath = System.getProperty("java.library.path", "");
        var soname = "vfx-extracted-JNativeHook";
        log.info("java.library.path: " + javaLibraryPath);
        log.info("dynamic library name: " + soname);

        var stream = Arrays.stream(javaLibraryPath.split(File.pathSeparator))
                .filter(s -> !s.isBlank())
                .map(File::new).filter(File::isDirectory);
        var libpathFile = selectFunc.apply(stream);
        if (libpathFile.isEmpty()) {
            log.debug("no available directory in java.library.path");
            return;
        }
        var prefix = suffix.endsWith(".dll") ? "" : "lib"; // add "lib" prefix for linux and macos
        var f = Path.of(libpathFile.get().getAbsolutePath(), prefix + soname + suffix).toFile();
        if (f.exists()) {
            log.info("JNativeHook tmp dynamic library already exists: " + f.getAbsolutePath());
            //noinspection ResultOfMethodCallIgnored
            f.setExecutable(true);
            setJNativeHookLib(soname);
            return;
        } else {
            var ok = f.createNewFile();
            if (!ok) {
                log.error("failed creating tmp file: " + f.getAbsolutePath());
                return;
            }
            //noinspection ResultOfMethodCallIgnored
            f.setExecutable(true);
            // f.deleteOnExit(); // no, do not do this, just leave it there
        }

        setJNativeHookLib(soname);

        try (inputStream; var fos = new FileOutputStream(f)) {
            var buf = new byte[128 * 1024];
            while (true) {
                int n = inputStream.read(buf);
                if (n == -1) {
                    break;
                }
                fos.write(buf, 0, n);
            }
        }

    }

    private static void setJNativeHookLib(String soname) {
        String key = "jnativehook.lib.name";
        System.setProperty(key, soname);
    }

    private static final Map<Object, Integer> enableKeys = new HashMap<>();

    public static synchronized void enable(Object key) {
        var n = enableKeys.get(key);
        if (n == null) {
            n = 0;
        }
        n += 1;
        enableKeys.put(key, n);

        if (enableKeys.size() == 1 && n == 1) {
            try {
                GlobalScreen.registerNativeHook();
            } catch (Throwable e) {
                if (Platform.isFxApplicationThread()) {
                    StackTraceAlert.show(InternalI18n.get().globalScreenRegisterFailed(), e);
                }
            }
        }
    }

    public static synchronized void disable(Object key) {
        var n = enableKeys.get(key);
        if (n == null) {
            log.info("GlobalScreenUtils.disable is called with " + key + ", but it's not enabled with this key before");
            return;
        }
        n -= 1;
        if (n == 0) {
            enableKeys.remove(key);
            if (enableKeys.isEmpty()) {
                unregister();
            }
        } else {
            enableKeys.put(key, n);
        }
    }

    public static void unregister() {
        try {
            GlobalScreen.unregisterNativeHook();
            GlobalScreen.setEventDispatcher(null);
        } catch (Throwable e) {
            log.error("failed to unregister GlobalScreen", e);
        }
    }
}
