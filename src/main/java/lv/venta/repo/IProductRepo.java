package lv.venta.repo;

import lv.venta.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IProductRepo extends CrudRepository <Product, Integer> {
//public abstract pec noklusejuma
    // izfiltret productu kuram sakrit ieejas
    // argumentos
    Product findByTitleAndDescriptionAndPrice(String title, String description, float price);

    ArrayList<Product> findByPriceLessThanEqual(float priceThreshold);

    ArrayList<Product> findByQuantityLessThanEqual(int quantityThreshold);


    ArrayList<Product> findByTitleLikeIgnoreCaseOrDescriptionLikeIgnoreCase(String titleDescription, String titleDescription1);
}
