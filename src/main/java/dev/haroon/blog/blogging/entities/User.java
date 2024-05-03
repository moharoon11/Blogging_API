package dev.haroon.blog.blogging.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
public class User /** implements UserDetails **/ {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="user_name", nullable=false, length=100)
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Post> postList;
	
//	@ManyToMany(cascade= {
//			CascadeType.DETACH,
//			CascadeType.MERGE,
//			CascadeType.PERSIST,
//			CascadeType.REFRESH
//	}, fetch=FetchType.EAGER)
//	@JoinTable(name="user_role",
//	joinColumns = @JoinColumn(name="users", referencedColumnName="id"),
//	inverseJoinColumns = @JoinColumn(name="role", referencedColumnName="id"))
//	private Set<Role> roles = new HashSet<>();
//	
	@Override
	public int hashCode() {
	    return Objects.hash(id); 
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    User user = (User) obj;
	    return Objects.equals(id, user.id); 
	}







}
