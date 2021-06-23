package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/edit")
public class EditRecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Recipe changedRecipe = new Recipe();
        //      user.setId(Integer.parseInt(request.getParameter("id")));
        changedRecipe.setId(Integer.parseInt(request.getParameter("id")));
        changedRecipe.setName(request.getParameter("recipeName"));
        changedRecipe.setIngredients(request.getParameter("recipeIngredients"));
        changedRecipe.setDescription(request.getParameter("recipeDescription"));
        changedRecipe.setPreparationTime(Integer.parseInt(request.getParameter("recipePreparationTime")));
        changedRecipe.setPreparation(request.getParameter("recipePrepration"));

        RecipeDao recipeDao = new RecipeDao();
        recipeDao.update(changedRecipe);

         response.sendRedirect(request.getContextPath() + "/app/recipe/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("recipeId");

        try{
            RecipeDao recipeDao = new RecipeDao();
            int recipeId = Integer.parseInt(id);
            Recipe recipeToEdit = recipeDao.read(recipeId);
            System.out.println(recipeToEdit.toString());
            request.setAttribute("recipe", recipeToEdit);
        } catch (NumberFormatException e ) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/editRecipe.jsp").forward(request,response);

    }
}
