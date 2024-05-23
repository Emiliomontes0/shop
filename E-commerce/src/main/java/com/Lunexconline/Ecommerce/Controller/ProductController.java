package com.Lunexconline.Ecommerce.Controller;

import com.Lunexconline.Ecommerce.Service.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }
    @GetMapping("/")
    public String listProducts (Model model){
        model.addAttribute("product" , productService.getAllProduct());
        return "";
    }
}
