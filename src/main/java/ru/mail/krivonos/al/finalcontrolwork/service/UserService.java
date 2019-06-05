package ru.mail.krivonos.al.finalcontrolwork.service;

import ru.mail.krivonos.al.finalcontrolwork.service.model.UserDTO;

public interface UserService {

    UserDTO getUserByUsername(String username);
}
