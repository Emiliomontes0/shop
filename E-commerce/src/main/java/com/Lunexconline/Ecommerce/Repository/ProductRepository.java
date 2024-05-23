package com.Lunexconline.Ecommerce.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.Lunexconline.Ecommerce.Entity.Product;
@Repository
public interface ProductRepository extends MongoRepository<Product ,Long> {
}
