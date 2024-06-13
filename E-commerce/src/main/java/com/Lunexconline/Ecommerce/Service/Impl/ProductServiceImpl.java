package com.Lunexconline.Ecommerce.Service.Impl;
import com.Lunexconline.Ecommerce.Entity.Product;
import com.Lunexconline.Ecommerce.Repository.ProductRepository;
import com.Lunexconline.Ecommerce.Service.ImageStorageService;
import com.Lunexconline.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageStorageService imageStorageService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ImageStorageService imageStorageService) {
        this.productRepository = productRepository;
        this.imageStorageService = imageStorageService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product saveProductWithImages(Product product, List<MultipartFile> images) throws IOException {
        List<String> imagePaths = imageStorageService.saveImages(images);
        product.setImagePaths(imagePaths);
        return productRepository.save(product);
    }
}
