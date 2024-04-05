package com.project.shopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopping.Entities.Product;
import com.project.shopping.Services.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
    ProductService productService;

    @GetMapping("/inventory")
    public ResponseEntity<Product> getInventory() {
        Product product = productService.getProduct();
        return ResponseEntity.ok(product);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
    	Product p = productService.addProduct(product);
    	return ResponseEntity.ok(p);
    }
}
