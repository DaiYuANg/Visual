/* (C)2024*/
package org.visual.model.database.driver;

import lombok.val;
import org.eclipse.aether.supplier.RepositorySystemSupplier;
import org.eclipse.aether.supplier.SessionBuilderSupplier;

public class DriverManager {

    DriverManager() {
        val repo = new RepositorySystemSupplier().get();
        new SessionBuilderSupplier(repo).get();
        val a = Driver.MYSQL.getArtifact();
    }
}
