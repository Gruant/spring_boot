package ru.antongrutsin.spring_boot.repository;

import ru.antongrutsin.spring_boot.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();
    Product getById(int id);
    void save(Product product);
}
