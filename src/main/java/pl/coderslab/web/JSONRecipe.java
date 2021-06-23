package pl.coderslab.web;

import com.google.gson.Gson;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/jsonRecipe")
public class JSONRecipe extends HttpServlet {
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipeList =recipeDao.findAll();
        
        recipeList.sort(Comparator.comparing(recipe -> recipe.getUpdated()));
        Collections.reverse(recipeList);


        String recipeJsonString = this.gson.toJson(recipeList);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setContentType("text/html; charset=UTF-8");
        out.print(recipeJsonString);
        out.flush();
    }
}
