package classes.dao;

import classes.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;


public class UsersDAOImplTest {
        UsersDAOImpl dao = new UsersDAOImpl();
        User testUser = new User(666, "John", "Doe", 66, "johndoe@gmail.com");
        String testTableName = "usersTest";

        @Test
        public void getAllUsersTest(){
                ReflectionTestUtils.setField(dao, "tableName", testTableName);

                assertNotEquals(dao.getAll(), null);
        }

        @Test
        public void saveUserTest(){
                ReflectionTestUtils.setField(dao, "tableName", testTableName);
                dao.saveUser(testUser);

                assertNotEquals(dao.getAll(), null);
        }

        @Test
        public void deleteUserTest(){
                ReflectionTestUtils.setField(dao, "tableName", testTableName);
                List<User> test1 = new ArrayList<>();
                test1 = dao.getAll();
                dao.saveUser(testUser);
                dao.deleteUser(666);

                assertEquals(test1, dao.getAll());
        }
}
