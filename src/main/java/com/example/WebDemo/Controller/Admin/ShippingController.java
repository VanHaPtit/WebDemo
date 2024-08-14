package com.example.WebDemo.Controller.Admin;

import com.example.WebDemo.Model.Product;
import com.example.WebDemo.Model.Shipping;
import com.example.WebDemo.Service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CustomerOrder")
public class ShippingController {

    @Autowired
    private ShippingService shippingService ;

    @GetMapping("/add_shipping")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("Customer/Shipping/add");
        return modelAndView;
    }

    @PostMapping("/add_shipping")
    public ModelAndView save(@Validated @ModelAttribute("category") Shipping shipping , BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("Customer/Shipping/add");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        shippingService.save(shipping);
        modelAndView.addObject("shipping", new Shipping());
        return modelAndView;
    }





    @GetMapping("/update/{ID}")
    public ModelAndView showUpdateForm(@PathVariable("ID") Long ID) {
        Optional<Shipping> shipping = shippingService.findById(Math.toIntExact(ID));
        if (shipping.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("Customer/Shipping/Update");
            modelAndView.addObject("shipping", shipping.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/update")
    public ModelAndView update(@Validated @ModelAttribute("shipping") Shipping shipping, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("Customer/Shipping/Update");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        shippingService.save(shipping);
        ModelAndView modelAndView1 = new ModelAndView("Customer/Shipping/Update");
//        modelAndView.addObject("category", categoryService.findAll());
        return modelAndView1;
    }




    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id, Pageable pageable) {
        shippingService.delete(id);
        Page<Shipping> shippings = shippingService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("Customer/Shipping/index");
        modelAndView.addObject("shippings", shippings);
        return modelAndView;
    }


    @GetMapping("/shipping")
    public ModelAndView showList(Model model , @RequestParam(value = "keyword", required = false) String keyword , @RequestParam(name ="pageNo",defaultValue = "1") Integer pageNo) {
        ModelAndView modelAndView = new ModelAndView("Customer/Shipping/index");
        Page<Shipping> list = shippingService.getAll(pageNo);

        if (keyword != null && !keyword.trim().isEmpty()) {
            list = shippingService.seachShipping(keyword, pageNo);
            modelAndView.addObject("keyword", keyword);
        }
        modelAndView.addObject("totalPage", list.getTotalPages());
        modelAndView.addObject("currentPage", pageNo);
        modelAndView.addObject("list", list);
        return modelAndView;
    }
}
