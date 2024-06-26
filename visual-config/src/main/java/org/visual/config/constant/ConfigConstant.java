package org.visual.config.constant;

import dev.dirs.UserDirectories;
import java.nio.file.Path;

public interface ConfigConstant {
  String CONFIG_FILE_NAME = "visual.toml";

  Path HOME_CONFIG = Path.of(UserDirectories.get().homeDir, CONFIG_FILE_NAME);
}
