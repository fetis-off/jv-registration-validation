package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.InvalidDataException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final byte MIN_LOG_PASSWORD_LENGTH = 6;
    private static final byte MIN_AGE = 18;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (storageDao.get(user.getLogin()) != null) {
            throw new InvalidDataException("User with the same login already exists");
        }
        if (user.getLogin() == null
                || user.getLogin().length() < MIN_LOG_PASSWORD_LENGTH) {
            throw new InvalidDataException("Login must be at least 6 characters");
        }
        if (user.getPassword() == null
                || user.getPassword().length() < MIN_LOG_PASSWORD_LENGTH) {
            throw new InvalidDataException("Password must be at least 6 characters");
        }
        if (user.getAge() < MIN_AGE) {
            throw new InvalidDataException("Age must be at least 18 years old");
        }
        storageDao.add(user);
        return user;
    }
}
