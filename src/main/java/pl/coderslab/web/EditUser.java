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

@WebServlet("/app/user/edit")
public class EditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("adminId");
        String inputName = request.getParameter("inputName");
        String inputSurname = request.getParameter("inputSurname");
        String emailInput = request.getParameter("inputEmail");

        if(inputName != "" && inputSurname != "" && emailInput != ""){
            Admin admin = new Admin();
            admin.setFirstName(inputName);
            admin.setLastName(inputSurname);
            admin.setEmail(emailInput);
            admin.setId(adminId);

            AdminDao adminDao = new AdminDao();
            adminDao.update(admin);
            session.setAttribute("firstName", inputName);

        }

        response.sendRedirect("/mainMenu");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("adminId");
        AdminDao adminDao = new AdminDao();
        request.setAttribute("admin",adminDao.read(adminId));
        getServletContext().getRequestDispatcher("/editUser.jsp")
                .forward(request,response);
    }
}
