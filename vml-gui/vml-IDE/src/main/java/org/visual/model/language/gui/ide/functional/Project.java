package org.visual.model.language.gui.ide.functional;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;
import lombok.ToString;
import org.visual.model.language.gui.ide.lifecycle.Initialize;

@Getter
@ToString
public class Project implements Initialize, Serializable {

	private final String name;

	private final File projectDirectory;

	private final List<File> projectFiles = new CopyOnWriteArrayList<>();

	private boolean valid = true;

	public Project(String name, File projectDirectory) {
		this.name = name;
		this.projectDirectory = projectDirectory;
	}

	@Override
	public void initialize() {
		valid = projectDirectory.exists();
	}
}
