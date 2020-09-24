package web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.models.Car;
import web.models.User;
import web.service.ServiseCar;
import web.service.UserService;
import web.service.UserServiceEntityImpl;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
	UserServiceEntityImpl userServiceEntity;

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("войти");
		model.addAttribute("messages", messages);
		return "redirect:/user";
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

	@RequestMapping(value= "user", method=RequestMethod.GET)
	public String homeuser(Model model) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping(value= "admin", method=RequestMethod.GET)
	public String homeAdmin(Model model) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("user", user);
		return "admin";
	}
//	@GetMapping(value = "user")
//	public String welcomePage(ModelMap model) {
//		List<String> messages = new ArrayList<>();
//		messages.add("Hello!");
//		messages.add("Ссылка для перехода на страницу юзеров ниже");
//		model.addAttribute("messages", messages);
//		return "user";

}
