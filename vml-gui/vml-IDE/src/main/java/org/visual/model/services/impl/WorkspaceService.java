package org.visual.model.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
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

    @Inject
    public WorkspaceService(@Named("ApplicationWorkspace") String workspaceRoot){
        log.info("init workspace");
        this.workspaceRoot = workspaceRoot;
        workspace = new File(workspaceRoot);
        initializeWorkspace();
    }

    @SneakyThrows
    private void initializeWorkspace(){
        FileUtils.forceMkdir(workspace);
        FileAlterationObserver observer = new FileAlterationObserver(workspace);
        observer.addListener(new WorkspaceFileSystemListener());
        FileAlterationMonitor monitor = new FileAlterationMonitor(10);
        monitor.addObserver(observer);

        // 启动监视器
        monitor.start();

        // 持续监听，你可以在此处添加逻辑来处理文件变化
        TimeUnit.MINUTES.sleep(5); // 监听5分钟

        // 停止监视器
        monitor.stop();
    }
}
