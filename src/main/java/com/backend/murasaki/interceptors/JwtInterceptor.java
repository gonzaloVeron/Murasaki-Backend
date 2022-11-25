package com.backend.murasaki.interceptors;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.murasaki.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        DecodedJWT credentials = null;
        String jwt = request.getHeader("Authorization");
        if(jwt == null){
            response.sendError(401, "Token not provided");
            return false;
        }
        try{
            credentials = this.jwtService.verify(jwt);
        }catch (TokenExpiredException e){
            response.sendError(401, "Provided token is expired");
            return false;
        }
        request.setAttribute("user_role", credentials.getClaim("role").asString());
        request.setAttribute("user_id", credentials.getClaim("sub").asInt());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
