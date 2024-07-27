package com.example.WebDemo.Service;//package com.example.CRUD_SQL_JPA.Service;
//
//import com.example.CRUD_SQL_JPA.Model.CustomUserDetails;
//import com.example.CRUD_SQL_JPA.Model.User;
//import com.example.CRUD_SQL_JPA.Model.User_Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserService userService ;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.findByUserName(username);
//        if(user == null){
//            throw new UsernameNotFoundException("Error");
//        }
//        Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
//        Set<User_Role> roles = user.getUserRoles();
//        for (User_Role account_Role : roles) {
//            grantedAuthoritySet.add(new SimpleGrantedAuthority(account_Role.getRole().getName()));
//        }
//        return new CustomUserDetails(grantedAuthoritySet, user.getEmail(), user.getFullName(), user.getPassWord(), user.getUserName(), user.getGender(), user.getAddress(), user.getTelephone(), user.getEnabled(),true,true,true);
//    }
//}
