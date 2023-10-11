package org.visual.model.language.gui.ide.service;

import java.util.Set;
import org.visual.model.language.gui.ide.functional.Project;
import org.visual.model.language.gui.ide.lifecycle.Initialize;

public interface IProjectManager extends Initialize {
	void createAProject(String name);

	Set<Project> historyProjects();

	void saveProjects();
}
