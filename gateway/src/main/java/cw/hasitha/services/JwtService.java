package cw.hasitha.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;

/********************************************************************
 * --
 * Author: hasitha
 * Date: 10/11/2023
 * --
 ********************************************************************/

@Component
@Slf4j
public class JwtService {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(getSignKey()).build();

    public boolean hasRole(String request, String role) {
        final Claims claims = extractAllClaims(request);
        String roles = claims.get("ROLE").toString();
        log.info("roles : {}", roles);
        return roles.equals(role);
    }


    private Claims extractAllClaims(String token) {
        log.info("extractAllClaims : {}", token);
        return this.jwtParser.parseClaimsJws(token).getBody();
    }


}