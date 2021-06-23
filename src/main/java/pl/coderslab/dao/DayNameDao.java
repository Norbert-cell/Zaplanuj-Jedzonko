package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {

    private static final String CREATE_DAY_NAME_QUERY = "INSERT INTO day_name(name, display_order) VALUES (?, ?);";
  //  private static final String READ_DAY_NAME_QUERY = "SELECT * FROM day_name WHERE id = ?;";
  // private static final String UPDATE_DAY_NAME_QUERY = "UPDATE day_name SET id=?, name=?, display_order=?, WHERE id =?;";
    private static final String FIND_ALL_DAY_NAME_QUERY = "SELECT * FROM day_name;";

    public DayName create(DayName dayName) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_DAY_NAME_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, dayName.getName());
            statement.setInt(2, dayName.getDisplayOrder());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                dayName.setId(resultSet.getInt(1));
            }
            return dayName;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception w create");
            return null;
        }
    }

    public List<DayName> findAll() {
        List<DayName> dayNames = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_DAY_NAME_QUERY);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                DayName dayName = new DayName();
                dayName.setId(result.getInt("id"));
                dayName.setName(result.getString("name"));
                dayName.setDisplayOrder(result.getInt("display_order"));
                dayNames.add(dayName);

            }
            return dayNames;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception w findAll");
            return null;
        }
    }
}
