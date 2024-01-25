module org.visual.model.git {
    requires org.eclipse.jgit;
    requires org.eclipse.jgit.http.apache;
    requires org.eclipse.jgit.lfs;
    requires org.eclipse.jgit.ssh.apache;

    exports org.visual.model.git;
}
