package com.example.WebDemo.Controller.Admin;

import com.example.WebDemo.Model.Category;
import com.example.WebDemo.Model.Product;
import com.example.WebDemo.Service.CategoryService;
import com.example.WebDemo.Service.FilesStorageService;
import com.example.WebDemo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")

public class ProductController {
    @Autowired
    private ProductService productService ;

    @Autowired
    private CategoryService categoryService ;

    @Autowired
    FilesStorageService storageService;

    @GetMapping("/CreateProduct")
    public ModelAndView showCreateProductForm() {
        ModelAndView modelAndView = new ModelAndView("Admin/product/add");
        Product product = new Product();
        List<Category> listCate = categoryService.getAll();
        modelAndView.addObject("product", product);
        modelAndView.addObject("ListCate", listCate);
        return modelAndView;
    }

    // lấy tên ảnh chưa lấy link ảnh

    @PostMapping("/CreateProduct")
    public ModelAndView saveProduct(@Validated @ModelAttribute("product") Product product,
                                    BindingResult bindingResult,
                                    @RequestParam("image") String fileName) { // Nhận tên tệp dưới dạng String
        ModelAndView modelAndView = new ModelAndView("Admin/product/add");

        // Nếu có lỗi xác thực, trả về trang thêm sản phẩm với các lỗi
        if (bindingResult.hasErrors()) {
            List<Category> listCate = categoryService.getAll();
            modelAndView.addObject("ListCate", listCate);
            return modelAndView;
        }

        String message;
        try {
            // Kiểm tra xem tên tệp có hợp lệ không và lưu tên tệp vào sản phẩm
            if (fileName != null && !fileName.isEmpty()) {
                // Lưu tên tệp vào sản phẩm
                product.setImage(fileName);
            }

            // Lưu sản phẩm vào cơ sở dữ liệu
            productService.save(product);
            message = "Saved the product successfully: " + product.getProductName();
        } catch (Exception e) {
            message = "Could not save the product. Error: " + e.getMessage();
        }

        modelAndView.addObject("message", message);
        modelAndView.addObject("product", new Product());  // Reset product form
        return modelAndView;
    }





    // đang lấy link ảnh

//    @Autowired
//    private GoogleDriveService googleDriveService;
//
//
//
//    @PostMapping("/CreateProduct")
//    public ModelAndView saveProduct(@Validated @ModelAttribute("product") Product product,
//                                    BindingResult bindingResult,
//                                    @RequestParam("image") MultipartFile imageFile) { // Nhận MultipartFile
//
//        ModelAndView modelAndView = new ModelAndView("Admin/product/add");
//
//        // Nếu có lỗi xác thực, trả về trang thêm sản phẩm với các lỗi
//        if (bindingResult.hasErrors()) {
//            List<Category> listCate = categoryService.getAll();
//            modelAndView.addObject("ListCate", listCate);
//            return modelAndView;
//        }
//
//        String message;
//        try {
//            if (imageFile != null && !imageFile.isEmpty()) {
//                // Tải lên Google Drive và nhận liên kết
//                String imageUrlOnDrive = googleDriveService.uploadFileToDrive(imageFile.getBytes(), imageFile.getOriginalFilename());
//                product.setImage(imageUrlOnDrive); // Lưu liên kết ảnh vào sản phẩm
//            }
//
//            // Lưu sản phẩm vào cơ sở dữ liệu
//            productService.save(product);
//            message = "Saved the product successfully: " + product.getProductName();
//        } catch (IOException | GeneralSecurityException e) {
//            message = "Could not save the product. Error: " + e.getMessage();
//        }
//
//        modelAndView.addObject("message", message);
//        modelAndView.addObject("product", new Product());  // Reset product form
//        return modelAndView;
//    }
//


//    @PostMapping("/CreateProduct")
//    public ModelAndView saveProduct(@Validated @ModelAttribute("product") Product product,
//                                    BindingResult bindingResult,
//                                    @RequestParam("image") String imageFile) { // Nhận MultipartFile
//
//        ModelAndView modelAndView = new ModelAndView("Admin/product/add");
//
//        // Nếu có lỗi xác thực, trả về trang thêm sản phẩm với các lỗi
//        if (bindingResult.hasErrors()) {
//            List<Category> listCate = categoryService.getAll();
//            modelAndView.addObject("ListCate", listCate);
//            return modelAndView;
//        }
//
//        String message;
//        try {
//            if (imageFile != null && !imageFile.isEmpty()) {
//                // Tải tệp từ URL thành byte[]
//                byte[] fileBytes = googleDriveService.downloadFileFromUrl(imageFile);
//                String fileName = "uploaded_image.jpg"; // Tên tệp bạn muốn đặt
//
//                // Tải lên Google Drive và nhận liên kết
//                String imageUrlOnDrive = googleDriveService.uploadFileToDrive(fileBytes, fileName);
//                product.setImage(imageUrlOnDrive);
//            }
//
//            // Lưu sản phẩm vào cơ sở dữ liệu
//            productService.save(product);
//            message = "Saved the product successfully: " + product.getProductName();
//        } catch (IOException | GeneralSecurityException e) {
//            message = "Could not save the product. Error: " + e.getMessage();
//        }
//
//        modelAndView.addObject("message", message);
//        modelAndView.addObject("product", new Product());  // Reset product form
//        return modelAndView;
//    }


//    @PostMapping("/CreateProduct")
//    public ModelAndView saveProduct(@Validated @ModelAttribute("product") Product product,
//                                    BindingResult bindingResult,
//                                    @RequestParam("image") String imageUrl) { // Nhận URL ảnh dưới dạng String
//
//        ModelAndView modelAndView = new ModelAndView("Admin/product/add");
//
//        // Nếu có lỗi xác thực, trả về trang thêm sản phẩm với các lỗi
//        if (bindingResult.hasErrors()) {
//            List<Category> listCate = categoryService.getAll();
//            modelAndView.addObject("ListCate", listCate);
//            return modelAndView;
//        }
//
//        String message;
//        try {
//            // Kiểm tra xem URL có hợp lệ không và tải lên Google Drive
//            if (imageUrl != null && !imageUrl.isEmpty()) {
//                // Tải tệp từ URL thành byte[]
//                byte[] fileBytes = FileUtils.downloadFileFromUrl(imageUrl);
//                String fileName = "uploaded_image.jpg"; // Tên tệp bạn muốn đặt
//
//                // Tải lên Google Drive và nhận liên kết
//                String imageUrlOnDrive = googleDriveService.uploadFileToDrive(fileBytes, fileName);
//                product.setImage(imageUrlOnDrive); // Lưu liên kết ảnh vào sản phẩm
//            }
//
//            // Lưu sản phẩm vào cơ sở dữ liệu
//            productService.save(product);
//            message = "Saved the product successfully: " + product.getProductName();
//        } catch (IOException | GeneralSecurityException e) {
//            message = "Could not save the product. Error: " + e.getMessage();
//        }
//
//        modelAndView.addObject("message", message);
//        modelAndView.addObject("product", new Product());  // Reset product form
//        return modelAndView;
//    }








//    @Autowired
//    private GoogleDriveService googleDriveService;
//
//    @PostMapping("/CreateProduct")
//    public ModelAndView saveProduct(@Validated @ModelAttribute("product") Product product,
//                                    BindingResult bindingResult,
//                                    @RequestParam("imageUrl") String imageUrl) { // Nhận URL ảnh dưới dạng String
//        ModelAndView modelAndView = new ModelAndView("Admin/product/add");
//
//        // Nếu có lỗi xác thực, trả về trang thêm sản phẩm với các lỗi
//        if (bindingResult.hasErrors()) {
//            List<Category> listCate = categoryService.getAll();
//            modelAndView.addObject("ListCate", listCate);
//            return modelAndView;
//        }
//
//        String message;
//        try {
//            // Kiểm tra xem URL có hợp lệ không và tải lên Google Drive
//            if (imageUrl != null && !imageUrl.isEmpty()) {
//                String imageUrlOnDrive = googleDriveService.uploadFileToDrive(imageUrl);
//                product.setImage(imageUrlOnDrive); // Lưu liên kết ảnh vào sản phẩm
//            }
//
//            // Lưu sản phẩm vào cơ sở dữ liệu
//            productService.save(product);
//            message = "Saved the product successfully: " + product.getProductName();
//        } catch (Exception e) {
//            message = "Could not save the product. Error: " + e.getMessage();
//        }
//
//        modelAndView.addObject("message", message);
//        modelAndView.addObject("product", new Product());  // Reset product form
//        return modelAndView;
//    }










    @GetMapping("/product")
    public ModelAndView showList(Model model) {
        ModelAndView modelAndView = new ModelAndView("Admin/product/index");
        List<Product> list = productService.getAll();
        modelAndView.addObject("list",list );
        return modelAndView;
    }

    @GetMapping("/updateProduct/{ID}")
    public ModelAndView showUpdateForm(@PathVariable("ID") Long ID) {
        Optional<Product> product = productService.findById(Math.toIntExact(ID));
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("Admin/product/Update");
            List<Category> listCate = categoryService.getAll();
            modelAndView.addObject("ListCate", listCate);
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/updateProduct")
    public ModelAndView update(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("Admin/product/Update");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        productService.save(product);
        ModelAndView modelAndView1 = new ModelAndView("Admin/product/index");
        return modelAndView1;
    }


    @GetMapping("/deleteProduct/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id, Pageable pageable) {
        productService.delete(id);
        Page<Product> products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("Admin/product/index");
        modelAndView.addObject("product", products);
        return modelAndView;
    }


}
