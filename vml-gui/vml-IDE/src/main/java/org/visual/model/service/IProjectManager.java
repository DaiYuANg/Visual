package org.visual.model.service;

import org.visual.model.lifecycle.Initialize;

public interface IProjectManager extends Initialize {
    void createAProject(String name);

    void saveProjects();
}
