package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe (id, name,ingredients,description,created,updated, preparation_time, preparation, admin_id) VALUES (null, ?,?,?,NOW(),NOW(),?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT id, name, ingredients, description, created, updated, preparation_time, preparation FROM recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, updated = NOW(), preparation_time = ?, preparation = ? WHERE	id = ?;";
    private static final String FIND_ADMIN_RECIPE ="SELECT * FROM recipe where admin_id = ?";
    private static final String FIND_PLAN_WHERE_RECIPE_IS = "SELECT plan_id FROM recipe_plan WHERE recipe_id = ?;";

    // ADMIN PLAN
    public List<Recipe> adminsRecipe(int adminID) {
        List<Recipe> adminRecipes = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(FIND_ADMIN_RECIPE)) {
            preStmt.setInt(1, adminID);
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                Recipe addedRecipe = new Recipe();
                addedRecipe.setId(resultSet.getInt("id"));
                addedRecipe.setName(resultSet.getString("name"));
                addedRecipe.setDescription(resultSet.getString("description"));
                addedRecipe.setPreparationTime(resultSet.getInt("preparation_time"));
                addedRecipe.setPreparation(resultSet.getString("preparation"));
                adminRecipes.add(addedRecipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminRecipes;
    }

/// dodał Artur


    public Recipe read(int recipeID) {
        Recipe recipe = new Recipe();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement state = conn.prepareStatement(READ_RECIPE_QUERY);
            state.setInt(1, recipeID);
            try (ResultSet resultSet = state.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getDate("created"));
                 //   recipe.setUpdated(resultSet.getDate("update"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }



    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getDate("created"));
                recipeToAdd.setUpdated(resultSet.getDate("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeList.add(recipeToAdd);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }


    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setInt(4, recipe.getPreparationTime());
            insertStm.setString(5, recipe.getPreparation());
            insertStm.setInt(6,recipe.getAdminId());
            insertStm.executeUpdate();
            return recipe;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete(int recipeID) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void update (Recipe recipe){
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
                statement.setInt(6, recipe.getId());
                statement.setString(1, recipe.getName());
                statement.setString(2, recipe.getIngredients());
                statement.setString(3, recipe.getDescription());
                statement.setInt(4, recipe.getPreparationTime());
                statement.setString(5, recipe.getPreparation());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public boolean isRecipeInAnyPlan(int recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PLAN_WHERE_RECIPE_IS)) {
            statement.setInt(1, recipeId);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            List<Integer> plans= new ArrayList<>();
            while(resultSet.next()) {
                 plans.add(resultSet.getInt("plan_id"));
                 return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
