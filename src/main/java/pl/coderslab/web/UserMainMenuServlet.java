package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/mainMenu")
public class UserMainMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("adminId");

        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("numberOfRecipe", recipeDao.adminsRecipe(adminId).size());

        PlanDao planDao = new PlanDao();
        request.setAttribute("numberOfPlans", planDao.findAll(adminId).size());

        List<Plan> all = planDao.findAll(adminId);
        for(int i = 0; i < all.size(); i++) {
           if(i == all.size()-1) {
                String name = all.get(i).getName();
                request.setAttribute("lastPlanName", name);
            }
        }


       List<List<String>> lastPlan = planDao.adminLastPlan(adminId);
        request.setAttribute("lastPlanList", lastPlan);

        System.out.println(lastPlan);

        getServletContext().getRequestDispatcher("/dashboard.jsp")
                .forward(request, response);
    }
}
