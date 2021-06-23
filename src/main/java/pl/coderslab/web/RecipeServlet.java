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
import java.util.List;


@WebServlet("/app/recipe/list")
public class RecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("adminId");
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipeList = recipeDao.adminsRecipe(adminId);
        request.setAttribute("recipeList", recipeList);
        request.setAttribute("recipeInUse",session.getAttribute("recipeInUse"));
        session.removeAttribute("recipeInUse");
        getServletContext().getRequestDispatcher("/recipeList.jsp").forward(request,response);

    }
}
