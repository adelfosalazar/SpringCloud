package com.adelfo.springcloud.msvc.products.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.adelfo.springcloud.msvc.products.entities.Product;
import com.adelfo.springcloud.msvc.products.services.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class ProductController {

    final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // @GetMapping()
    // public List<Product> list() {
    //     return this.service.findAll();
    // }

    @GetMapping()
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(this.service.findAll());
    }
    
    @GetMapping("/{id}")
    // public ResponseEntity<?> details(@PathVariable Long id) {
    public ResponseEntity<Product> details(@PathVariable Long id) {
        Optional<Product> producOptional = this.service.findById(id);

        if(producOptional.isPresent()){
            return ResponseEntity.ok(producOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
    
}
