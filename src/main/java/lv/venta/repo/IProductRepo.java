package lv.venta.repo;

import lv.venta.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepo extends CrudRepository <Product, Integer> {
//public abstract pec noklusejuma
    // izfiltret productu kuram sakrit ieejas
    // argumentos
    Product findByTitleAndDescriptionAndPrice(String title, String description, float price);
}
