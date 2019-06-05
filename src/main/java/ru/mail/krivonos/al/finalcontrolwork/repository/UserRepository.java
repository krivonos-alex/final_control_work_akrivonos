package ru.mail.krivonos.al.finalcontrolwork.repository;

import ru.mail.krivonos.al.finalcontrolwork.repository.model.User;

public interface UserRepository extends GenericRepository<Long, User> {

    User findUserByUsername(String username);
}
