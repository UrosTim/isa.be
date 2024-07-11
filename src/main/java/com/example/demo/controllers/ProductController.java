package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.repositories.IProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("product")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final IProductsRepository productsRepository;

    @GetMapping("get-list")
    public List<Product> getList() {
        return productsRepository.findAll();
    }

}
