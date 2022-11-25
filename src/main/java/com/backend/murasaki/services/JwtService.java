package com.backend.murasaki.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Autowired Environment env;

    public String create(int user_id, String roleName){
        Date expireDate = new Date();
        long exp = (expireDate.getTime() + 210 * 24 * 3600) / 1000;
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwtSecret"));
        return JWT.create().withClaim("exp", exp).withClaim("sub", user_id).withClaim("role", roleName).withIssuer(env.getProperty("jwtIssuer")).sign(algorithm);
    }

    public DecodedJWT verify(String token){
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwtSecret"));
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(env.getProperty("jwtIssuer")).build();
        return verifier.verify(token);
    }

}
