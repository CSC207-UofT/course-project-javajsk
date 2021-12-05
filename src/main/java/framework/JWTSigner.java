package framework;

import adapters.dam.TokenSigner;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;

public class JWTSigner implements TokenSigner {
    static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);;

    public JWTSigner() {
    }

    @Override
    public String generateToken(String userId) {

        return Jwts.builder().setSubject(userId).signWith(key).compact();
    }

    @Override
    public String getIdFromToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            return "ERROR: " + e.getMessage();
        }
    }

}