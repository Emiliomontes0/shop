package com.Lunexconline.Ecommerce.Service;

import com.Lunexconline.Ecommerce.Entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product saveProduct(Product product);
    Product updateProduct (Product product);
}
