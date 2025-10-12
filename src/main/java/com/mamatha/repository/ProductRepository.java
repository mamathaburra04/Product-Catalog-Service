package com.mamatha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mamatha.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
