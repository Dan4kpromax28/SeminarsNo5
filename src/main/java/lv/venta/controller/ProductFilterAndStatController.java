package lv.venta.controller;
import lv.venta.model.Product;
import lv.venta.service.IFilterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;

@RestController
@RequestMapping("/product/info")
public class ProductFilterAndStatController {
    @Autowired
    private IFilterProductService filterService;

    @GetMapping("filter/price")
    public String getProductInfoFilterByPrice(@RequestParam("treshold") float treshold, Model model) {
        try {
            ArrayList<Product> result = filterService.filterProductByPriceThreshold(treshold);
            model.addAttribute("XXX", result);
            return "XXX";

        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("filter/quantity")
    public String getProductInfoFilterByQuantity(@RequestParam("treshold") int treshold, Model model) {
        try {
            ArrayList<Product> result = filterService.filterProductByQuantityThreshold(treshold);
            model.addAttribute("myobjs", result);
            return "show-product-list";

        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("filter/text")
    public String getProductInfoFilterByTitleOrDescription(@RequestParam("keywords") String keywords, Model model) {
        try {
            ArrayList<Product> result = filterService.filterByTitleDescription("keywords");
            model.addAttribute("myobjs", result);
            return "show-product-list";

        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("total")
    public String getProductInfoFilterByTitleOrDescription(Model model) {
        try {
           float result = filterService.calculateProductsTotalValue();
            model.addAttribute("mymsg", "Total: " + result + "eur");
            return "msg-page";

        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }


}
