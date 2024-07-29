package com.exercise.process.Repository;

import com.exercise.process.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String mail);

    User findByName(String name);

    User findOneByEmailAndPassword(String email, String password);
}
