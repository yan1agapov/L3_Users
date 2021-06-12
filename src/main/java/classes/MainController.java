package classes;

import classes.dao.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    private UsersDAO usersDAO;

    @RequestMapping("/")
    public String getUsers(Model model) throws SQLException{
        model.addAttribute("users", usersDAO.getAll());
        return "users.jsp";
    }

}
