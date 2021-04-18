package ru.antongrutsin.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.antongrutsin.spring_boot.model.Product;
import ru.antongrutsin.spring_boot.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAllProducts(Model uiModel){
        List<Product> products = productRepository.getAll();
        uiModel.addAttribute("products", products);
        return "products";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showById(Model uiModel, @PathVariable(value = "id") int id){
        Product product = productRepository.getById(id);
        uiModel.addAttribute("id", product.getId());
        uiModel.addAttribute("title", product.getTitle());
        uiModel.addAttribute("cost", product.getCost());
        return "product";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String getForm(Model uiModel){
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String create(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

}
