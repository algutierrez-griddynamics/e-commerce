package org.ecommerce.services.impl;

import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.exceptions.InvalidInput;
import org.ecommerce.models.Manager;
import org.ecommerce.repositories.inmemory.ManagerRepository;
import org.ecommerce.services.UserService;
import org.ecommerce.util.validators.impl.Validators;

import java.util.ArrayList;
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
    public Manager update(Manager entity, Long id) {
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

    @Override
    public Boolean isValidEntity(Manager manager) {
        List<String> errorMessages = new ArrayList<>();

        if (!Validators.isValidName(manager.getFirstName())) {
            errorMessages.add(Error.INVALID_NAME.getDescription());
        }
        if (!Validators.isValidName(manager.getLastName())) {
            errorMessages.add(Error.INVALID_NAME.getDescription());
        }

        if (!Validators.isValidEmail(manager.getEmail())) {
            errorMessages.add(Error.INVALID_EMAIL.getDescription());
        }

        if (!Validators.isValidPassword(manager.getPassword())) {
            errorMessages.add(Error.PASSWORD_FORMAT.getDescription());
        }

        if (!errorMessages.isEmpty()) {
            throw new InvalidInput(String.join(", ", errorMessages));
        }

        return true;
    }

}
