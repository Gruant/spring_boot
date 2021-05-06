package ru.antongrutsin.spring_boot.controller;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.antongrutsin.spring_boot.exception.ProductNotFoundException;
import ru.antongrutsin.spring_boot.model.Product;
import ru.antongrutsin.spring_boot.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductRestController {
    private final ProductRepository productRepository;


    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product finaById(@PathVariable int id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productRepository.deleteById(id);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product ) {
        productRepository.findById(product.getId()).orElseThrow(ProductNotFoundException::new);
        return productRepository.save(product);
    }

    @PostMapping("/create")
    public Product addProduct(@RequestBody Product product) {
        product.setId(0);
        return productRepository.save(product);
    }



}
