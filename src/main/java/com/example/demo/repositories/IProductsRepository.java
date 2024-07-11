package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductsRepository extends JpaRepository<Product, Integer> {
}
