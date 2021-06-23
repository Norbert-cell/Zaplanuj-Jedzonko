package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/recipe/plan/add")
public class AddRecipeToPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choosePlan = request.getParameter("choosePlan");
        String mealName = request.getParameter("mealName");
        String numberOfMeal = request.getParameter("numberOfMeal");
        String chooseRecipe = request.getParameter("recipe");
        String chooseDayName = request.getParameter("day");
        boolean add = true;
        try{
            int mealNumber = Integer.parseInt(numberOfMeal);
            int planId = Integer.parseInt(choosePlan);
            int recipeId = Integer.parseInt(chooseRecipe);
            int dayId = Integer.parseInt(chooseDayName);
            if(!mealName.isEmpty() && mealNumber>0 ) {
                add = true;
                PlanDao planDao = new PlanDao();
                planDao.createRecipeInPlan(recipeId,mealName,mealNumber,dayId,planId);
                request.setAttribute("add", add);
            }

        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("adminId");

        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("listRecipe", recipeDao.adminsRecipe(adminId));

        PlanDao planDao = new PlanDao();
        request.setAttribute("listPlan", planDao.findAll(adminId));

        DayNameDao dayNameDao = new DayNameDao();
        request.setAttribute("dayName", dayNameDao.findAll());


        getServletContext().getRequestDispatcher("/formAddRecipeToPlan.jsp")
                .forward(request,response);
    }
}
