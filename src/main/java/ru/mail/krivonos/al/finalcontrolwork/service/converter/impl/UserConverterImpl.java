package ru.mail.krivonos.al.finalcontrolwork.service.converter.impl;

import org.springframework.stereotype.Component;
import ru.mail.krivonos.al.finalcontrolwork.repository.model.User;
import ru.mail.krivonos.al.finalcontrolwork.service.converter.UserConverter;
import ru.mail.krivonos.al.finalcontrolwork.service.model.UserDTO;

@Component("userConverter")
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
