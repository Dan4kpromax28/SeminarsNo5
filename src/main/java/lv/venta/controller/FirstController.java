package lv.venta.controller;

import lv.venta.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class FirstController {
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

    @GetMapping("/product/test")
    public String getProductTest(Model model) {
        Product myProduct = new Product("Abols", "Sarkans", 0.99f, 5);
        model.addAttribute("myobj", myProduct);
        return "show-product-page";
    }

    @GetMapping("/product/list")
    public String getProductsList(Model model) {
        Product product1 = new Product("Abols", "Labais", 0.87f, 5);
        Product product2 = new Product("Ediens", "Labais", 0.54f,4);
        Product product3 = new Product("Ediens", "Labais", 0.54f,4);
        ArrayList<Product> newList = new ArrayList<Product>();
        newList.add(product1);
        newList.add(product2);
        newList.add(product3);
        model.addAttribute("myobjs", newList);
        return "show-product-list";
    }

}
