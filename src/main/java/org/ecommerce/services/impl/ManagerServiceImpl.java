package org.ecommerce.services.impl;

import org.ecommerce.models.Manager;
import org.ecommerce.services.UserService;

import java.util.List;
import java.util.Optional;

public class ManagerServiceImpl implements UserService<Manager> {

    public ManagerServiceImpl() {}

    @Override
    public Manager save(Manager entity) {
      // TODO: UserValidatorService
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Manager> findById(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Long aLong, Manager entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Manager> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
