package com.Lunexconline.Ecommerce.Controller;

import com.Lunexconline.Ecommerce.Entity.Product;
import com.Lunexconline.Ecommerce.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/shop")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }
    @GetMapping("/product")
    public List<Product> listProducts() {
        return productService.getAllProducts();
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("images") List<MultipartFile> images) throws IOException {

        Product product = new Product(name, price, 0); // Assuming count is initialized to 0
        Product savedProduct = productService.saveProductWithImages(product, images);

        return ResponseEntity.ok(savedProduct);
    }
}
