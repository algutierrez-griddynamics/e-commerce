package org.ecommerce.repositories.inmemory;

import org.ecommerce.models.Manager;
import org.ecommerce.repositories.inmemory.impl.CrudInMemoryOperationsImpl;
import org.ecommerce.util.database.Operations;

public class ManagerRepository extends CrudInMemoryOperationsImpl<Manager> {
    public ManagerRepository(Operations<Manager> operations) {
        super(operations);
    }
}
