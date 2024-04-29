package lv.venta;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeminarsNo5Application {

    public static void main(String[] args) {

        SpringApplication.run(SeminarsNo5Application.class, args);
    }
    @Bean // izsaksirs si funkcija automatiski lidz ko sistema bus palaisa
    public CommandLineRunner testDatabaseLayer(IProductRepo productRepo){
        return new CommandLineRunner(){
            public void run (String... args) {
                Product p1 = new Product("Daniels", "Balika", 0.99f, 1);
                Product p2 = new Product("Danis", "Bika", 0.99f, 6);
                Product p3 =  new Product("Aels", "Oika", 0.99f, 8);
                productRepo.save(p1);
                productRepo.save(p2);
                productRepo.save(p3);

            }
        };
    }

}
