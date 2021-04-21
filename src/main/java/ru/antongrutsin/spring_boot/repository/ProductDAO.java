package ru.antongrutsin.spring_boot.repository;

import ru.antongrutsin.spring_boot.model.Product;

import java.util.List;

public interface ProductDAO{
    Product findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    void saveOrUpdate(Product product);
}
