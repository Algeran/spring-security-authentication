package com.springfreamwork.springsecurity.app.configurations;

import com.springfreamwork.springsecurity.domain.dao.UserRepository;
import com.springfreamwork.springsecurity.domain.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserCreation {

    private final UserRepository userRepository;

    @Autowired
    public UserCreation(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createUser() {
        User user = new User("admin", "password");
        Optional<User> userInRepo = userRepository.findByUsername(user.getUsername());
        if (!userInRepo.isPresent()) {
            userRepository.save(user);
        }
    }
}
