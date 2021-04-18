package ru.antongrutsin.spring_boot.repository;

import org.springframework.stereotype.Component;
import ru.antongrutsin.spring_boot.model.Product;

import java.util.LinkedList;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository{

    private final List<Product> products = new LinkedList<>();

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getById(int id){
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void save(Product product){
        products.add(product);
    }
}