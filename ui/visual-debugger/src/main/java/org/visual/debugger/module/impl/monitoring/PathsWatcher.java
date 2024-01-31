/*
 * Scenic View,
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler, Matthieu Brouillard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.visual.debugger.module.impl.monitoring;

/*
 * #%L
 * CSSFX
 * %%
 * Copyright (C) 2014 CSSFX by Matthieu Brouillard
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PathsWatcher {
  private WatchService watchService;
  private final Map<String, Map<String, List<Runnable>>> filesActions = new HashMap<>();
  private Thread watcherThread;

  public PathsWatcher() {
    try {
      watchService = FileSystems.getDefault().newWatchService();
    } catch (IOException e) {
      log.atError().log("cannot create WatchService", e);
    }
  }

  public void monitor(Path directory, Path sourceFile, Runnable action) {
    if (watchService != null) {
      log.atInfo().log(
          "registering action {} for monitoring {} in {}",
          System.identityHashCode(action),
          sourceFile,
          directory);
      Map<String, List<Runnable>> fileAction =
          filesActions.computeIfAbsent(
              directory.toString(),
              (p) -> {
                try {
                  directory.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
                return new HashMap<>();
              });

      List<Runnable> actions =
          fileAction.computeIfAbsent(sourceFile.toString(), k -> new LinkedList<>());
      actions.add(action);

      log.atDebug().log(
          "{} CSS modification actions registered for file {}", actions.size(), sourceFile);
    } else {
      log.atWarn().log("no WatchService active, CSS monitoring cannot occur");
    }
  }

  public void watch() {
    watcherThread =
        new Thread(
            () -> {
              log.atInfo().log("starting to monitor physical files");
              while (true) {
                WatchKey key;
                try {
                  key = watchService.take();
                } catch (InterruptedException ex) {
                  return;
                }
                Path directory = ((Path) key.watchable()).toAbsolutePath().normalize();

                key.pollEvents()
                    .forEach(
                        event -> {
                          WatchEvent.Kind<?> kind = event.kind();
                          log.atDebug().log("{}' change detected in directory {}", kind, directory);
                          if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                            // it is a modification
                            @SuppressWarnings("unchecked")
                            WatchEvent<Path> ev = (WatchEvent<Path>) event;
                            Path modifiedFile =
                                directory.resolve(ev.context()).toAbsolutePath().normalize();

                            if (filesActions.containsKey(directory.toString())) {
                              log.atDebug().log(
                                  "file: {} was modified", modifiedFile.getFileName());
                              Map<String, List<Runnable>> filesAction =
                                  filesActions.get(directory.toString());
                              if (filesAction.containsKey(modifiedFile.toString())) {
                                log.atDebug().log("file is monitored");
                                List<Runnable> actions = filesAction.get(modifiedFile.toString());
                                log.atDebug().log(
                                    "{} CSS modification will be performed ", actions.size());

                                for (Runnable action : actions) {
                                  action.run();
                                }
                              } else {
                                log.atDebug().log("file is not monitored");
                              }
                            }
                          }
                        });

                boolean valid = key.reset();
                if (!valid) {
                  break;
                }
              }
            },
            "CSSFX-file-monitor");
    watcherThread.setDaemon(true);
    watcherThread.start();
  }

  public void stop() {
    watcherThread.interrupt();
  }
}
