module org.visual.git {
  requires org.eclipse.jgit;
  requires org.eclipse.jgit.http.apache;
  requires org.eclipse.jgit.lfs;
  requires org.eclipse.jgit.ssh.apache;

  exports org.visual.git;
}
