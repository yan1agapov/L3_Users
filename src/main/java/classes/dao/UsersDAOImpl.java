package classes.dao;

import classes.model.User;
import exceptions.IncorrectEmailException;
import validators.EmailValidator;
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
    private static String tableName;

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
            tableName = properties.getProperty("table_name");

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

    private void createTableIfNotExist(){
        PreparedStatement statement = null;
        try {
            String creation = "CREATE TABLE IF NOT EXISTS " + tableName +"(\n" +
                    "    id serial primary key ,\n" +
                    "    firstName VARCHAR (50) NOT NULL,\n" +
                    "    lastName VARCHAR (50) NOT NULL ,\n" +
                    "    age int NOT NULL,\n" +
                    "    email VARCHAR (50) NOT NULL);";
            statement = connection.prepareStatement(creation);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        createTableIfNotExist();
        try {
            statement = connection.prepareStatement("select * from " + tableName + ";");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setID(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setSecondName(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                user.setEmail(resultSet.getString(5));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public void saveUser(User user) {
        try {
            if(!EmailValidator.isEmail(user.getEmail())){
                throw new IncorrectEmailException("Incorrect email was entered");
            }
            createTableIfNotExist();
            if (user.getID() > 0) {
                String updating = "UPDATE " + tableName + " SET firstname = ?, lastname = ?, age = ?, email = ?\n" +
                        "  WHERE id=?;";
                PreparedStatement statement = connection.prepareStatement(updating);
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getSecondName());
                statement.setInt(3, user.getAge());
                statement.setString(4, user.getEmail());
                statement.setInt(5, user.getID());
                statement.execute();
            } else {
                String adding = "insert into " + tableName + " (firstName, lastName, age, email)\n" +
                        "VALUES (?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(adding);
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getSecondName());
                statement.setInt(3, user.getAge());
                statement.setString(4, user.getEmail());
                statement.execute();
            }
        } catch (IncorrectEmailException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        createTableIfNotExist();
        String deleting = "delete from " + tableName +"\n" +
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