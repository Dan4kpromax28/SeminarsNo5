package lv.venta.controller;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lv.venta.model.Product;
import lv.venta.service.ICRUDProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("product")

public class ProductCRUDContoller {
    @Autowired
    private ICRUDProductSevice crudService;
    @SneakyThrows
    @GetMapping("/test")
    public String getProductTest(Model model) {
        Product myProduct = new Product("Abols", "Sarkans", 0.99f, 5);
        try {
            model.addAttribute("myobj", crudService.retrieveAll().get(0));
            return "show-product-page";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }

    }

    @GetMapping("/list") //static
    public String getProductsList(Model model) {
        try {
            model.addAttribute("myobjs", crudService.retrieveAll());
            return "show-product-list";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }

    }

    @GetMapping("/productone") //localhost:8080/productone?id=3
    public String getProductone(@RequestParam("id") int id, Model model) {
        try {
            model.addAttribute("myobj", crudService.retrieveById(id));
            return "show-product-page";
        } catch (Exception e) {
            model.addAttribute("msg", "Wrong id");
            return "error-page";
        }

    }

    @GetMapping("/all/{id}")
    public String getProductById(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("myobj", crudService.retrieveById(id));
            return "show-product-page";
        } catch (Exception e) {
            model.addAttribute("msg", "Wrong id");
            return "error-page";
        }
    }

    @GetMapping("/insert")
    public String getProductInsert(Model model) { //localhost:8080/product/insert
        model.addAttribute("product", new Product()); // padodam tuksu produktu kurs bus aizpildits
        return "insert-product-page"; // tiek paradita lapa kur var ievadit datus
    }

    @PostMapping("/insert")   //localhost:8080//product/insert"
    public String insertProduct(@Valid Product product, BindingResult result) { // iegproductust aizpilditu produktu

        if (result.hasErrors()) {
            return "insert-product-page";
        } else {
            try {
                crudService.crateProduct(product);
                return "redirect:/product/list";
            } catch (Exception e) {
                return "redirect:/error";
            }
        }
    }



    @GetMapping("/update") //localhost:8080/product/update
    public String getProductUpdateById(@RequestParam("id") int id, Model model) {
        try {
            Product product = crudService.retrieveById(id);
            model.addAttribute("product", product);
            model.addAttribute("id", id);
            return "update-product-page"; // tirk paradita update product lapa
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }
    @PostMapping("/update")   //localhost:8080//product/insert"
    public String postProductUpdate(@RequestParam("id") int id, @Valid Product product, BindingResult result, Model model) { // iegproductust aizpilditu produktu

        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "update-product-page";
        } else {
            try {
                crudService.updateById(id,product);
                return "redirect:/product/list";
            } catch (Exception e) {
                return "redirect:/error";
            }
        }
    }

    @GetMapping("/delete") // localhost:8080/product/delete?id=2
    public String getProductDeleteById(@RequestParam("id") int id , Model model) { // iegproductust aizpilditu produkt
        try {
            crudService.deleteById(id);
            model.addAttribute("myobjs", crudService.retrieveAll());
            return "show-product-list";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }

    }
}





