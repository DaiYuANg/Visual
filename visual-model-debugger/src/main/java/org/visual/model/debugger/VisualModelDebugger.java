/*
 * Scenic View,
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler
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
package org.visual.model.debugger;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.debugger.api.AppController;
import org.visual.model.debugger.controller.AppControllerImpl;
import org.visual.model.debugger.controller.StageControllerImpl;
import org.visual.model.debugger.model.attach.AttachHandlerFactory;
import org.visual.model.debugger.model.update.LocalUpdateStrategy;
import org.visual.model.debugger.model.update.RemoteVMsUpdateStrategy;
import org.visual.model.debugger.remote.FXConnectorFactory;
import org.visual.model.debugger.view.ScenicViewGui;
import org.visual.model.ui.util.ScreenUtil;

/**
 * This is the entry point for all different versions of Scenic View.
 */
@Slf4j
public class VisualModelDebugger extends Application {

    public static final String JDK_PATH_KEY = "jdkPath";

//    public static void show(final @NotNull Scene target) {
//        show(target.getRoot());
//    }
//
//    public static void show(final Parent target) {
//        if (target == null) {
//            return;
//        }
//
//        final Stage stage = new Stage();
//
//        // workaround for RT-10714
//        stage.setWidth(1024);
//        stage.setHeight(768);
//        stage.setTitle("Scenic View v" + ScenicViewGui.VERSION);
//
//        final List<AppController> controllers = new ArrayList<>();
//        final AppController aController = new AppControllerImpl();
//        final boolean sceneRoot = target.getScene().getRoot() == target;
//        final StageControllerImpl sController = new StageControllerImpl(target, aController, sceneRoot);
//
//        System.out.println("aController = " + aController);
//        aController.getStages().add(sController);
//        controllers.add(aController);
//
//        final LocalUpdateStrategy updateStrategy = new LocalUpdateStrategy(controllers);
//        ScenicViewGui.show(new ScenicViewGui(updateStrategy, stage), stage);
//    }


    /**************************************************************************
     *
     * runtime discovery start point
     * (Also refer to RuntimeAttach class)
     *
     *************************************************************************/
    public static void premain(final String agentArgs, final Instrumentation instrumentation) {
        // we start up a new thread to take care of initialising Scenic View
        // so that we don't block the loading of the actual application.
        Platform.runLater(() -> new VisualModelDebugger().start());
    }

    @SneakyThrows
    private void start() {
        new VisualModelDebugger().start(new Stage());
    }

    public static void main(final String @NotNull [] args) {
        VisualModelDebugger.launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        // This mode is only available when we are in the commercial Scenic View,
        // so we must start up the license checker and validate
        AttachHandlerFactory.initAttachAPI(stage);
//        System.setProperty(FXConnector.SCENIC_VIEW_VM, "true");

        val strategy = new RemoteVMsUpdateStrategy();

        // workaround for RT-10714
        val size = ScreenUtil.percentOfScreen(0.7);
        stage.setWidth(size.getLeft());
        stage.setHeight(size.getRight());
        stage.setTitle("Scenic View v" + ScenicViewGui.VERSION);
        log.info("Platform running");
        log.info("Launching ScenicView v" + ScenicViewGui.VERSION);
        ScenicViewGui view = new ScenicViewGui(strategy, stage);
        ScenicViewGui.show(view, stage);
        log.info("Startup done");

        log.info("Creating server");
        strategy.setFXConnector(FXConnectorFactory.getConnector());
        log.info("Server done");
    }
}
