package in.education.student.common;

import in.education.student.common.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class HomeController {

	/*@RequestMapping("/")
	public String home( Map<String, Object> model) {

		return "index";
	}*/

	@RequestMapping(value="/")
	public ModelAndView start() {
		return new ModelAndView("index", "user",new User());
	}

	@RequestMapping(method= RequestMethod.GET, value="/test")
	public ModelAndView next() {

		return new ModelAndView("test");
	}

	@RequestMapping(value="/home")
	public ModelAndView home() {

		return new ModelAndView("home");
	}


}
