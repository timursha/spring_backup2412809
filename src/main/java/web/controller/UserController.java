package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.dao.UserDaoEntityImpl;
import web.models.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class UserController {
    private UserService userServiceEntityImpl;

//    public UserController(UserService userServiceEntityImpl) {
//        this.userServiceEntityImpl = userServiceEntityImpl;
//    }

//    @GetMapping(value = "/users")
//    public String index(Model model){
//        //we will have a key "users" that give us an array of users
//        model.addAttribute("users", userServiceEntityImpl.listUsers());
//        // getting all users
//        return "index_users";
//    }
    @GetMapping("/{id}")
    //with {id} we'll get an id from url and will have access to this variable inside this method
    public String show(@PathVariable("id") int id, Model model){
        //getting one user from id dao and give this to the view
        model.addAttribute("user", userServiceEntityImpl.getUserById(id));
        return "show";
    }




//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String printWelcome(ModelMap model) {
//        List<String> messages = new ArrayList<>();
//        messages.add("Hello!");
//        messages.add("I'm Spring MVC-SECURITY application");
//        messages.add("5.2.0 version by sep'19 ");
//        model.addAttribute("messages", messages);
//        return "admin";
//    }




    @RequestMapping("/admin")
    public String admPage() {
        return "admin";
    }
}
