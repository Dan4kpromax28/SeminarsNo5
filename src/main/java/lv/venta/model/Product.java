package lv.venta.model;

import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString
//@AllArgsConstructor
public class Product {
    private static long counter = 0;
    private long id;
    @NotNull
    @Pattern(regexp = "[A-Z]{1}[a-z]+")
    @Size(min = 3, max = 20)
    private String title;

    @Min(0)
    @Max(10000)
    private float price;
    @NotNull
    @Pattern(regexp = "[A-Z]{1}[a-z]+")
    @Size(min = 3, max = 20)
    private String description;
    private int quantity;
    private String surname;
    @Min(0)
    @Max(100)
    private int age;

    public void setId(){
        this.id = counter;
        counter++;
    }

    public Product(){

    }
}
