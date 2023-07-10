package uz.pdp.uyvazifacard.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    long expireTime = 36_000_000;
    String kalitSuz = "BuTokenniMaxfiySuziHechKimBilmasin12345678980";

    public String generateToken(String username) {
        Date date = new Date(System.currentTimeMillis() + expireTime);
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, kalitSuz)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(kalitSuz)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
public String getUsername(String token){
    return Jwts
            .parser()
            .setSigningKey(kalitSuz)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
}

}
