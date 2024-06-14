package com.Lunexconline.Ecommerce.Service.Impl;

import com.Lunexconline.Ecommerce.Entity.User;
import com.Lunexconline.Ecommerce.Repository.UserRepository;
import com.Lunexconline.Ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
