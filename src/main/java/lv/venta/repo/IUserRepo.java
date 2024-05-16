package lv.venta.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lv.venta.model.MyUser;

public interface IUserRepo extends JpaRepository<MyUser, Integer>  {
    
}
