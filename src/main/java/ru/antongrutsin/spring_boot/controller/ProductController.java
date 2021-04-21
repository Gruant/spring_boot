package ru.antongrutsin.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.antongrutsin.spring_boot.model.Product;
import ru.antongrutsin.spring_boot.repository.ProductDAO;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAllProducts(Model uiModel){
        List<Product> products = productDAO.findAll();
        uiModel.addAttribute("products", products);
        return "products";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showById(Model uiModel, @PathVariable(value = "id") Long id){
        Product product = productDAO.findById(id);
        uiModel.addAttribute("id", product.getId());
        uiModel.addAttribute("title", product.getTitle());
        uiModel.addAttribute("cost", product.getCost());
        return "product";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteById(Model uiModel, @PathVariable(value = "id") Long id){
        productDAO.deleteById(id);
        return "redirect:/products";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String getForm(Model uiModel){
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String create(Product product) {
        productDAO.saveOrUpdate(product);
        return "redirect:/products";
    }

}
