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
@RequestMapping("/users")
public class UserController {
    private UserService userServiceEntityImpl;

//    public UserController(UserService userServiceEntityImpl) {
//        this.userServiceEntityImpl = userServiceEntityImpl;
//    }

    @GetMapping
    public String index(Model model){
        //we will have a key "users" that give us an array of users
        model.addAttribute("users", userServiceEntityImpl.listUsers());
        // getting all users
        return "index_users";
    }
    @GetMapping("/{id}")
    //with {id} we'll get an id from url and will have access to this variable inside this method
    public String show(@PathVariable("id") int id, Model model){
        //getting one user from id dao and give this to the view
        model.addAttribute("user", userServiceEntityImpl.getUserById(id));
        return "show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user){
        return "new";
    }
    @PostMapping
    public String create(@ModelAttribute("user") User user){
        userServiceEntityImpl.addUser(user);
        return "redirect:/users";
    }
    //additional methods
    @PostMapping("/edituser")
    public String updateUser(@ModelAttribute User user) {
        userServiceEntityImpl.updateUser(user);
        return "redirect:/users";
    }
    @GetMapping("/edituser/{id}")
    public ModelAndView updateUser(@PathVariable int id){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edituser");
        modelAndView.addObject("user", userServiceEntityImpl.getUserById(id));
        return modelAndView;
    }
    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable int id) {
        userServiceEntityImpl.removeUserById(id);
        return "redirect:/users";
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
