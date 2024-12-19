package org.ecommerce.repositories.inmemory;

import org.ecommerce.models.Employee;
import org.ecommerce.repositories.inmemory.impl.CrudInMemoryOperationsImpl;
import org.ecommerce.util.database.Operations;

public class ManagerRepository extends CrudInMemoryOperationsImpl<Employee> {
    public ManagerRepository(Operations<Employee> operations) {
        super(operations);
    }
}
