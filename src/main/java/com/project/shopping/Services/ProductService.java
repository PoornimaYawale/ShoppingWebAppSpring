package com.project.shopping.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopping.Entities.Product;
import com.project.shopping.Repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	 @Autowired
	  ProductRepository productRepository;
	 
	   public Product addProduct(Product product) {
		   Product p=  new Product();
		   p.setOrdered(0);
		   p.setProductName(product.getProductName());
		   p.setAvailableQuantity(product.getAvailableQuantity());
		   p.setPrice(product.getPrice());
		    return productRepository.save(p);
	   }

	    public Product getProduct() {
	    	Optional<Product> product = productRepository.findById(2);
			if (product.isPresent()) {
				return product.get();
			}
			throw  new EntityNotFoundException("Product not found");
	    }
}
