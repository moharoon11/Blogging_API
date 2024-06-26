//package dev.haroon.blog.blogging.security;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtService {
//
//	private static final String SECRET_KEY = "ptN+mkTp1uys5uSNYSd7esZgkAZ7q6M3bQWmhtCWrIb133YPhqgRSjrRLZDB26Mw\r\n";
//			
//	public String extractUsername(String token) {
//		return extractClaim(token, Claims::getSubject);
//	}
//	
//	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//		Claims claims = extractAllClaims(token);
//		return claimResolver.apply(claims);
//	}
//	
//	
//    private Claims extractAllClaims(String token) {
//		return Jwts
//			   .parserBuilder()
//			   .setSigningKey(getSigningKey())
//			   .build()
//			   .parseClaimsJws(token)
//			   .getBody();
//	}
//    
//    private Key getSigningKey() {
//    	byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//    	return Keys.hmacShaKeyFor(keyBytes);
//    }
//    
//    public String generateToken(UserDetails userDetails) {
//    	return Jwts
//    			.builder()
//    			.addClaims(new HashMap<String, Object>())
//    			.setSubject(userDetails.getUsername())
//    			.setIssuedAt(new Date(System.currentTimeMillis()))
//    			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//    			.signWith(getSigningKey(), SignatureAlgorithm.HS256)
//    			.compact();
//    }
//    
//    
//    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//    	return Jwts
//    			.builder()
//    			.addClaims(extraClaims)
//    			.setSubject(userDetails.getUsername())
//    			.setIssuedAt(new Date(System.currentTimeMillis()))
//    			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//    			.signWith(getSigningKey(), SignatureAlgorithm.HS256)
//    			.compact();
//    }
//    
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//    	final String username = extractUsername(token);
//    	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//	
//    
//    private boolean isTokenExpired(String token) {
//    	return extractExpiration(token).before(new Date());
//    }
//
//
//	private Date extractExpiration(String token) {
//		return extractClaim(token, Claims::getExpiration);
//	}
//	
//}
