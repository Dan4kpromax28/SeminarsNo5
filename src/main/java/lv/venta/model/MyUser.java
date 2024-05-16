package lv.venta.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "UserTable")
@Getter
@Setter
@NoArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idu")
    private Integer id;

    @Column(unique = true)
    @NotBlank
    @Pattern(regexp = "[A-Za-z]{3,20}")
    private String username;

    @NotBlank
    private String password;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Collection<MyAuthority> authorites = new ArrayList<MyAuthority>();

    public MyUser(String username, String password, MyAuthority ... auths) {
        setUsername(username);
        setPassword(password);
        for (MyAuthority auth : auths) {
            addAuthority(auth);
        }
    }
    public void addAuthority(MyAuthority authority){
        if(!authorites.contains(authority)){
            authorites.add(authority);
        }
    }
    public void removeAuthority(MyAuthority authority){
        if(authorites.contains(authority)){
            authorites.remove(authority);
        }
    }

}
