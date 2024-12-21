package iuh.fit.se.controllers;

import iuh.fit.se.entities.Product;
import iuh.fit.se.services.CategoryService;
import iuh.fit.se.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quanly")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    private String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "list";
    }

    @GetMapping("/them")
    private String formThem(Model model) {
        Product product = new Product();
        product.setActive(true);

        model.addAttribute("product",product);
        model.addAttribute("categorys",categoryService.findAll());
        return "them";
    }

    @PostMapping("/them")
    private String them(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("categorys",categoryService.findAll());
            return "them";
        }
        productService.save(product);
        return "redirect:/quanly";
    }

    @PostMapping("/search")
    private String search(@RequestParam(name="ma")String ma, @RequestParam(name = "ten")String ten,@RequestParam(value = "active", required = false,defaultValue = "true") boolean active, Model model) {
        System.out.println(ma + ten + active);
        model.addAttribute("products",productService.findByCodeAndNameCategory(ma,ten,active));
        return "list";
    }
    @GetMapping("/delete/{id}")
    private String delete(@PathVariable("id") int id){
        productService.deleteById(id);
        return "redirect:/quanly";
    }

    @GetMapping("/update/{id}")
    private String formSua(@PathVariable("id")int id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        model.addAttribute("categorys",categoryService.findAll());
        return "sua";
    }
    @PostMapping("/update/{id}")
    private String update(@PathVariable("id")int id,@Valid @ModelAttribute("product") Product product,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("categorys",categoryService.findAll());
            return "sua";
        }
        productService.update(product);
        return "redirect:/quanly";
    }
}

