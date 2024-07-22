package com.Lunexconline.Ecommerce.Repository;

import com.Lunexconline.Ecommerce.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
