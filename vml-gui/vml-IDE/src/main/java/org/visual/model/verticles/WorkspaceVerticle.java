package org.visual.model.verticles;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.visual.model.functional.WorkspaceFileSystemListener;

import java.io.File;

@Slf4j
@Singleton
public class WorkspaceVerticle extends AbstractVerticle {

    private final String workspaceRoot;

    private final File workspace;

    private final EventBus eventBus;

    private FileAlterationMonitor monitor;

    @SneakyThrows
    @Inject
    public WorkspaceVerticle(@Named("ApplicationWorkspace") String workspaceRoot, EventBus eventBus) {
        log.info("init workspace");
        this.workspaceRoot = workspaceRoot;
        this.eventBus = eventBus;
        workspace = new File(workspaceRoot);
        FileUtils.forceMkdir(workspace);

    }

    @SneakyThrows
    @Override
    public void init(Vertx vertx, Context context) {
        FileAlterationObserver observer = new FileAlterationObserver(workspace);
        observer.addListener(new WorkspaceFileSystemListener());
        monitor = new FileAlterationMonitor(10);
        monitor.addObserver(observer);
        monitor.start();
    }


    @SneakyThrows
    @Override
    public void stop() {
        log.atInfo().log("workspace verticle stopping");
        monitor.stop(5);
    }
}
