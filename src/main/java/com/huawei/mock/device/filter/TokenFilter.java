package com.huawei.mock.device.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebFilter
public class TokenFilter implements Filter {

    private String[] unTokenFilterPath = null;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        servletResponse.setContentType("application/json;charset=UTF-8");
        // 放行的路径
        boolean match = Arrays.stream(unTokenFilterPath).anyMatch(item -> item.equals(request.getRequestURI()));
        if (match) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = request.getSession();
        if (session == null) {
            return;
        }
        String token = (String) session.getAttribute("token");
        if (token == null) {
            PrintWriter writer = servletResponse.getWriter();
            writer.println("{\"message\":\"请先登录！\"}");
            writer.flush();
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String unTokenFilter = filterConfig.getInitParameter("unTokenFilter");
        if (unTokenFilter != null) {
            unTokenFilterPath = unTokenFilter.split(",");
        }
    }

    @Override
    public void destroy() {

    }
}
