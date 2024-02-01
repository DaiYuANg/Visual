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
package org.visual.debugger.remote;

import java.lang.instrument.Instrumentation;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.component.util.NodeUtil;
import org.visual.debugger.api.FXConnectorEventDispatcher;
import org.visual.debugger.api.StageController;
import org.visual.debugger.controller.*;
import org.visual.debugger.details.DetailPaneType;
import org.visual.debugger.module.api.CSSFXEvent;
import org.visual.debugger.module.api.URIToPathConverters;
import org.visual.debugger.module.impl.CSSFXMonitor;
import org.visual.debugger.module.impl.log.CSSFXLogger;
import org.visual.debugger.node.SVNode;

@Slf4j
public class RuntimeAttach {

  private static boolean debug = true;
  private static RemoteApplicationImpl application;

  public static void agentmain(final String agentArgs, final Instrumentation instrumentation) {
    init(agentArgs, instrumentation);
  }

  private static void init(final @NotNull String agentArgs, final Instrumentation instrumentation) {
    /**
     * Do it first to see first trace, this should be change if any other boolean argument is
     * included in the future
     */
    debug = agentArgs.contains("true");
    debug("Launching agent server on:" + agentArgs);

    if (debug) {
      CSSFXLogger.console();
      CSSFXLogger.setLogLevel(CSSFXLogger.LogLevel.INFO);
    } else {
      CSSFXLogger.noop();
    }

    try {
      final String[] args = agentArgs.split(":");

      final int port = Integer.parseInt(args[0]);
      final int serverPort = Integer.parseInt(args[1]);
      final int appID = Integer.parseInt(args[2]);
      debug = Boolean.parseBoolean(args[3]);
      final AppControllerImpl acontroller = new AppControllerImpl(appID, args[2]);

      final CSSFXMonitor cssMonitor = new CSSFXMonitor();
      cssMonitor.addAllConverters(URIToPathConverters.DEFAULT_CONVERTERS);
      ObservableList<Window> applicationWindows = FXCollections.observableArrayList();
      cssMonitor.setWindows(applicationWindows);

      final RemoteApplication application =
          new RemoteApplication() {
            final List<StageControllerImpl> finded = new ArrayList<>();
            final List<StageControllerImpl> controller = new ArrayList<>();

            @Override
            public void update(final StageID id) {
              Platform.runLater(() -> getSC(id).update());
            }

            @Override
            public void configurationUpdated(final StageID id, final Configuration configuration)
                throws RemoteException {
              Platform.runLater(() -> getSC(id).configurationUpdated(configuration));
            }

            @Override
            public void setEventDispatcher(
                final StageID id, final FXConnectorEventDispatcher dispatcher)
                throws RemoteException {
              Platform.runLater(
                  () -> {
                    /** Move from finded to controllers */
                    finded.stream()
                        .filter(stageController -> stageController.getID().equals(id))
                        .findFirst()
                        .ifPresent(controller::add);
                    getSC(id).setEventDispatcher(dispatcher);

                    if (getSC(id) instanceof StageControllerImpl sci) {
                      // Now there is a dispatcher, we can notify existing
                      // monitored CSS

                      Consumer<CSSFXEvent<?>> sciEventListener = sci.getCSSFXEventListener();
                      cssMonitor.allKnownStylesheets().stream()
                          .filter(
                              t -> {
                                Parent p = NodeUtil.parentOf(t.getParent());
                                if (p == null && t.getScene() != null) {
                                  p = t.getScene().getRoot();
                                }

                                final int stageRootID = sci.getID().getStageID();
                                final int hashCode = Objects.requireNonNull(p).hashCode();
                                // TODO weakness: stages/window are
                                // identified with root of scene
                                // (that can change)
                                return (stageRootID == hashCode);
                              })
                          .forEach(
                              ms ->
                                  sciEventListener.accept(
                                      CSSFXEvent.newEvent(
                                          CSSFXEvent.EventType.STYLESHEET_MONITORED, ms)));
                    }
                  });
            }

            @Override
            public StageID @NotNull [] getStageIDs() throws RemoteException {
              finded.clear();

              ObservableList<Window> windows = Window.getWindows();
              for (Window window : windows) {
                if (ConnectorUtils.acceptWindow(window)) {
                  debug("Local JavaFX Stage found:" + ((Stage) window).getTitle());
                  final StageControllerImpl scontroller =
                      new StageControllerImpl((Stage) window, acontroller);
                  scontroller.setRemote(true);
                  finded.add(scontroller);
                  if (!applicationWindows.contains(window)) {
                    final Consumer<CSSFXEvent<?>> cssfxEventListener =
                        scontroller.getCSSFXEventListener();
                    cssMonitor.addEventListener(cssfxEventListener);
                    applicationWindows.add(window);
                  }
                }
              }

              final StageID[] ids = new StageID[finded.size()];
              for (int i = 0; i < ids.length; i++) {
                ids[i] = finded.get(i).getID();
              }
              return ids;
            }

            @Override
            public void close(final StageID id) throws RemoteException {
              Platform.runLater(
                  () -> {
                    /** Special for closing the server */
                    if (id == null) {
                      cssMonitor.stop();
                      IntStream.range(0, controller.size()).forEach(i -> controller.get(i).close());
                      controller.clear();
                    } else {
                      final StageController c = getSC(id, true);
                      if (c instanceof StageControllerImpl sci) {
                        cssMonitor.removeEventListener(sci.getCSSFXEventListener());
                      }
                      if (c != null) {
                        c.close();
                      }
                    }
                  });
            }

            @Override
            public void setSelectedNode(final StageID id, final SVNode value)
                throws RemoteException {
              Platform.runLater(
                  () -> {
                    debug(
                        "Setting selected node:"
                            + (value != null
                                ? (" id:" + value.getNodeId() + " class:" + value.getClass())
                                : ""));
                    final StageController sc = getSC(id);
                    if (sc != null) sc.setSelectedNode(value);
                  });
            }

            @Override
            public void removeSelectedNode(final StageID id) throws RemoteException {
              Platform.runLater(
                  () -> {
                    final StageController sc = getSC(id);
                    if (sc != null) sc.removeSelectedNode();
                  });
            }

            @Override
            public void setDetail(
                final StageID id,
                final DetailPaneType detailType,
                final int detailID,
                final String value) {
              Platform.runLater(() -> getSC(id).setDetail(detailType, detailID, value));
            }

            @Override
            public void animationsEnabled(final StageID id, final boolean enabled)
                throws RemoteException {
              Platform.runLater(() -> getSC(id).animationsEnabled(enabled));
            }

            @Override
            public void updateAnimations(final StageID id) throws RemoteException {
              Platform.runLater(() -> getSC(id).updateAnimations());
            }

            @Override
            public void pauseAnimation(final StageID id, final int animationID)
                throws RemoteException {
              Platform.runLater(() -> getSC(id).pauseAnimation(animationID));
            }

            private StageController getSC(final StageID id) {
              return getSC(id, false);
            }

            private StageController getSC(final StageID id, final boolean remove) {
              for (int i = 0; i < controller.size(); i++) {
                if (controller.get(i).getID().equals(id)) {
                  return remove ? controller.remove(i) : controller.get(i);
                }
              }
              return null;
            }

            @Override
            public void close() throws RemoteException {
              RuntimeAttach.application.close();
            }
          };

      debug = false;
      RuntimeAttach.application = new RemoteApplicationImpl(application, port, serverPort);
      cssMonitor.start();
    } catch (final RemoteException e) {
      log.error(e.getMessage(), e);
    }
  }

  private static void debug(String msg) {
    if (debug) {
      System.out.println(msg);
    }
  }
}