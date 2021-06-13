package classes.dao;

import classes.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class UsersDAOImpl implements UsersDAO {

    private static Connection connection;

    static {
        String url = null;
        String username = null;
        String password = null;

        try (InputStream in = UsersDAOImpl.class.getClassLoader()
                .getResourceAsStream("persistence.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            String creation = "CREATE TABLE IF NOT EXISTS users (\n" +
                    "                    id serial primary key ,\n" +
                    "                    firstName VARCHAR (50) NOT NULL,\n" +
                    "                    lastName VARCHAR (50) NOT NULL ,\n" +
                    "                    age int NOT NULL\n" +
                    "                    );";
            statement = connection.prepareStatement(creation);
            statement.execute();
            statement = connection.prepareStatement("select * from users;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setID(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setSecondName(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public void saveUser(User user) {
        if (user.getID() > 0) {
            String updating = "UPDATE users SET firstname = ?, lastname = ?, age = ?\n" +
                    "  WHERE id=?;";
            try {
                PreparedStatement statement = connection.prepareStatement(updating);
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getSecondName());
                statement.setInt(3, user.getAge());
                statement.setInt(4, user.getID());
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            String adding = "insert into users(firstName, lastName, age)\n" +
                    "VALUES (?,?,?)";
            try {
                PreparedStatement statement = connection.prepareStatement(adding);
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getSecondName());
                statement.setInt(3, user.getAge());
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUser(int id) {
        String deleting = "delete from users\n" +
                "where id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(deleting);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}