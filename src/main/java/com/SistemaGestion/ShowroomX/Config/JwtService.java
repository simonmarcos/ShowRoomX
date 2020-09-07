package com.SistemaGestion.ShowroomX.Config;

import com.SistemaGestion.ShowroomX.Exceptions.JwtException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private static final String BEARER = "Bearer";
    private static final String ISSUER = "marcos-simon";
    private static final String EXPIRE_IN_MILLISECOND = "360000";
    private static final String ROLES = "roles";
    private static final String USER = "user";
    private static final String SECRET = "bWFyY29zLXNpbW9u";

    public String createToken(String user, List<String> roles) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim(USER, user)
                .withArrayClaim(ROLES, roles.toArray(new String[0]))
                .withIssuedAt(new Date())
                .withNotBefore(new Date())
                .sign(Algorithm.HMAC256(SECRET));
    }

    public boolean isBearer(String authorization) {
        return authorization != null && authorization.startsWith(BEARER) && authorization.split("\\.").length == 3;
    }

    public String user(String authorization) throws JwtException {
        return this.verify(authorization).getClaim(USER).asString();
    }

    public List<String> roles(String authorization) throws JwtException {
        return Arrays.asList(this.verify(authorization).getClaim(ROLES).asArray(String.class));
    }

    private DecodedJWT verify(String authorization) throws JwtException {
        if (!this.isBearer(authorization)) throw new JwtException("Esto no es un Bearer");
        try {
            return JWT.require(Algorithm.HMAC256(Base64.getDecoder().decode(SECRET)))
                    .withIssuer(ISSUER).build()
                    .verify(authorization.substring(BEARER.length()));
        } catch (Exception exception) {
            throw new JwtException("JWT es incorrecto. " + exception.getMessage());
        }
    }
}
