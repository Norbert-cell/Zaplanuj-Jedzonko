package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/delete")
public class DeletePlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planId = request.getParameter("planId");
        PlanDao planDao = new PlanDao();
        try{
            int id = Integer.parseInt(planId);
            planDao.deleteAllRecipeFromPlan(id);
            planDao.delete(id);
            getServletContext().getRequestDispatcher("/app/plan/list")
                    .forward(request,response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
