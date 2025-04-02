package com.example.ad_integration.service;

import com.example.ad_integration.entity.User;
import com.example.ad_integration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LdapTemplate ldapTemplate;
    private final PasswordEncoder passwordEncoder;

    public User RegisterUser(User user){
        return null;
    }

}
