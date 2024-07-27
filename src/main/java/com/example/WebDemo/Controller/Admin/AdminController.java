package com.example.WebDemo.Controller.Admin;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/admin1")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("Admin/index");
        return modelAndView;
    }
}
