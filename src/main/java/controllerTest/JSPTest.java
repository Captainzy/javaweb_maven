package controllerTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class JSPTest {
	
	@RequestMapping(value="/basicTest")
	public String jspTest(){
		return "jsp/basicTest";
	}
}
