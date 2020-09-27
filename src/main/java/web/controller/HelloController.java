package web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.models.Car;
import web.models.User;
import web.service.ServiseCar;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
	private final UserService userServiceEntityImpl;

	public HelloController(UserService userServiceEntityImpl) {
		this.userServiceEntityImpl = userServiceEntityImpl;
	}

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("войти");
		model.addAttribute("messages", messages);
		return "index_users";
	}

	@GetMapping(value = "cars")
	public String printCars(@RequestParam(value = "locale", required = false) String par, HttpServletRequest request, ModelMap model) {
		ServiseCar serviseCar = new ServiseCar();
		List<Car> carsList;
		carsList = serviseCar.getListOfCars();
		model.addAttribute("carsList", carsList);
		String title;
		String carModel;
		String color;
		String year;

		title = "МАШИНЫ";
		carModel = "модель";
		color = "цвет";
		year = "год";

		if (par != null && par.equals("en")) {
			title = "CARS";
			carModel = "model";
			color = "color";
			year = "year";
		}
		model.addAttribute("title", title);
		model.addAttribute("carModel", carModel);
		model.addAttribute("color", color);
		model.addAttribute("year", year);
		return "cars";
	}

	@GetMapping("/user/{id}")
	public String homePage(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userServiceEntityImpl.getUserById(id));
		return "user";
	}

	@RequestMapping(value= "admin", method=RequestMethod.GET)
	public String homeAdmin(Model model) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("user", user);
		return "admin";
	}

	@GetMapping(value = "admin/users")
	public String indexUsers(Model model){
		//we will have a key "users" that give us an array of users

		model.addAttribute("users", userServiceEntityImpl.listUsers());
		// getting all users
		return "index_users";
	}
	@GetMapping("admin/users/remove/{id}")
	public String removeUser(@PathVariable int id) {
		userServiceEntityImpl.removeUserById(id);
		return "redirect:/admin/users";
	}
	//	additional methods
	@PostMapping("admin/users/edituser")
	public String updateUser(@ModelAttribute User user) {
		userServiceEntityImpl.updateUser(user);
		return "redirect:/admin/users";
	}
	@GetMapping("admin/users/edituser/{id}")
	public ModelAndView updateUser(@PathVariable int id){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edituser");
		modelAndView.addObject("user", userServiceEntityImpl.getUserById(id));
		return modelAndView;
	}
	//	@GetMapping
//	public String newPerson(@ModelAttribute("user") User user){
//		return "new";
//	}
//	@PostMapping("admin/users/new")
//	public String create(@ModelAttribute("user") User user){
//		userServiceEntityImpl.addUser(user);
//		return "redirect:/cars";
//	}
	@PostMapping("admin/users/new")
	public String create(@ModelAttribute User user){
		userServiceEntityImpl.addUser(user);
		return "redirect:/admin/users";
	}

	@GetMapping("admin/users/new")
	public ModelAndView newPerson(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("new");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}



//	@GetMapping(value = "user")
//	public String welcomePage(ModelMap model) {
//		List<String> messages = new ArrayList<>();
//		messages.add("Hello!");
//		messages.add("Ссылка для перехода на страницу юзеров ниже");
//		model.addAttribute("messages", messages);
//		return "user";

}
