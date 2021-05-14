package ru.antongrutsin.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antongrutsin.spring_boot.model.Product;
import ru.antongrutsin.spring_boot.model.Role;
import ru.antongrutsin.spring_boot.model.User;
import ru.antongrutsin.spring_boot.repository.RoleRepository;
import ru.antongrutsin.spring_boot.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin/{action}/{id}")
    public String  deleteUser(@PathVariable int id,
                              @PathVariable String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(id);
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/add/")
    public String createUser(@RequestParam(name = "name") String name,
                              @RequestParam(name = "role") Set<Role> roles){
        User user = new User();
        user.setUsername(name);
        user.setPassword("0000");
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/userform")
    public String getForm(Model uiModel){
        User user = new User();
        uiModel.addAttribute("user", user);
        return "userform";
    }


    @PostMapping("/admin/userform")
    public String create(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

}
