package dev.haroon.blog.blogging.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.haroon.blog.blogging.entities.Category;
import dev.haroon.blog.blogging.entities.Post;
import dev.haroon.blog.blogging.entities.User;
	
public interface PostRepo extends JpaRepository<Post, Integer> {
	
	Set<Post> findByUser(User user);
	
	Set<Post> findByCategory(Category category);

	Set<Post> findByTitleLike(String title);

}