package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor //lonbok
@ToString
//@AllArgsConstructor
@Table(name = "AuthorityTable")
@Entity

public class MyAuthority {
    @Column(name = "Ida")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ida;
// new
    @NotNull
    @Pattern(regexp = "[A-Z]{1}[a-z]+", message = "Only letters and space")
    @Size(min = 2, max = 20)
    @Column(name = "Title")
    private String title;
    @ManyToMany
    @JoinTable(name = "UserAuthorityTable",
    joinColumns = @JoinColumn(name="Ida"),
    inverseJoinColumns = @JoinColumn(name="Idu"))
    @ToString.Exclude
    private Collection<MyUser> users = new ArrayList<MyUser>();

    public MyAuthority(String title){
        setTitle(title);
    }


    public void addUser(MyUser user) {
        if(!users.contains(user))
            users.add(user);
    }

    public void removeUser(MyUser user) {
        if(users.contains(user))
            users.remove(user);
    }
}
