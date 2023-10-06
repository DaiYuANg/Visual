package org.visual.model.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.visual.model.contexts.TasksContext;

import java.io.File;

@Slf4j
public class WorkspaceFileSystemListener implements FileAlterationListener {
    @Override
    public void onDirectoryChange(File directory) {

    }

    @Override
    public void onDirectoryCreate(File directory) {

    }

    @Override
    public void onDirectoryDelete(File directory) {

    }

    @Override
    public void onFileChange(File file) {

    }

    @Override
    public void onFileCreate(File file) {
        TasksContext.INSTANCE.run(()->log.atInfo().log(file.getAbsolutePath()));
    }

    @Override
    public void onFileDelete(File file) {

    }

    @Override
    public void onStart(FileAlterationObserver observer) {

    }

    @Override
    public void onStop(FileAlterationObserver observer) {

    }
}
