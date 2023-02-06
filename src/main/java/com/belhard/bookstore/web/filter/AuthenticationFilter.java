package com.belhard.bookstore.web.filter;

import com.belhard.bookstore.data.entity.Role;
import com.belhard.bookstore.service.dto.UserDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        Object object = req.getSession().getAttribute("user");
        UserDto user = (UserDto) object;
        if (user.getRole() == Role.CUSTOMER) {
            res.sendRedirect("/error");
            return;
        }
        chain.doFilter(req, res);
    }
}
