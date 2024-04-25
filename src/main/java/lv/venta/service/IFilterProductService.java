package lv.venta.service;

import lv.venta.model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IFilterProductService {
    public abstract ArrayList<Product> filterProductByPriceThreshold(float priceThreshold) throws Exception;

    public abstract ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception;

    public abstract ArrayList<Product> filterByTitleDescription(String titleDescription) throws Exception;
    public abstract float calculateProductsTotalValue() throws Exception;


}
