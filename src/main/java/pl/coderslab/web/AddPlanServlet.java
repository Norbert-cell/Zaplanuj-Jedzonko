package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/plan/add")
public class AddPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("adminId");

        if(!planName.isEmpty() && !planDescription.isEmpty()) {
            Plan plan = new Plan();
            plan.setName(planName);
            plan.setDescription(planDescription);
            plan.setAdminID(adminId);
            PlanDao planDao = new PlanDao();
            planDao.createPlan(plan);
            response.sendRedirect(request.getContextPath() + "/app/plan/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/app/plan/add");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/formAddPlan.jsp")
                .forward(request,response);
    }
}
