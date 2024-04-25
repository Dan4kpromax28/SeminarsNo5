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













    @GetMapping("/error")
    public String getError() {
        return "error-page";
    }




}
