package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/super/admin/block")
public class AdminBlockUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        AdminDao adminDao = new AdminDao();
        try {
            int id = Integer.parseInt(userId);
            Admin admin = adminDao.read(id);
            if(adminDao.isEnabled(admin)) {
                adminDao.setAdminEnable(0, admin.getId());
            } else {
                adminDao.setAdminEnable(1,admin.getId());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/super/admin/user");
    }
}
