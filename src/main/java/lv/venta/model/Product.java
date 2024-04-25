package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor //lonbok
@ToString
//@AllArgsConstructor
@Table(name = "ProductTable")
@Entity
public class Product {
    private static long counter = 0;
    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Pattern(regexp = "[A-Z]{1}[a-z]+", message = "Only letters and space")
    @Size(min = 3, max = 20)
    @Column(name = "Title")
    private String title;

    @Min(0)
    @Max(10000)
    @Column(name = "Price")
    private float price;
    @NotNull
    @Pattern(regexp = "[A-Z]{1}[a-z]+")
    @Size(min = 3, max = 20)
    private String description;
    @NotNull
    @Min(0)
    @Max(10000)
    @Column(name = "Quantity")
    private int quantity;
    private String surname;
    @Min(0)
    @Max(100)
    private int age;



    public Product(String title, String description, float price, int quantity){

        setTitle(title);
        setDescription(description);
        setPrice(price);
        setQuantity(quantity);

    }
}
