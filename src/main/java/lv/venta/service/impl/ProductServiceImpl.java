package lv.venta.service.impl;

import jakarta.validation.constraints.NotNull;
import lv.venta.model.Product;
import lv.venta.service.ICRUDProductSevice;
import lv.venta.service.IFilterProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ICRUDProductSevice, IFilterProductService {

    private ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(new Product("Daniels", "Balika", 0.99f,1),new Product("Danis", "Bika", 0.99f,6), new Product("iels", "ika", 0.99f,8)));

    @Override

    public Product crateProduct(Product product) throws Exception {
        if (product == null) {
            throw new Exception("Error: product is null");
        }
        for (Product tempP : allProducts) {
            if (tempP.getTitle().equals(product.getTitle()) && tempP.getPrice() == product.getPrice() && tempP.getDescription().equals(product.getDescription())) {
                tempP.setQuantity(tempP.getQuantity() + product.getQuantity());
                return tempP;

            }
        }
        return null;
    }

    @Override
    public ArrayList<Product> retrieveAll() throws Exception {
        if (allProducts.isEmpty()) {
            throw new Exception("Error: there are no products");
        }
        return allProducts;
    }

    @Override
    public Product retrieveById(int id) throws Exception {
        if (allProducts.isEmpty()) {
            throw new Exception("Error: there are no products");
        }
        for (Product tempP : allProducts) {
            if (tempP.getId() == id) {
                return tempP;
            }
        }
        throw new Exception("Error: there are no products");
    }

    @Override
    public void updateById(int id, Product product) throws Exception {
        if (allProducts.isEmpty()) {
            throw new Exception("Error: there are no products");
        }
        for (Product tempP : allProducts) {
            if (tempP.getId() == id) {
                tempP.setTitle(product.getTitle());
                tempP.setPrice(product.getPrice());
                tempP.setDescription(product.getDescription());
                tempP.setQuantity(product.getQuantity());
                return;
            }
        }
        throw new Exception("Error: there are no products");
    }

    @Override
    public Product deleteById(int id) throws Exception {
        Product product = retrieveById(id);
        allProducts.remove(product);
        return product;
    }

    @Override
    public ArrayList<Product> filterProductByPriceThreshold(float priceThreshold) throws Exception {
        if (priceThreshold <= 0 || priceThreshold > 10000) {
            throw new Exception("PriceThreshold should be between 0 and 10000");
        }
        if (allProducts.isEmpty()) {
            throw new Exception("Error: there are no products");
        }
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product tempP : allProducts) {
            if (tempP.getPrice() <= priceThreshold) {
                filteredProducts.add(tempP);
            }
        }
        return filteredProducts;
    }

    @Override
    public ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception {
        if (quantityThreshold <= 0 || quantityThreshold > 10000) {
            throw new Exception("QuantityThreshold should be between 0 and 10000");
        }
        if (allProducts.isEmpty()) {
            throw new Exception("Error: there are no products");
        }
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product tempP : allProducts) {
            if (tempP.getQuantity() <= quantityThreshold) {
                filteredProducts.add(tempP);
            }
        }
        return filteredProducts;
    }

    @Override
    public ArrayList<Product> filterByTitleDescription(String titleDescription) throws Exception {
        if (titleDescription == null) {
            throw new Exception("titleDescription is null");
        }
        if (allProducts.isEmpty()) {
            throw new Exception("Error: there are no products");
        }
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product tempP : allProducts) {
            if (tempP.getTitle().contains(titleDescription) || tempP.getDescription().contains(titleDescription)) {
                filteredProducts.add(tempP);
            }
        }
        return filteredProducts;
    }
}
