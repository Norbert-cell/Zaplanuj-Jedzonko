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

@WebServlet("/app/edit/password")
public class EditPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("adminId");
        boolean successPass = false;
        boolean wrongPass = false;
        if(!pass1.isEmpty() && !pass2.isEmpty() && pass1.equals(pass2)) {
            Admin admin = new Admin();
            admin.setPassword(pass1);
            admin.setId(adminId);
            AdminDao adminDao = new AdminDao();
            adminDao.updatePassword(admin);
            successPass = true;
        } else {
            wrongPass = true;
        }
        request.setAttribute("wrongPass", wrongPass);
        request.setAttribute("pass", successPass);
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String firstName = (String) session.getAttribute("firstName");
        request.setAttribute("firstName", firstName);
        request.setAttribute("succesPass",request.getAttribute("pass"));
        request.setAttribute("wrongPass",request.getAttribute("wrongPass"));
        getServletContext().getRequestDispatcher("/editPassword.jsp")
                .forward(request, response);
    }
}
