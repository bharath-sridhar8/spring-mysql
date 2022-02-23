package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repo) {
        userRepository = repo;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String createUser(String name, String email) {
        User u = new User();
        u.setEmail(email);
        u.setName(name);
        userRepository.save(u);
        return u.getId();
    }
}
