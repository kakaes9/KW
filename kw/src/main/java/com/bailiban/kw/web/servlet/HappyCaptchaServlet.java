package com.bailiban.kw.web.servlet;

import com.ramostear.captcha.HappyCaptcha;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/HappyCaptchaServlet")
public class HappyCaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //
        HappyCaptcha.require(request,response).length(4).height(50).build().finish();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //
        doGet(request,response);
    }
}
