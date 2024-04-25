package lv.venta.repo;

import lv.venta.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepo extends CrudRepository <Product, Integer> {

}
