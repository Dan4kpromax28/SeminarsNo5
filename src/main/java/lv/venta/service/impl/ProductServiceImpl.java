package lv.venta.service.impl;

import jakarta.validation.constraints.NotNull;
import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.ICRUDProductSevice;
import lv.venta.service.IFilterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ICRUDProductSevice, IFilterProductService {
    @Autowired
  private IProductRepo productRepo;

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
        if (productRepo.count() == 0) {
            throw new Exception("Error: there are no products");
        }
        return (ArrayList<Product>) productRepo.findAll();
    }

    @Override
    public Product retrieveById(int id) throws Exception {
       if (id < 0) throw new Exception("Error");

       if (productRepo.existsById(id)){
           return productRepo.findById(id).get();
       }
       throw new Exception("Product with " + id + " does not exist");
    }

    @Override
    public void updateById(int id, Product product) throws Exception {
        Product updateProduct = retrieveById(id);

        updateProduct.setTitle(product.getTitle());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setQuantity(product.getQuantity());
        productRepo.save(updateProduct); // notiek Update ari DB limeni
    }

    @Override
    public Product deleteById(int id) throws Exception {
        Product product = retrieveById(id);
        productRepo.delete(product);
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

    @Override
    public float calculateProductsTotalValue() throws Exception {
        if (allProducts.isEmpty()) {
            throw new Exception("No products");
        }
        float totalValue = 0;
        for (Product tempP : allProducts) {
            totalValue += tempP.getPrice() * tempP.getQuantity();
        }
        return totalValue;
    }
}
