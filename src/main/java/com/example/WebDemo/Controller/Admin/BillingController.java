package com.example.WebDemo.Controller.Admin;

import com.example.WebDemo.Model.Billing;
import com.example.WebDemo.Model.CustomerOrder;
import com.example.WebDemo.Model.Product;
import com.example.WebDemo.Service.BillingIService;
import com.example.WebDemo.Service.CustomerOrderService;
import com.example.WebDemo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/CustomerOrder")
public class BillingController {
    @Autowired
    private BillingIService billingIService ;

    @Autowired
    private CustomerOrderService customerOrderService ;

    @Autowired
    private ProductService productService ;

    @GetMapping("/detail/{ID}")
    public ModelAndView showLists(@PathVariable("ID") Long ID) throws ChangeSetPersister.NotFoundException {
        ModelAndView modelAndView = new ModelAndView("Customer/Billing/Detail");
        Optional<CustomerOrder> customerOrders = customerOrderService.findById(ID);
        Optional<Product> products = productService.findById(ID);
        Optional<Billing> billings = billingIService.findById(Math.toIntExact(ID));
        if (customerOrders.isEmpty() || products.isEmpty() || billings.isEmpty()) {
            modelAndView.addObject("message", "Chưa có đủ thông tin để hiển thị.");
        } else {
            modelAndView.addObject("customerOrder", customerOrders.get());
            modelAndView.addObject("product", products.get());
            modelAndView.addObject("billing", billings.get());

        }
        return modelAndView;
    }

    // tạo hóa đơn
    @GetMapping("/add_billing")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("Customer/Billing/add");
        return modelAndView;
    }


    @PostMapping("/add_billing")
    public ModelAndView save(@Validated @ModelAttribute("billing") Billing billing , BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("Customer/Billing/add");
        if (bindingResult.hasFieldErrors()) {
            return modelAndView;
        }
        billingIService.save(billing);
        modelAndView.addObject("billing", new Billing());
        return modelAndView;
    }


}
