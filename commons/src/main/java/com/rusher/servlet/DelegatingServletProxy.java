package com.rusher.servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelegatingServletProxy extends HttpServlet {
    private Servlet target;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        final WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        target = webApplicationContext.getBean(config.getServletName(), Servlet.class);
        target.init(config);
    }

    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        target.service(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
        target.destroy();
    }
}
