package com.example.WebDemo.Service;


import com.example.WebDemo.Model.User;

public interface UserService {
    User findByUserName(String name);
}
