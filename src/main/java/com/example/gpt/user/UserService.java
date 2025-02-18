package com.example.gpt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable("users")
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
