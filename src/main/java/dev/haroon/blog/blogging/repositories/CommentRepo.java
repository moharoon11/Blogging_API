package dev.haroon.blog.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.haroon.blog.blogging.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
