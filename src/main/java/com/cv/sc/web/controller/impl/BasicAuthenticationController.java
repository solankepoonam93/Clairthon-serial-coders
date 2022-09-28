package com.cv.sc.web.controller.impl;

import com.cv.sc.cache.TokenCache;
import com.cv.sc.model.SCEntity;
import com.cv.sc.web.controller.AuthenticationController;
import com.cv.sc.model.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import static com.cv.sc.web.filter.utils.WebUtils.buildAPIResponse;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
@RestController
@RequestMapping("/auth")
public class BasicAuthenticationController<T extends SCEntity> implements AuthenticationController {

    @GetMapping(value = "/basic", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    @Override
    public APIResponse login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        APIResponse apiResponse = null;
        String authorization = httpServletRequest.getHeader("Authorization");
        authorization = authorization.split(" ")[1]; // Basic <value>
        String usernamePass = new String(Base64.getDecoder().decode(authorization), StandardCharsets.UTF_8); // username:password
        String[] split = usernamePass.split(":");

        if("admin".equalsIgnoreCase(split[0]) && "bkpune".equals(split[1])) { // username always case-insensitive
            apiResponse = buildAPIResponse(HttpStatus.OK, buildToken(), null);
        } else {
            apiResponse = buildAPIResponse(HttpStatus.UNAUTHORIZED, null, "You are not Authorized to use this API. Please pass valid token.");
        }

        return apiResponse;
    }

    private String buildToken() {
        String token = UUID.randomUUID().toString();
        TokenCache.addToken(token);
        return token;
    }

}
