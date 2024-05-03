package dev.haroon.blog.blogging.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
	
	private String token;

	public void setToken(String token2) {
		this.token = token2;
	}

}
