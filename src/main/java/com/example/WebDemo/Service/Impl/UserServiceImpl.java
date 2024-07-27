package com.example.WebDemo.Service.Impl;

//import com.example.CRUD_SQL_JPA.Model.User;
//import com.example.CRUD_SQL_JPA.Repository.UserRepository;
//import com.example.CRUD_SQL_JPA.Service.UserService;
import com.example.WebDemo.Model.User;
import com.example.WebDemo.Repository.UserRepository;
import com.example.WebDemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository ;

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUserName(name);
    }
}
