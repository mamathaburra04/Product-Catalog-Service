package com.mamatha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mamatha.entity.Product;
import com.mamatha.exception.ResourceNotFoundException;
import com.mamatha.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    public Product createProduct(Product product) {
        return repo.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. Product not found with ID: " + id));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        existing.setActive(product.getActive());

        return repo.save(existing);
    }

    public void deleteProduct(Long id) {
        Product existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete. Product not found with ID: " + id));

        repo.delete(existing);
    }

}
