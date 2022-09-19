package com.cv.sc.web.filter;

import com.cv.sc.cache.TokenCache;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        try {
            if(! isBypassUrl(servletRequest.getRequestURI())) {
                // get token
                String authorization = servletRequest.getHeader("Authorization");
                if(authorization == null || authorization.isEmpty()) {
                    // return error
                    servletResponse.sendError(UNAUTHORIZED.value(), "Token not present or it is invalid");
                    return;
                }
                String token = authorization.split(" ")[1];  // Authorization: Bearer <token>
                if(TokenCache.isTokenPresent(token)) {
                    chain.doFilter(request, response);
                } else {
                    servletResponse.sendError(UNAUTHORIZED.value(), "Invalid token. Please login again and get a new valid token");
                    // returns
                }
            } else { // all good
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            servletResponse.sendError(INTERNAL_SERVER_ERROR.value(), "Something went wrong during authentication");
        }
    }

    private boolean isBypassUrl(String url) {
        return url.contains("/auth/basic");
    }
}
