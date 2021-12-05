package framework;

import adapters.dam.TokenSigner;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTSigner implements TokenSigner {
    Key key;

    public JWTSigner() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
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
