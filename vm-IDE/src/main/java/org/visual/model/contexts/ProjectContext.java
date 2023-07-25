package org.visual.model.contexts;

import com.google.common.jimfs.Jimfs;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.FileSystem;
import java.nio.file.WatchService;

@Slf4j
public enum ProjectContext {
    PROJECT;

    private FileSystem fileSystem;

    private WatchService watchService;

    @SneakyThrows
    ProjectContext(){
        fileSystem = Jimfs.newFileSystem();
        watchService = fileSystem.newWatchService();
    }

    public void updateFileSystem(FileSystem fileSystem){
        this.fileSystem = fileSystem;
    }
}
