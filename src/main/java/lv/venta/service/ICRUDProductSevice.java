package lv.venta.service;

import lv.venta.model.Product;

import java.util.ArrayList;

public interface ICRUDProductSevice {
    // CRUD - create - retrieve - update - delete
    public abstract Product crateProduct(Product product) throws Exception;

    public abstract ArrayList<Product> retrieveAll() throws Exception;

    public abstract Product retrieveById(int id) throws Exception;

    public abstract void updateById(int id, Product product) throws Exception;

    public abstract Product deleteById(int id) throws Exception;



}
