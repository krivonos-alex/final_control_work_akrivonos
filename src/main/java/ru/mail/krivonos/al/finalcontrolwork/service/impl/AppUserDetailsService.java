package ru.mail.krivonos.al.finalcontrolwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mail.krivonos.al.finalcontrolwork.service.UserService;
import ru.mail.krivonos.al.finalcontrolwork.service.model.AuthUserPrincipal;
import ru.mail.krivonos.al.finalcontrolwork.service.model.UserDTO;

@Service("userDetailsService")
public class AppUserDetailsService implements UserDetailsService {

    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "Username \"%s\" not found.";

    private final UserService userService;

    @Autowired
    public AppUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.getUserByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_ERROR_MESSAGE, username));
        }
        return new AuthUserPrincipal(userDTO);
    }
}
