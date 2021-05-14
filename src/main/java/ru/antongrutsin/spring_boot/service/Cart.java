package ru.antongrutsin.spring_boot.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.antongrutsin.spring_boot.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class Cart {
    private final List<Product> productCart;

    public Cart() {
        this.productCart = new ArrayList<>();
    }

    public void addProduct(Product product){
        productCart.add(product);
    }

    public void deleteProduct(Product product){
        productCart.remove(product);
    }

    public void deleteById(int id){
        productCart.removeIf(product -> product.getId() == id);
    }

    public List<Product> getAllProduct(){
        return productCart;
    }

    public void update(Product product) {
        for (Product cartProduct : productCart) {
            if (cartProduct.getId() == product.getId()) {
                cartProduct.setId(product.getId());
                cartProduct.setName(product.getName());
                cartProduct.setCost(product.getCost());
            }
        }
    }


    public Product getById(int id){
        for (Product cartProduct : productCart) {
            if(cartProduct.getId()==id)
                return cartProduct;
        }
        return null;
    }
}
