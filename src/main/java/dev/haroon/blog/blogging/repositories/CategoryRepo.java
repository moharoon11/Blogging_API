package dev.haroon.blog.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.haroon.blog.blogging.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
