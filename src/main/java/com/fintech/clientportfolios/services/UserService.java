package com.fintech.clientportfolios.services;

import com.fintech.clientportfolios.domain.User;
import com.fintech.clientportfolios.exceptions.UserNameAlreadyExistsException;
import com.fintech.clientportfolios.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User newUser){

        try {
            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UserNameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
        }
    }

}
