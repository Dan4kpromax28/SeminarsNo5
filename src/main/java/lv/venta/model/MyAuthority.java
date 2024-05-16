package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor 
@ToString
@Table(name = "AuthorityTable")
@Entity
public class MyAuthority {
    @Column(name = "Ida")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ida;

    @NotNull
    @Pattern(regexp = "[A-Z]{1}[a-z]+", message = "Only letters and space")
    @Size(min = 2, max = 20)
    @Column(name = "Title")
    private String title;

}
