/* (C)2024*/
package org.visual.database.driver

import org.eclipse.aether.RepositorySystemSession
import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.supplier.RepositorySystemSupplier
import org.eclipse.aether.supplier.SessionBuilderSupplier

class DriverManager {
  init {
    val repositorySystem = RepositorySystemSupplier().get()
    val session = SessionBuilderSupplier(repositorySystem).get().build()
    val localRepo = LocalRepository(System.getProperty("user.home") + "/.m2/repository")
    session.localRepositoryManager.repository.basedir
    val local = session.localRepositoryManager.repository
    System.err.println(local.basedir)
    //        val a = SessionBuilderSupplier(repo).get().build()
  }
}
