package org.empit.bibliavetelkedo.wsgui.filter;

import org.empit.bibliavetelkedo.servicelayer.services.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by helmut on 11.04.2017.
 */
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        UserService userBA = UserService.getInstance();

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpSession session = httpServletRequest.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            if (userBA.checkUsername(username)) {
                ((HttpServletResponse) resp).sendRedirect("Home");
                return;
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
