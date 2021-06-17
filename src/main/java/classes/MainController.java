package classes;

import classes.dao.UsersDAO;
import classes.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    private UsersDAO usersDAO;

    @RequestMapping("/")
    public String getUsers(Model model){
        model.addAttribute("users", usersDAO.getAll());
        return "users";
    }

    @RequestMapping("/add_new_user")
    public String addNewUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "add_new_user";
    }

    @RequestMapping("/save_user")
    public String saveUser(@ModelAttribute("user")User user){
        usersDAO.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping("/update_user")
    public String updateUser(@RequestParam("ID") int id, @RequestParam("firstName") String firstName,
                             @RequestParam("secondName") String secondName, @RequestParam("age") int age,
                             @RequestParam("email") String email, Model model){
        User user = new User(id, firstName, secondName, age, email);
        model.addAttribute("user", user);
        return "add_new_user";
    }

    @RequestMapping("/delete_user")
    public String deleteUser(@RequestParam("ID") int id){
        usersDAO.deleteUser(id);
        return "redirect:/";
    }
}
