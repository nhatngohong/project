package com.nhat.project.login;

import com.nhat.project.user.CustomUserDetails;
import com.nhat.project.user.CustomUserDetailsService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Slf4j
public class JwtTokenProvider {
    private final String JWT_SECRET = "nhat";
    private final long JWT_EXPIRATION = 1000000007;
    public String generateToken(CustomUserDetails userDetails){
        Date now = new Date();
        Date expire = new Date(now.getTime() +JWT_EXPIRATION);
        return Jwts
                .builder()
                .setSubject(Integer.toString(userDetails.getUser().getId()))
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
