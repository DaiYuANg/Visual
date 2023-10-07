package org.visual.model.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.vertx.core.eventbus.EventBus;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.visual.model.services.IWorkspaceService;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Slf4j
@Singleton
public class WorkspaceService implements IWorkspaceService {

    private final String workspaceRoot;

    private final File workspace;

    private EventBus eventBus;

    @Inject
    public WorkspaceService(@Named("ApplicationWorkspace") String workspaceRoot, EventBus eventBus) {
        log.info("init workspace");
        this.workspaceRoot = workspaceRoot;
        this.eventBus = eventBus;
        workspace = new File(workspaceRoot);
        initializeWorkspace();
    }

    @SneakyThrows
    private void initializeWorkspace() {
        FileUtils.forceMkdir(workspace);
        FileAlterationObserver observer = new FileAlterationObserver(workspace);
        observer.addListener(new WorkspaceFileSystemListener());
        FileAlterationMonitor monitor = new FileAlterationMonitor(10);
        monitor.addObserver(observer);
        // 启动监视器
        monitor.start();
        eventBus.consumer("shutdown",event -> {
            log.info("accpet shutdown");
            try {
                monitor.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
