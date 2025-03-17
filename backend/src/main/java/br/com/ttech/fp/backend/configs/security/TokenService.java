package br.com.ttech.fp.backend.configs.security;

import br.com.ttech.fp.backend.common.entity.User;
import br.com.ttech.fp.backend.common.enums.Messages;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generate(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("fp")
                    .withSubject(user.getUsername())
                    .withExpiresAt(this.genExpirationDate())
                    .sign(algorithm);
            return token;
        }
        catch (JWTCreationException je){
            throw new RuntimeException(Messages.ERROR_WHILE_GENERATE_TOKEN.getMessage(), je);
        }

    }

    public String validate(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("fp")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch(JWTVerificationException je){
            return null;
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

