package org.visual.model.contexts;

import com.google.common.jimfs.Jimfs;
import java.nio.file.FileSystem;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ProjectContext {
	PROJECT;

	private FileSystem fileSystem;

	private WatchService watchService;

	@Getter
	private final List<java.io.File> projectFiles;

	@Getter
	private final java.io.File root;

	@SneakyThrows
	ProjectContext() {
		root = new java.io.File(System.getProperty("user.home"));
		projectFiles = Arrays.stream(Objects.requireNonNull(root.listFiles())).toList();
		fileSystem = Jimfs.newFileSystem();
		watchService = fileSystem.newWatchService();
	}

	public void updateFileSystem(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}
}
