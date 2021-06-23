package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/app/plan/details")
public class PlanDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        try {
            int planId = Integer.parseInt(request.getParameter("planId"));
            session.setAttribute("planId", planId);
            PlanDao planDao = new PlanDao();
            request.setAttribute("plan", planDao.read(planId));


            Map<String, List<String>> finalMap = planDao.readRecipeInPlan(planId);
            System.out.println(finalMap);

            System.out.println(finalMap);
            boolean exist = false;
            if(!finalMap.isEmpty()) {
                exist = true;
            }

            request.setAttribute("finalMap", finalMap);
            request.setAttribute("exist", exist);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/detailsPlan.jsp")
                .forward(request,response);

    }
}
