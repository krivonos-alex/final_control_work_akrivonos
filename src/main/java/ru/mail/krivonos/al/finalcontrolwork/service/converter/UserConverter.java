package ru.mail.krivonos.al.finalcontrolwork.service.converter;

import ru.mail.krivonos.al.finalcontrolwork.repository.model.User;
import ru.mail.krivonos.al.finalcontrolwork.service.model.UserDTO;

public interface UserConverter {

    UserDTO toDTO(User user);
}
