package com.Lunexconline.Ecommerce.Service;

import com.Lunexconline.Ecommerce.Entity.User;

public interface UserService {
    User findByEmail(String email);
    User saveUser(User user);
}
