package org.empit.bibliavetelkedo.wsgui.filter;

import org.empit.bibliavetelkedo.servicelayer.dto.UserDTO;
import org.empit.bibliavetelkedo.servicelayer.services.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by helmut on 11.04.2017.
 */
public class HomePageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        UserService userService = UserService.getInstance();

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpSession session = httpServletRequest.getSession();

        String username = (String) session.getAttribute("username");

        if (username != null) {
            UserDTO user = userService.getUserByUsername(username);
            if (user != null) {
                ((HttpServletResponse) resp).addCookie(new Cookie("username", username));
                ((HttpServletResponse) resp).addCookie(new Cookie("fname", user.getFirstName()));
                ((HttpServletResponse) resp).addCookie(new Cookie("lname", user.getLastName()));
                chain.doFilter(req, resp);
                return;
            }
        }

        username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        if (username != null && password != null) {
            if (userService.checkUsernameAndPassword(username, password)) {
                session.setAttribute("username", username);
                ((HttpServletResponse) resp).sendRedirect("Home");
                return;
            }
        }

        ((HttpServletResponse) resp).sendRedirect("Login");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
