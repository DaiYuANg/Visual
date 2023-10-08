package org.visual.model.di.providers;

import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.functional.WorkspaceFileSystemListener;

@Slf4j
public class WorkspaceFileSystemListenerProvider implements Provider<WorkspaceFileSystemListener> {
    @Override
    public WorkspaceFileSystemListener get() {
        return new WorkspaceFileSystemListener();
    }
}
