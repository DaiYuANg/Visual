package org.visual.model.language.gui.ide.service;

import static org.visual.model.language.gui.ide.constants.ProjectConstant.*;

import com.google.inject.Singleton;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.*;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.jetbrains.annotations.NotNull;
import org.visual.model.language.gui.ide.functional.Project;

@Slf4j
@Singleton
@NoArgsConstructor(force = true)
public class ProjectManager implements IProjectManager {

    private final Set<Project> currentProjects = new CopyOnWriteArraySet<>();

    private final CopyOnWriteArraySet<Project> historyProjects = new CopyOnWriteArraySet<>();

    private final File saved;

    private final String workspacePath;

    private final IOFileFilter prefixFilter = new PrefixFileFilter(PersistencePrefix.getValue());
    private final IOFileFilter suffixFilter = new SuffixFileFilter(PersistenceSuffix.getValue());
    private final IOFileFilter combinedFilter = new AndFileFilter(prefixFilter, suffixFilter);

    @SneakyThrows
    @Inject
    public ProjectManager(@Named("ApplicationData") String saved, @Named("ApplicationWorkspace") String workspacePath) {
        val savedFile = Path.of(saved, "projects").toFile();
        FileUtils.forceMkdir(savedFile);
        this.saved = savedFile;
        this.workspacePath = workspacePath;
    }

    @Override
    public void createAProject(String name) {
    }

    @Override
    public void initialize() {
        loadHistoryProject();
    }

    private void loadHistoryProject() {
        val savedProjects = FileUtils.listFiles(saved, combinedFilter, null);
        val converted = savedProjects.stream().map(r -> {
                    try {
                        val oss = new ObjectInputStream(new FileInputStream(r));
                        return (Project) oss.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.fillInStackTrace();
                        log.error(e.getMessage(), e.fillInStackTrace());
                        return null;
                    }
                }).filter(Objects::nonNull)
                .collect(Collectors.toCollection(CopyOnWriteArraySet::new));
        log.info("exists projects:{}", converted);
        historyProjects.addAll(converted);
    }

    @Override
    public Set<Project> historyProjects() {
        return this.historyProjects;
    }

    @Override
    public void saveProjects() {
        currentProjects.add(new Project("test", new File(workspacePath + File.separator + "test")));
        currentProjects.parallelStream().forEach(this::persistenceProject);
        log.info("project save successful:{}", saved.getAbsolutePath());
    }

    @SneakyThrows
    private void persistenceProject(@NotNull Project project) {
        val persistenceFileName = saved + File.separator + PersistencePrefix.getValue() + "." + project.getName() + "."
                + PersistenceSuffix.getValue();
        try (val oos = new ObjectOutputStream(new FileOutputStream(persistenceFileName))) {
            log.info("write project:{}", project.getName());
            oos.writeObject(project);
        }
    }
}
