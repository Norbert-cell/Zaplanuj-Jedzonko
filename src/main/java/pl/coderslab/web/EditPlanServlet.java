package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditPlanServlet" , urlPatterns = "/app/plan/edit")
public class EditPlanServlet extends HttpServlet {
    PlanDao planDao = new PlanDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planId = request.getParameter("planId");
        System.out.println(planId);
        Plan planToUpdate = planDao.read(Integer.parseInt(planId));
        planToUpdate.setName(request.getParameter("planName"));
        planToUpdate.setDescription(request.getParameter("planDescription"));
        planDao.update(planToUpdate);
        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planId = request.getParameter("planId");
        Plan plan = planDao.read(Integer.parseInt(planId));
        request.setAttribute("plan", plan);
        getServletContext().getRequestDispatcher("/editPlan.jsp").forward(request,response);
    }
}
