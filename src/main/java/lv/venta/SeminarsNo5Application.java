package lv.venta;

import lv.venta.model.MyAuthority;
import lv.venta.model.MyUser;
import lv.venta.model.Product;
import lv.venta.repo.IMyAuthorityRepo;
import lv.venta.repo.IProductRepo;
import lv.venta.repo.IUserRepo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SeminarsNo5Application {

    public static void main(String[] args) {

        SpringApplication.run(SeminarsNo5Application.class, args);
    }
    @Bean // izsaksirs si funkcija automatiski lidz ko sistema bus palaisa
    public CommandLineRunner testDatabaseLayer(IProductRepo productRepo, IMyAuthorityRepo authRepo, IUserRepo userRepo){
        return new CommandLineRunner(){
            public void run (String... args) {
                Product p1 = new Product("Daniels", "Balika", 0.99f, 1);
                Product p2 = new Product("Danis", "Bika", 0.99f, 6);
                Product p3 =  new Product("Aels", "Oika", 0.99f, 8);
                productRepo.save(p1);
                productRepo.save(p2);
                productRepo.save(p3);

                MyAuthority a1 = new MyAuthority("ADMIN");
                MyAuthority a2 = new MyAuthority("USER");
                authRepo.save(a1);
                authRepo.save(a2);

                PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

                MyUser u1 = new MyUser("admin", encoder.encode("admin"), a1);
                MyUser u2 = new MyUser("user", encoder.encode("user"), a2);
                MyUser u3 = new MyUser("both", encoder.encode("both"), a1, a2);

                userRepo.saveAll(Arrays.asList(u1,u2,u3));

                a1.addUser(u1);
                a1.addUser(u3);

                a2.addUser(u2);
                a2.addUser(u3);

                authRepo.saveAll(Arrays.asList(a1, a2));
            }
        };
    }

}
