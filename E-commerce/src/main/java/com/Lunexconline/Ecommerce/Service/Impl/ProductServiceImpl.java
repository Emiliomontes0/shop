package com.Lunexconline.Ecommerce.Service.Impl;

import com.Lunexconline.Ecommerce.Entity.Product;
import com.Lunexconline.Ecommerce.Repository.ProductRepository;
import com.Lunexconline.Ecommerce.Service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl (ProductRepository productRepository){
        super();
        this.productRepository=productRepository;
    }
    public List<Product> getAllProduct (){
        return productRepository.findAll();
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(Product product){
        return productRepository.save(product);
    }
}
