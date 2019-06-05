package ru.mail.krivonos.al.finalcontrolwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.krivonos.al.finalcontrolwork.repository.UserRepository;
import ru.mail.krivonos.al.finalcontrolwork.repository.model.User;
import ru.mail.krivonos.al.finalcontrolwork.service.UserService;
import ru.mail.krivonos.al.finalcontrolwork.service.converter.UserConverter;
import ru.mail.krivonos.al.finalcontrolwork.service.model.UserDTO;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserConverter userConverter
    ) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        return userConverter.toDTO(user);
    }
}
