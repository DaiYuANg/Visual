package org.visual.model.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.visual.model.services.IWorkspaceService;

import java.io.File;

@Slf4j
@Singleton
public class WorkspaceService implements IWorkspaceService {

    private final String workspaceRoot;

    private final File workspace;

    @Inject
    public WorkspaceService(@Named("ApplicationWorkspace") String workspaceRoot){
        this.workspaceRoot = workspaceRoot;
        workspace = new File(workspaceRoot);
        initializeWorkspace();
    }

    private void initializeWorkspace(){
        val isDir = FileUtils.isDirectory(workspace);
        System.err.println(isDir);
    }
}
