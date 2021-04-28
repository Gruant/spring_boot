package ru.antongrutsin.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antongrutsin.spring_boot.model.Product;
import ru.antongrutsin.spring_boot.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(value = "minPrice", required = false) String min,
                                  @RequestParam(value = "maxPrice", required = false) String max){
        int minimal = 0;
        int maximum = Integer.MAX_VALUE;

        if(min != null){ minimal = Integer.parseInt(min); }
        if(max != null){ maximum = Integer.parseInt(max); }
        List<Product> products = productRepository.findProductByCostGreaterThanEqualAndCostLessThanEqual(minimal, maximum);
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping
    @ResponseBody
    public void createProduct(@RequestParam(name = "name") String name,
                              @RequestParam(name = "cost") int cost){
        Product product = new Product();
        product.setName(name);
        product.setCost(cost);
        productRepository.save(product);
    }

    @GetMapping(value = "/{id}")
    public String showById(Model model, @PathVariable(value = "id") int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            model.addAttribute("product", product.get());
        } else {
            model.addAttribute("product", new Product());
        }
        return "product";
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public void delete (@PathVariable(value = "id") int id){
        productRepository.deleteById(id);
    }


}
