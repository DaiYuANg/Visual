package org.visual.model.language.gui.ide.di.providers;

import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.language.gui.ide.functional.WorkspaceFileSystemListener;

@Slf4j
public class WorkspaceFileSystemListenerProvider implements Provider<WorkspaceFileSystemListener> {
	@Override
	public WorkspaceFileSystemListener get() {
		return new WorkspaceFileSystemListener();
	}
}
