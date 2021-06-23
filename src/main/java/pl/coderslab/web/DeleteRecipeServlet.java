package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeServlet", urlPatterns = "/app/recipes/deleteRecipe")
public class DeleteRecipeServlet extends HttpServlet {
    RecipeDao recipeDao = new RecipeDao();
    String recipeId = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Recipe recipe = new Recipe();
        try {
            recipeId = request.getParameter("recipeId");
            recipe.setId(Integer.parseInt(recipeId));
        } catch (NumberFormatException e) {
            log("Niepoprawne id");
        }

        if (!recipeDao.isRecipeInAnyPlan(recipe.getId())) {
            recipeDao.delete(recipe.getId());
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("recipeInUse", "Ten przepis istnieje w którymś planie. Nie mogę usunąć");
        }
        response.sendRedirect(request.getContextPath() + "/app/recipe/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        recipeId = request.getParameter("recipeId");
        doPost(request, response);
    }
}
