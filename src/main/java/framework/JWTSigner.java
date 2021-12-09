package framework;

import adapters.TokenSigner;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

/**
 * Concrete Token Signer
 */
public class JWTSigner implements TokenSigner {
    /**
     * The Key.
     */
    static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Instantiates a new Jwt signer.
     */
    public JWTSigner() {}

    /**
     * Method generates token from given user id
     * @param userId id of user
     * @return string containing user token
     */
    @Override
    public String generateToken(String userId) {

        return Jwts.builder().setSubject(userId).signWith(key).compact();
    }

    /**
     * Method returns id associated with given user token
     * @param token token of user
     * @return string id of user
     */
    @Override
    public String getIdFromToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            return "ERROR: " + e.getMessage();
        }
    }
}