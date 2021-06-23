package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class PlanDao {


private static final String CREATE_PLAN_QUERY= "INSERT INTO plan (name, description, created, admin_id ) VALUES (?, ?, NOW(), ?);";
private static final String DELETE_PLAN_QUERY= "DELETE FROM plan where id = ?;";
private static final String READ_PLAN_QUERY = "SELECT ID, NAME, DESCRIPTION, CREATED, ADMIN_ID FROM plan WHERE id = ?;"; //dodałam admin_id AO.
private static final String FIND_ALL_PLAN_IN_ADMIN = "SELECT plan.id, name, description FROM plan INNER JOIN admins a on plan.admin_id = a.id WHERE admin_id = ?;";
private static final String FIND_ALL_PLAN_QUERY = "SELECT * FROM plan";
private static final String UPDATE_PLAN_QUERY= "UPDATE plan SET NAME = ?, description = ?, created = NOW() WHERE id = ? AND admin_id = ?";
private static final String CREATE_RECIPE_IN_PLAN_QUERY = "INSERT INTO recipe_plan(RECIPE_ID, MEAL_NAME, DISPLAY_ORDER, DAY_NAME_ID, PLAN_ID) VALUES (?,?,?,?,?);";
private static final String FIND_ADMIN_PLAN = "SELECT * FROM plan where admin_id = ?;";
private static final String ADMIN_LAST_PLAN = "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.ingredients as ingredients\n" +
            ", recipe_id FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "        recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) -- zamiast 1 należy wstawić id użytkownika (tabela admins) --\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    //////* AdminLastPlan
    private static final String READ_RECIPE_IN_PLAN_QUERY = "SELECT recipe_plan.id,recipe_id, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE plan_id = ? -- zamiast 6 należy wstawić id planu do pobrania --\n" +
            "ORDER by day_name.display_order;";

    private static final String DELETE_FROM_RECIPE_PLAN = "DELETE FROM recipe_plan WHERE id = ?";
    private static final String DELETE_ALL_RECIPE_IN_PLAN = "DELETE FROM recipe_plan WHERE plan_id = ?";

    public void deleteAllRecipeFromPlan(int planId) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement preStmt = conn.prepareStatement(DELETE_ALL_RECIPE_IN_PLAN);
            preStmt.setInt(1, planId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecipeFromPlan(int recipeId) {
        try (Connection conn = DbUtil.getConnection()){
             PreparedStatement preStmt = conn.prepareStatement(DELETE_FROM_RECIPE_PLAN);
            preStmt.setInt(1, recipeId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> readRecipeInPlan(int planId) {
        Map<String, List<String>> mapRecipePlan = new HashMap<>();
        try(Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(READ_RECIPE_IN_PLAN_QUERY);
            preStmt.setInt(1,planId);
            ResultSet rs = preStmt.executeQuery();
            while(rs.next()) {
                List<String> initArray = new ArrayList<>();
                int recipePlanId = rs.getInt("recipe_plan.id");
                int recipeId = (rs.getInt("recipe_id"));
                String dayName = rs.getString("day_name");
                String mealName = rs.getString("meal_name");
                String recipeName = rs.getString("recipe_name");
                String recipeDescription = rs.getString("recipe_description");

                Collections.addAll(initArray,mealName,recipeName,recipeDescription, Integer.toString(recipePlanId),Integer.toString(recipeId));
                    mapRecipePlan.put(dayName, initArray);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapRecipePlan;
    }

    public void createRecipeInPlan(int recipeId, String mealName, int numberOfMeal, int dayId, int planId ) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(CREATE_RECIPE_IN_PLAN_QUERY)) {
            preStmt.setInt(1, recipeId);
            preStmt.setString(2, mealName);
            preStmt.setInt(3, numberOfMeal);
            preStmt.setInt(4, dayId);
            preStmt.setInt(5, planId);
            preStmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
  public List<List<String>> adminLastPlan(int adminID) {
        List<List<String>> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(ADMIN_LAST_PLAN)) {
            preStmt.setInt(1, adminID);
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                List<String> initList = new ArrayList<>();
                String dayName = resultSet.getString("day_name");
                String recipeName = resultSet.getString("recipe_name");
                String mealName = resultSet.getString("meal_name");
                String ingredients = resultSet.getString("ingredients");
                int recipeId = resultSet.getInt("recipe_id");
                Collections.addAll(initList, dayName, mealName,recipeName,Integer.toString(recipeId),  ingredients);
                list.add(initList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Plan> findAll(int adminId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_PLAN_IN_ADMIN)) {
            List<Plan> planList = new ArrayList<>();
            preStmt.setInt(1, adminId);
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                planList.add(plan);
            }
            return planList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    


    public Plan read(int planId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(READ_PLAN_QUERY)) {
            preStmt.setInt(1, planId);
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                plan.setCreated(resultSet.getDate("created"));
                plan.setAdminID(resultSet.getInt("admin_id")); //dodałam Ania O
                return plan;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Plan createPlan(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preStmt = connection.prepareStatement(CREATE_PLAN_QUERY)) {
            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setInt(3, plan.getAdminID());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int planId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(DELETE_PLAN_QUERY)) {
            preStmt.setInt(1, planId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Plan plan) {
        try (Connection conn = DbUtil.getConnection()){
             PreparedStatement statement = conn.prepareStatement(UPDATE_PLAN_QUERY);
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setInt(3, plan.getId());
            statement.setInt(4,plan.getAdminID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()){
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAN_QUERY);
             ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                plan.setCreated(resultSet.getDate("created"));
                plan.setAdminID(resultSet.getInt("admin_id"));
                planList.add(plan);
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }
}

