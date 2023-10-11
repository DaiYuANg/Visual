package org.visual.model.functional;

import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@ToString
public class Project implements Serializable {

    private final String name;

    private final File projectDirectory;

    private final List<File> projectFiles = new CopyOnWriteArrayList<>();

    public Project(String name,File projectDirectory){
        this.name = name;
        this.projectDirectory = projectDirectory;
    }
}
