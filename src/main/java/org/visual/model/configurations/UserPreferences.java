package org.visual.model.configurations;

import java.util.prefs.Preferences;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class UserPreferences {
    public static final Preferences userPrefs = Preferences.userNodeForPackage(UserPreferences.class);
    private double size;
}
