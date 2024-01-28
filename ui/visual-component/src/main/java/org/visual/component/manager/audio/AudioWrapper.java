package org.visual.component.manager.audio;

import javafx.scene.media.AudioClip;
import lombok.Getter;
import lombok.Setter;
import org.visual.component.util.FXUtils;

public class AudioWrapper {
    private final AudioClip clip;
    @Getter
    private int count = 0;
    @Setter
    @Getter
    private boolean lastPlayed = false;

    public AudioWrapper(AudioClip clip) {
        this.clip = clip;
    }

    public void play() {
        FXUtils.runOnFX(() -> {
            ++count;
            clip.play();
        });
    }

}
