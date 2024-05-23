package com.Lunexconline.Ecommerce.Service;
import com.Lunexconline.Ecommerce.Entity.Product;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product saveProduct(Product product);
    Product updateProduct (Product product);
    void saveProductImages(Long productId, List<MultipartFile> files) throws IOException;
}
