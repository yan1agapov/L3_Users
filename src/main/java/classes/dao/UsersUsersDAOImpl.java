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
public class UsersUsersDAOImpl implements UsersDAO {

    private static Connection connection;

    static {
        String url = null;
        String username = null;
        String password = null;

        try(InputStream in = UsersUsersDAOImpl.class.getClassLoader()
                .getResourceAsStream("persistence.properties")){
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
            statement = connection.prepareStatement("select * from users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString(1));
                user.setSecondName(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
}
