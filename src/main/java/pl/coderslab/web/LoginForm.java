package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();
        boolean found = false;


        List<Admin> adminList = adminDao.findAll();
        try {
            for (Admin admin : adminList) {
                if (admin.getEmail().equals(email) && BCrypt.checkpw(password, admin.getPassword())) {
                    if(admin.getEnable() == 0) {
                        session.setAttribute("notEnable", true);
                        break;
                    }
                    boolean adminUser = false;
                    found = true;
                    int superadmin = admin.getSuperadmin();
                    if(superadmin == 1) {
                        adminUser = true;
                    }
                    int id = admin.getId();
                    session.setAttribute("adminId", id);
                    String firstName = admin.getFirstName();
                    session.setAttribute("firstName", firstName);

                    session.setAttribute("adminUser", adminUser);
                    response.sendRedirect("/app/mainMenu");

                }
            }
            if (!found) {
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }catch (IllegalArgumentException e ) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
    }
}
