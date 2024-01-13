package org.visual.model.sql.driver;

import lombok.val;
import org.eclipse.aether.supplier.RepositorySystemSupplier;
import org.eclipse.aether.supplier.SessionBuilderSupplier;
import org.visual.model.sql.Driver;

public class DriverManager {

    DriverManager() {
        val repo = new RepositorySystemSupplier().get();
        new SessionBuilderSupplier(repo).get();
        val a = Driver.MYSQL.getArtifact();
    }
}
