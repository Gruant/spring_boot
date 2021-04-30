package ru.antongrutsin.spring_boot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.antongrutsin.spring_boot.model.Product;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    List<Product> findProductByCostGreaterThanEqualAndCostLessThanEqual(int min, int max, Pageable page);
}
