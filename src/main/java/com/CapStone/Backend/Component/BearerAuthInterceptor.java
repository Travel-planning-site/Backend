package com.CapStone.Backend.Component;

import com.CapStone.Backend.Service.LoginBoard.JwtManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);

    private AuthorizationExtractor authorizationExtractor;
    private JwtManager JwtManager;

    public BearerAuthInterceptor(AuthorizationExtractor authorizationExtractor, JwtManager jwtTokenProvider) {
        this.authorizationExtractor = authorizationExtractor;
        this.JwtManager = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info(">>> interceptor.preHandle 호출");
        String token = authorizationExtractor.extract(request, "Bearer");
        System.out.println("inter token : " + token);
        if (token.isEmpty()) {
            return true;
        }

        if (!JwtManager.validateToken(token)) {
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }

        Long id = JwtManager.getSubject(token);
        request.setAttribute("id", id);
        return true;
    }
}
