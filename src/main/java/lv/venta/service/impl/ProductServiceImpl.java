package lv.venta.service.impl;

import lv.venta.model.Product;
import lv.venta.service.ICRUDProductSevice;
import lv.venta.service.IFilterProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ICRUDProductSevice, IFilterProductService {
    @Override
    public Product crateProduct(Product product) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Product> retrieveAll() throws Exception {
        return null;
    }

    @Override
    public Product retrieveById(int id) throws Exception {
        return null;
    }

    @Override
    public void updateById(int id, Product product) throws Exception {

    }

    @Override
    public Product deleteById(int id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Product> filterProductByPriceThreshold(float priceThreshold) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Product> filterByTitleDescription(String titleDescription) throws Exception {
        return null;
    }
}
