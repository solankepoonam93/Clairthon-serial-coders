package com.cv.sc.web.controller;

import com.cv.sc.models.APIResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
public interface AuthenticationController extends SCController {
    APIResponse login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;
}
