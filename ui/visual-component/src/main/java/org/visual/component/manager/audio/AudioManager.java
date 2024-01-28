package org.visual.component.manager.audio;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import javafx.scene.media.AudioClip;

public class AudioManager {
    private static final AudioManager instance = new AudioManager();

    public static AudioManager get() {
        return instance;
    }

    private AudioManager() {
    }

    private final Map<String, AudioClip> map = new ConcurrentHashMap<>();
    private final WeakHashMap<String, AudioClip> weakMap = new WeakHashMap<>();

    public AudioClip loadAudio(String path) {
        try {
            return loadAudio(path, false);
        } catch (Exception ignore) {
            return null;
        }
    }

    @SuppressWarnings("RedundantThrows")
    public AudioClip loadAudio(String path, boolean throwException) throws Exception {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        var audio = map.get(path);
        if (audio == null) {
            audio = weakMap.get(path);
        }
        if (audio != null) {
            return audio;
        }
        try {
            var res = getClass().getClassLoader().getResource(path);
            if (res == null) {
                if (throwException) {
                    throw new IOException("cannot find audio " + path);
                }
                return null;
            }
            audio = new AudioClip(res.toExternalForm());
        } catch (Exception e) {
            if (throwException) {
                throw e;
            }
            return null;
        }
        map.put(path, audio);
        return audio;
    }

    public AudioClip weakRefAudio(String path) {
        var audio = map.remove(path);
        if (audio == null) {
            return null;
        }
        weakMap.put(path, audio);
        return audio;
    }

    public void removeAudio(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        if (!map.containsKey(path) && !weakMap.containsKey(path)) {
            return;
        }
        map.remove(path);
        weakMap.remove(path);
    }
}
