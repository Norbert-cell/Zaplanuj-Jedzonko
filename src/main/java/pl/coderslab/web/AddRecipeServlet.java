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

@WebServlet("/app/recipe/add")
public class AddRecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeName = request.getParameter("recipeName");
        String recipeDescription = request.getParameter("recipeDescription");
        String recipePreparationTime = request.getParameter("recipePreparationTime"); //pytanie do mądrych głów czy wolimy to wrzucać jako string skoro
        //skoro nic później z tym nie robimy czy przerobić to na inta?
        String recipePreparation = request.getParameter("recipePreparation");
        String recipeIngredients = request.getParameter("recipeIngredients");
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("adminId");
        boolean create = false;
        try{
            int prepTime = Integer.parseInt(recipePreparationTime);
            if(!recipeName.isEmpty() && !recipeDescription.isEmpty() && !recipePreparation.isEmpty() && !recipeIngredients.isEmpty()) {
                Recipe recipe = new Recipe();
                recipe.setName(recipeName);
                recipe.setDescription(recipeDescription);
                recipe.setPreparation(recipePreparation);
                recipe.setIngredients(recipeIngredients);
                recipe.setPreparationTime(prepTime);
                recipe.setAdminId(adminId);
                RecipeDao recipeDao = new RecipeDao();
                recipeDao.create(recipe);
                create = true;
                response.sendRedirect(request.getContextPath() + "/app/recipe/list");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/app/recipe/add");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String recipeName = (String) session.getAttribute("recipeName");
        request.setAttribute("recipeName", recipeName);
        getServletContext().getRequestDispatcher("/addRecipeForm.jsp")
                .forward(request,response);
    }
}
/*
name
description
preparationTime
preparation
ingredients
*/
