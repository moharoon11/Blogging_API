package dev.haroon.blog.blogging.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.haroon.blog.blogging.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String name);
}
