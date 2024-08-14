package com.example.WebDemo.Service;


import com.example.WebDemo.Model.Category;
import com.example.WebDemo.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByUserName(String name);
    void delete(Long id);

    Page<User> findAll(Pageable pageInfo);

    List<User> getAll();

    public Optional<User> findById(Long ID);

    public User save(User user);

    public List<User> findAll();

    boolean isUserExists(String userName, String password);
}
