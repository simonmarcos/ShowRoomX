package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Config.JwtService;
import com.SistemaGestion.ShowroomX.Model.User;
import com.SistemaGestion.ShowroomX.Repository.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final IUser dao;
    private final JwtService jwtService;

    @Autowired
    public UserService(IUser dao, JwtService jwtService) {
        this.dao = dao;
        this.jwtService = jwtService;
    }


    public User save(User user) {
        if (user.getNameUser().equals("") || user.getPassword().equals("") || user.getRoles().equals("")) {
            return null;
        }
        if (!user.getNameUser().chars().allMatch(Character::isLetterOrDigit)) {
            return null;
        }

        //Encrypting Password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return dao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userResponse = dao.findByNameUser(username);

        //if (!userResponse.getStatus()) return null;
        if (userResponse == null) throw new UsernameNotFoundException("No se encontró el usuario " + username);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + userResponse.getRoles()));

        //Create Token
        String token = this.jwtService.createToken(userResponse.getNameUser(), Arrays.asList("ROLE_" + userResponse.getRoles()));
        System.out.println("EL TOKEN ES: " + token);

        return new org.springframework.security.core.userdetails.User(userResponse.getNameUser(), userResponse.getPassword(), authorityList);
    }
}
