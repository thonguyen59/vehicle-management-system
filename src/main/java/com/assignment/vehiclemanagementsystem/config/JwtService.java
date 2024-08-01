package com.assignment.vehiclemanagementsystem.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.assignment.vehiclemanagementsystem.constant.Role;
import com.assignment.vehiclemanagementsystem.entity.User;
import com.assignment.vehiclemanagementsystem.payload.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;

@Service
public class JwtService {

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1200000))  // time exists of token - 2 minute
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public UserPrincipal getUserFromToken(String token) {
        String jwtToken = getJwtFromBearerToken(token);

        final Claims claims = extractAllClaims(jwtToken);
        UserPrincipal user = new UserPrincipal();
        user.setUsername(claims.getSubject());
        user.setId(((Number) claims.get("userId")).longValue());
        user.setRole((String) claims.get("role"));
        return user;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String getJwtFromBearerToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public UserPrincipal extractUserPrincipal(String token) {
        Claims claims = extractAllClaims(token);
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setUsername(claims.getSubject());
        userPrincipal.setId(((Number) claims.get("userId")).longValue());
        userPrincipal.setRole((String) claims.get("role"));
        return userPrincipal;
    }
}
