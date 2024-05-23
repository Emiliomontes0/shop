package com.Lunexconline.Ecommerce.Service.Impl;

import com.Lunexconline.Ecommerce.Entity.Product;
import com.Lunexconline.Ecommerce.Repository.ProductRepository;
import com.Lunexconline.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private static final String UPLOAD_DIR = "uploads/";
    public ProductServiceImpl (ProductRepository productRepository){
        super();
        this.productRepository=productRepository;
    }
    @Override
    public List<Product> getAllProduct (){
        return productRepository.findAll();
    }
    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(Product product){
        return productRepository.save(product);
    }
    public void saveProductImages(Long productId, List<MultipartFile> files) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            fileNames.add(fileName);
        }
        product.setImagePaths(fileNames);
        productRepository.save(product);
    }
}
