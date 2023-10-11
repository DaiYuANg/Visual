package org.visual.model.language.gui.ide.functional;

import io.vertx.core.eventbus.EventBus;
import jakarta.inject.Inject;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.visual.model.language.gui.ide.contexts.AsyncContext;
import org.visual.model.language.gui.ide.di.DIContainer;

@Slf4j
public class WorkspaceFileSystemListener implements FileAlterationListener {

	@Inject
	private EventBus eventBus;

	public WorkspaceFileSystemListener() {
		DIContainer.INSTANCE.getInjector().injectMembers(this);
	}

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
		AsyncContext.INSTANCE.run(() -> log.atInfo().log(file.getAbsolutePath()));
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
