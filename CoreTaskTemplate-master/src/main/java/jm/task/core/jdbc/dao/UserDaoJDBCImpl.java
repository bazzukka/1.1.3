package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    private static Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    public void createUsersTable() {
        try {
            Statement stm = connection.createStatement();
            stm.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(256) NOT NULL,\n" +
                    "  `lastname` VARCHAR(256) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n");
            stm.close();
        } catch (SQLException e) {
            logger.warning("Table has not created!\n" + e);
        }
    }

    public void dropUsersTable() {
        try {
            Statement stm = connection.createStatement();
            stm.execute("DROP TABLE if EXISTS users");
            stm.close();
        } catch (SQLException e) {
            logger.warning("Table has not dropping!\n" + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String query = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            logger.info("User с именем - " + name + " добавлен в базу данных");
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            logger.warning("Пользователь " + name + " " + lastName + " не добавлен!\n" + e);
        }
    }

    public void removeUserById(long id) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM users WHERE id =" + id);
            stmt.close();
        } catch (SQLException e) {
            logger.warning("User по id " + id + " не удалён!\n" + e);
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> userList = new ArrayList<>();
            Statement stm = connection.createStatement();
            stm.executeQuery("SELECT * FROM users");
            ResultSet rs = stm.getResultSet();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
               // System.out.println(user.toString());
            }
            return userList;
        } catch (SQLException e) {
            logger.warning("getAllUsers has not getting!\n" + e);
            return null;
        }
    }

    public void cleanUsersTable() {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate("DELETE FROM users");
            stm.close();
        } catch (SQLException e) {
            logger.warning("Table has not cleaning!\n" + e);
        }
    }
}