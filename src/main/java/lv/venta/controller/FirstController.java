package lv.venta.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import lv.venta.model.Product;
import lv.venta.service.ICRUDProductSevice;
import lv.venta.service.IFilterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class FirstController {
    @Autowired
    private ICRUDProductSevice crudService;

    @Autowired
    private IFilterProductService filterService;

    @GetMapping("/hello") //
    public String getHello() {
        System.out.println("Hello from string");
        return "hello-page"; //tiek paradita hello-page.html lapa
    }

    @GetMapping("/hello/msg") //localhost:8080/hello/msg
    public String getHelloMsg(Model model) {
        //nosaukums          vērtība
        model.addAttribute("mymsg", "Ziņa no backend!! Hei");
        return "msg-page";
    }

    @SneakyThrows
    @GetMapping("/product/test")
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

    @GetMapping("/product/list") //static
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

    @GetMapping("/product/all/{id}")
    public String getProductById(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("myobj", crudService.retrieveById(id));
            return "show-product-page";
        } catch (Exception e) {
            model.addAttribute("msg", "Wrong id");
            return "error-page";
        }
    }

    @GetMapping("/product/insert")
    public String getProductInsert(Model model) { //localhost:8080/product/insert
        model.addAttribute("product", new Product()); // padodam tuksu produktu kurs bus aizpildits
        return "insert-product-page"; // tiek paradita lapa kur var ievadit datus
    }

    @PostMapping("/product/insert")
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

    @GetMapping("/error")
    public String getError() {
        return "error-page";
    }
}
