package bob.spring.demo.thymeleaf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	private List<Hello> data = new LinkedList<>(Arrays.asList(
			new Hello("hello", "EN"), 
			new Hello("Willkommen", "DE")));

	@RequestMapping(method = RequestMethod.GET)
	public String getListe(Model model) {
		model.addAttribute("helloList", data);
		// Rückgabe = Dateiname (ohne Endung)
		return "hello";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addToListe(Hello hello) {
		data.add(hello);
		return "redirect:/hello";
	}

}
