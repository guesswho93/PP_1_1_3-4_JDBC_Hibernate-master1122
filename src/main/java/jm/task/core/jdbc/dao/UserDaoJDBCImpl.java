package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS users (id int not null auto_increment PRIMARY KEY," +
                    "name VARCHAR (30) not null, lastName VARCHAR (30) not null," +
                    "age INT NOT NULL )");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()) {
            stat.executeUpdate("DROP TABLE IF EXISTS  users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (? , ?, ?)";
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User с именем " + name + " добавлен в таблицу");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()) {
            String sql = "SELECT * FROM users";
            ResultSet resultSet = stat.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");

                users.add(new User( name, lastName, age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(users);
        return users;
    }

    public void removeUserById(long id) {
        String sql ="DELETE FROM users WHERE id = ?";
        try (Connection conn = Util.getConnection();
             PreparedStatement prepStat = conn.prepareStatement(sql)) {
            prepStat.setLong(1, id);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement()) {
            stat.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
