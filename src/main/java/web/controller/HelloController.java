package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.Car;
import web.service.ServiseCar;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("Ссылка для перехода на страницу юзеров ниже");
		model.addAttribute("messages", messages);
		return "index";
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

		if (par != null && par.equals("en")){
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
}