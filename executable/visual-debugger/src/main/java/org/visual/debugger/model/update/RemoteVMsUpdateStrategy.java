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
package org.visual.debugger.model.update;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.visual.debugger.api.AppController;
import org.visual.debugger.api.StageController;
import org.visual.debugger.api.UpdateStrategy;
import org.visual.debugger.helper.WorkerThread;
import org.visual.debugger.remote.FXConnector;


@Slf4j
public class RemoteVMsUpdateStrategy extends WorkerThread implements UpdateStrategy {

    private boolean first = true;
    private FXConnector connector;

    AppsRepository repository;
    List<AppController> previous = new ArrayList<>();

    public RemoteVMsUpdateStrategy() {
        super(RemoteVMsUpdateStrategy.class.getName(), 500);
    }

    private List<AppController> getActiveApps() {
        if (first) {
            /**
             * Wait for the server to startup
             */
            first = false;
            while (connector == null) {
                try {
                    Thread.sleep(50);
                } catch (final InterruptedException e) {
                    log.error(e.getLocalizedMessage(),e);
                }
            }
        }

        return connector.connect();
    }

    @Override
    public void finish() {
        super.finish();
        connector.close();
        System.exit(0);
    }

    public void setFXConnector(final FXConnector connector) {
        this.connector = connector;
    }

    @Override
    public void start(final AppsRepository repository) {
        this.repository = repository;
        start();
    }

    @Override
    protected void work() {
        boolean modifications = false;
        final List<AppController> actualApps = getActiveApps();

        final List<StageController> unused = actualApps.stream().map(AppController::getStages).flatMap(Collection::stream).collect(Collectors.toList());

        /**
         * First check new apps
         */
        for (AppController actualApp : actualApps) {
            if (isAppOnArray(actualApp, previous) == -1) {
                repository.appAdded(actualApp);
                unused.removeAll(actualApp.getStages());
                modifications = true;
            }
        }

        /**
         * Then check remove apps
         */
        for (AppController appController : previous)
            if (isAppOnArray(appController, actualApps) == -1) {
                repository.appRemoved(appController);
                modifications = true;
            }

        /**
         * Then check added/removed Stages
         */
        for (AppController actualApp : actualApps) {
            if (isAppOnArray(actualApp, previous) != -1) {
                final List<StageController> stages = actualApp.getStages();
                final List<StageController> previousStages = previous.get(isAppOnArray(actualApp, previous)).getStages();
                for (StageController stage : stages) {
                    if (!isStageOnArray(stage, previousStages)) {
                        repository.stageAdded(stage);
                        unused.remove(stage);
                        modifications = true;
                    }
                }
                for (StageController previousStage : previousStages) {
                    if (!isStageOnArray(previousStage, stages)) {
                        repository.stageRemoved(previousStage);
                        modifications = true;
                    }
                }
            }
        }
        if (modifications) {
            previous = actualApps;
        }
    }

    boolean isStageOnArray(final StageController controller, final List<StageController> stages) {
        return IntStream.range(0, stages.size()).anyMatch(i -> stages.get(i).getID().getStageID() == controller.getID().getStageID());
    }

    int isAppOnArray(final AppController controller, final List<AppController> apps) {
        return IntStream.range(0, apps.size()).filter(i -> apps.get(i).getID() == controller.getID()).findFirst().orElse(-1);
    }
}
