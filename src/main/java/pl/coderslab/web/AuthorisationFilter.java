package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthorisationFilter", urlPatterns = "/app/*")
public class AuthorisationFilter implements Filter {
    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();
        Admin currentAdmin = adminDao.read((Integer) session.getAttribute("adminId"));

        if (!adminDao.isEnabled(currentAdmin)) {
            response.sendRedirect("/login");
            System.out.println("Nie jesteś zalogowany, spadaj");
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {}

}
