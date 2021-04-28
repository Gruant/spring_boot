package ru.antongrutsin.spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antongrutsin.spring_boot.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductByCostGreaterThanEqualAndCostLessThanEqual(int min, int max);
}
