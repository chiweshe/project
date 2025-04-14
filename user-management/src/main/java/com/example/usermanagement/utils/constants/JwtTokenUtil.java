package com.example.usermanagement.utils.constants;

import com.example.usermanagement.utils.responses.AuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${securityframework.secretKey}")
    private String secret;
    @Value("${session.expireTime}")
    private long sessionExpiryTime;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String getUsernameFromToken(String token) {

        try{

            log.info("token passed : {}", token);

            if(token.startsWith("Bearer ")){
                token = token.replace("Bearer ", "");
            }

            log.info("token cleaned : {}", token);

            return getClaimFromToken(token, Claims::getSubject);

        }catch(Exception ex){

            return "undifined";
        }


    }

    public Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(token, Claims::getExpiration);

    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);

    }

    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    }

    private Boolean isTokenExpired(String token) {

        final Date expiration = getExpirationDateFromToken(token);

        return expiration.before(new Date());

    }


    public String generateUserLoginToken(String username, String memberType) {

        Map<String, Object> claims = new HashMap<>();

        return doGenerateToken(claims, username, memberType);

    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String memberType) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(new Date(System.currentTimeMillis() + sessionExpiryTime * 1000)).setId(memberType)

                .signWith(SignatureAlgorithm.HS512, secret).compact();

    }

    public Boolean validateToken(String token, AuthenticationResponse authenticationResponse) {

        final String username = getUsernameFromToken(token);

        return (username.equals(authenticationResponse.getResponseBody().getUserAttributes().getUsername()) && !isTokenExpired(token));

    }

    public Claims getAllClaimsFromProvidedToken(String token) {

        try{

            log.info("token passed : {}", token);

            if(token.startsWith("Bearer ")){
                token = token.replace("Bearer ", "");
            }

            log.info("token cleaned : {}", token);

            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        }catch(Exception ex){

            return null;
        }
    }

}
