package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipes/details")
public class RecipeDetailsHome extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        String recipeId = request.getParameter("recipeId");
        response.setContentType("text/html;charset=UTF-8");
        try{
            int id = Integer.parseInt(recipeId);
            Recipe recipe = recipeDao.read(id);
            request.setAttribute("recipe", recipe);

        } catch (NumberFormatException e ) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/recipeDetailsHome.jsp").forward(request,response);

    }
}
