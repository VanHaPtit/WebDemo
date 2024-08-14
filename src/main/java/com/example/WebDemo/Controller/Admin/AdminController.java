package com.example.WebDemo.Controller.Admin;


import com.example.WebDemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService ;

    @GetMapping("/admin1")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("Admin/index");
        return modelAndView;
    }

    @GetMapping("/Profile")
    public ModelAndView showProfile() {
        ModelAndView modelAndView = new ModelAndView("Admin/profile");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView showlogin() {
        ModelAndView modelAndView = new ModelAndView("Admin/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginn(@RequestParam("userName") String userName,
                              @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView("Admin/login");

        if (userService.isUserExists(userName, password)) {
            modelAndView.addObject("message", "Đăng nhập thành công!");
            modelAndView.setViewName("Admin/index"); // Trang sau khi đăng nhập thành công
        } else {
            modelAndView.addObject("error", "Tên đăng nhập hoặc mật khẩu không chính xác.");
            modelAndView.setViewName("Admin/login"); // Trang đăng nhập với thông báo lỗi
        }

        return modelAndView;
    }


}
