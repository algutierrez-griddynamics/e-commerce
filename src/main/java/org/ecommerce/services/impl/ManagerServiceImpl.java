package org.ecommerce.services.impl;

import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.models.Manager;
import org.ecommerce.repositories.ManagerRepository;
import org.ecommerce.services.UserService;

import java.util.List;

public class ManagerServiceImpl implements UserService<Manager> {

    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager create(Manager entity) {
        return managerRepository.save(entity);
    }

    @Override
    public Manager update(Long id, Manager entity) {
        managerRepository.update(id, entity);
        return managerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()));
    }

    @Override
    public void delete(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public Manager findById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()));
    }
}
