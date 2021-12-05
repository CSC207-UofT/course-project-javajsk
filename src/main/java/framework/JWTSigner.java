package framework;

import adapters.dam.TokenSigner;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.io.Serializable;
import java.security.Key;
import java.security.SecureRandom;

public class JWTSigner implements TokenSigner {



    @Override
    public String generateToken(String userId) {
        String random_token = randomString(5);
        return userId + random_token;
    }

    @Override
    public String getIdFromToken(String token) {
        try {
            return token.substring(0, token.length() - 5);
        }catch (Exception e){
            return "ERROR: " + e.getMessage();
        }
    }


    String randomString(int len){
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }


}
