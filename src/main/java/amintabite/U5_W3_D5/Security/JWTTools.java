package amintabite.U5_W3_D5.Security;


import amintabite.U5_W3_D5.Entities.Utente;
import amintabite.U5_W3_D5.Exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Utente utente) {

        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*24*7))
                .subject(String.valueOf(utente.getUtenteId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String accessToken){

        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(accessToken);
        } catch (Exception exception) {
            throw new UnauthorizedException("Errore sul token, riesegui il login !");
        }

    }


    public long extractIdFromToken(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject()
        );
    }

}