package com.example.WebDemo.Controller.Admin;


import com.example.WebDemo.Model.User;
import com.example.WebDemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/User")
public class UserController2 {
    @Autowired
    private UserService userService ;

    @GetMapping("/CreateUser")
    public ModelAndView showform() {
        ModelAndView modelAndView = new ModelAndView("Admin/CreateAccount");
        return modelAndView;
    }

    @PostMapping("/CreateUser")
    public ModelAndView save(@Validated @ModelAttribute("user") User user , BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("Admin/CreateAccount");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        else{
            userService.save(user);
            ModelAndView modelAndView1 = new ModelAndView("Admin/login");
            return modelAndView1;
        }
    }
}
