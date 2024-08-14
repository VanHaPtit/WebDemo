package com.example.WebDemo.Repository;

import com.example.WebDemo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String name);

    Optional<User> findByUserNameAndPassWord(String userName, String password);
}
