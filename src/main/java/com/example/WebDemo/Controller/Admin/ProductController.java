package com.example.WebDemo.Controller.Admin;

import com.example.WebDemo.Model.*;
import com.example.WebDemo.Service.CategoryService;
import com.example.WebDemo.Service.FilesStorageService;
//import com.example.WebDemo.Service.ImageUploadService;
import com.example.WebDemo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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
        ProductDTO product = new ProductDTO();
        List<Category> listCate = categoryService.getAll();
        modelAndView.addObject("product", product);
        modelAndView.addObject("ListCate", listCate);
        return modelAndView;
    }



    @PostMapping("/CreateProduct")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        // Xử lý tệp tải lên
        List<MultipartFile> files = productDTO.getImages(); // Danh sách MultipartFile
        List<Image> imageList = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (isImageFile(file)) {
                    // Lưu tệp và lấy đường dẫn
                    String imageUrl = storageService.save(file);

                    // Tạo đối tượng Image và thêm vào danh sách
                    Image image = new Image();
                    image.setName(file.getOriginalFilename());
                    image.setContentType(file.getContentType());
                    image.setSize(file.getSize());
                    image.setUrl(imageUrl);
                    imageList.add(image);
                }
            }
        }

        // Tạo đối tượng Product và thiết lập các thuộc tính
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setImages(imageList);

        // Liên kết hình ảnh với sản phẩm
        for (Image image : imageList) {
            image.setProduct(product);
        }

        // Lưu sản phẩm vào cơ sở dữ liệu
        productService.save(product);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        return (contentType.equals("image/jpeg") ||
                contentType.equals("image/png") ||
                contentType.equals("image/gif") ||
                contentType.equals("image/bmp") ||
                contentType.equals("image/webp")) ||
                (fileName != null && (fileName.endsWith(".jpg") ||
                        fileName.endsWith(".jpeg") ||
                        fileName.endsWith(".png") ||
                        fileName.endsWith(".gif") ||
                        fileName.endsWith(".bmp") ||
                        fileName.endsWith(".webp")));
    }



    @GetMapping("/product")
    public ModelAndView showList(Model model , @RequestParam(value = "keyword", required = false) String keyword , @RequestParam(name ="pageNo",defaultValue = "1") Integer pageNo) {
        ModelAndView modelAndView = new ModelAndView("Admin/product/index");
        Page<Product> list = productService.getAll(pageNo);

        if (keyword != null && !keyword.trim().isEmpty()) {
            list = productService.seachProduct(keyword , pageNo);
            modelAndView.addObject("keyword" , keyword);
        }
        modelAndView.addObject("totalPage",list.getTotalPages());
        modelAndView.addObject("currentPage" , pageNo);
        modelAndView.addObject("list",list );
        return modelAndView;
    }


    @GetMapping("/updateProduct/{ID}")
    public ModelAndView showUpdateForm(@PathVariable("ID") Long ID) {
        Optional<Product> product = productService.findById(ID);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("Admin/product/Update");
            List<Category> listCate = categoryService.getAll();
            if (listCate.isEmpty()) {
                modelAndView.addObject("message", "Danh sách danh mục trống.");
            }
            modelAndView.addObject("ListCate", listCate);
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }



    @PostMapping("/updateProduct")
    public ModelAndView update(@Validated @ModelAttribute("product") ProductDTO productDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("Admin/product/Update");

        if (bindingResult.hasErrors()) {
            List<Category> listCate = categoryService.getAll();
            modelAndView.addObject("ListCate", listCate);
            return modelAndView;
        }

        // Tìm sản phẩm hiện tại
        Product existingProduct = productService.findById(productDTO.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Cập nhật thông tin sản phẩm
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setCategory(productDTO.getCategory());

        // Xử lý hình ảnh mới
        List<MultipartFile> files = productDTO.getImages(); // Danh sách MultipartFile từ ProductDTO
        List<Image> newImages = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty() && isImageFile(file)) {
                    // Lưu tệp và lấy đường dẫn
                    String imageUrl = storageService.save(file);

                    // Tạo đối tượng Image và thêm vào danh sách
                    Image image = new Image();
                    image.setName(file.getOriginalFilename());
                    image.setContentType(file.getContentType());
                    image.setSize(file.getSize());
                    image.setUrl(imageUrl);
                    image.setProduct(existingProduct);
                    newImages.add(image);
                }
            }
        }

        // Xóa các hình ảnh không còn liên kết
        Set<Image> imagesToRemove = new HashSet<>(existingProduct.getImages());
        imagesToRemove.removeAll(newImages);

        // Xóa hình ảnh không còn liên kết
        for (Image image : imagesToRemove) {
            existingProduct.getImages().remove(image);
            image.setProduct(null); // Đặt liên kết về null để không còn liên kết với sản phẩm
            storageService.delete(image.getUrl()); // Xóa hình ảnh từ cơ sở dữ liệu
        }

        // Cập nhật hình ảnh mới
        existingProduct.getImages().addAll(newImages);

        // Lưu sản phẩm vào cơ sở dữ liệu
        productService.save(existingProduct);

        return new ModelAndView("redirect:/admin/product/index");
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
