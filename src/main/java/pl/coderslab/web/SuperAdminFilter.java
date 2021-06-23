package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SuperAdminFilter", urlPatterns ="/super/*")
public class SuperAdminFilter implements Filter {
    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();
        Admin currentAdmin = adminDao.read((Integer) session.getAttribute("adminId"));
        if (adminDao.isSuperAdmin(currentAdmin)) {
            chain.doFilter(req, resp);
        } else {
            response.getWriter().append("Not an admin");
            }
    }

    public void init(FilterConfig config) throws ServletException {}

}
