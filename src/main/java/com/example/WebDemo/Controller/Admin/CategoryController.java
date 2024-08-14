package com.example.WebDemo.Controller.Admin;

import com.example.WebDemo.Model.Category;
import com.example.WebDemo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService ;

    @GetMapping("/category")
    public ModelAndView index(@RequestParam(value = "keyword", required = false) String keyword , @RequestParam(name ="pageNo",defaultValue = "1") Integer pageNo) {
        ModelAndView modelAndView = new ModelAndView("Admin/category/index");
        Page<Category> list = categoryService.getAll(pageNo);

        if (keyword != null && !keyword.trim().isEmpty()) {
            list = categoryService.seachCategory(keyword , pageNo);
            modelAndView.addObject("keyword" , keyword);
        }
        modelAndView.addObject("totalPage",list.getTotalPages());
        modelAndView.addObject("currentPage" , pageNo);
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @GetMapping("/add_category")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("Admin/category/add");
        return modelAndView;
    }

    @PostMapping("/add_category")
    public ModelAndView save(@Validated @ModelAttribute("category")Category category , BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("Admin/category/add");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        categoryService.save(category);
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }



    @GetMapping("/update/{ID}")
    public ModelAndView showUpdateForm(@PathVariable("ID") Long ID) {
        Optional<Category> category = categoryService.findById(Math.toIntExact(ID));
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("Admin/category/Update");
            modelAndView.addObject("category", category.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/update")
    public ModelAndView update(@Validated @ModelAttribute("category") Category category, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("Admin/category/Update");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        categoryService.save(category);
        ModelAndView modelAndView1 = new ModelAndView("Admin/category/index");
//        modelAndView.addObject("category", categoryService.findAll());
        return modelAndView1;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id, Pageable pageable) {
        categoryService.delete(id);
        Page<Category> categories = categoryService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("Admin/category/List");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("List");
        Iterable category = categoryService.findAll();
        modelAndView.addObject("category", category);
        return modelAndView;
    }

}
