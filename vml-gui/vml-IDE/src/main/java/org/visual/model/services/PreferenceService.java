package org.visual.model.services;

import com.google.inject.Singleton;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.prefs.Preferences;

@Slf4j
@Singleton
public class PreferenceService implements IPreferenceService{
    private final Preferences preferences = Preferences.userRoot();
    @SneakyThrows
    public PreferenceService(){
        log.atInfo().log(preferences.absolutePath());
        System.err.println(preferences.absolutePath());
        preferences.put("test","test");
        preferences.flush();
    }

    public String getWorkspace(){
        return preferences.get("workspace",System.getProperty("user.home"));
    }

    public void putString(String key, @NotNull String value){
        preferences.putByteArray(key,value.getBytes(StandardCharsets.UTF_8));
    }
}
