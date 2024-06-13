package com.Lunexconline.Ecommerce.Service;
import com.Lunexconline.Ecommerce.Entity.Product;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product saveProduct(Product product);
    Product saveProductWithImages(Product product, List<MultipartFile> images) throws IOException;
}
