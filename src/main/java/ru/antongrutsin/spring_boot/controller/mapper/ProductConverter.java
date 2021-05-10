package ru.antongrutsin.spring_boot.controller.mapper;

import org.springframework.stereotype.Component;
import ru.antongrutsin.spring_boot.controller.dto.ProductDTO;
import ru.antongrutsin.spring_boot.model.Product;

@Component
public class ProductConverter {

    public ProductDTO fromProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCost(product.getCost());
        return productDTO;
    }
}
