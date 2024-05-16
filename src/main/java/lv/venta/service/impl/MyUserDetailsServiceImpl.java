package lv.venta.service.impl;

import lv.venta.config.MyUserDetails;
import lv.venta.model.MyUser;
import lv.venta.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private IUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepo.loadUserByUsername(username);

        if (user == null) {throw new UsernameNotFoundException(username + "not found");}
        MyUserDetails userDetails = new MyUserDetails(user);

        return userDetails;
    }
}
