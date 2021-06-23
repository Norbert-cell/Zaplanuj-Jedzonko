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

@WebServlet("/app/recipe/plan/delete")
public class DeleteRecipeInPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int planId = (int) session.getAttribute("planId");
        String recipeId = request.getParameter("recipeId");
        PlanDao planDao = new PlanDao();
        try{
            int id = Integer.parseInt(recipeId);
            planDao.deleteRecipeFromPlan(id);
        } catch (NumberFormatException e ) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/app/plan/details?planId=" + planId)
                .forward(request,response);
    }
}
