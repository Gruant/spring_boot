package ru.antongrutsin.spring_boot.controller;

import org.springframework.web.bind.annotation.*;
import ru.antongrutsin.spring_boot.controller.dto.ProductDTO;
import ru.antongrutsin.spring_boot.controller.mapper.ProductConverter;
import ru.antongrutsin.spring_boot.model.Product;
import ru.antongrutsin.spring_boot.repository.ProductRepository;
import ru.antongrutsin.spring_boot.service.Cart;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRestController {
    private final Cart cart;
    private final ProductConverter productConverter;
    private final ProductRepository productRepository;

    public CartRestController(Cart cart, ProductConverter productConverter, ProductRepository productRepository, ProductRepository productRepository1) {
        this.cart = cart;
        this.productConverter = productConverter;
        this.productRepository = productRepository1;
    }

    @GetMapping
    public List<ProductDTO> getCart(){
        return cart.getAllProduct().stream().map(productConverter::fromProduct).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable int id){
        return productConverter.fromProduct(cart.getById(id));
    }

    @DeleteMapping("/remove/{id}")
    public void delete(@PathVariable int id){
        cart.deleteById(id);
    }

    @DeleteMapping("/remove/product")
    public void deleteByObject(@RequestBody Product product){
        cart.deleteProduct(product);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        cart.addProduct(product);
    }

    @PatchMapping("/update")
    public void updateProduct(@RequestBody Product product){
        cart.update(product);
    }
}
