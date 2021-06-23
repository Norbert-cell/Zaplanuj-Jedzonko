package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDao adminDao = new AdminDao();
        List<Admin> adminList = adminDao.findAll();
        String name = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        boolean emailIsUnique= true;
        Admin myNewAdmin= new Admin();
        myNewAdmin.setFirstName(name);
        myNewAdmin.setLastName(lastName);
        myNewAdmin.setEmail(email);
        myNewAdmin.setPassword(password);
        for (Admin admin : adminList) {
            if(admin.getEmail().equals(email)){
                emailIsUnique = false;
                break;
            }
        }
        if(emailIsUnique){
            adminDao.create(myNewAdmin);
        response.sendRedirect(request.getContextPath() + "/login");
        } else {
            session.setAttribute("mailInUse","Ten adres już istnieje w systemie");
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("mailInUse");
        if(!(null == message)){
            request.setAttribute("mailInUse", message);
            session.removeAttribute("mailInUse");
        }
        getServletContext().getRequestDispatcher("/register.jsp")
                .forward(request, response);
    }
}
