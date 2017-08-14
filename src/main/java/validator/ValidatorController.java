package validator;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/valid")
public class ValidatorController {
	@RequestMapping(value="/testValid",produces=MediaType.TEXT_HTML_VALUE)
	public ModelAndView testValid(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/validTest/testPage");
		mv.addObject("testBean", new TestBean());
		return mv;
	}
	@RequestMapping(value="/validTest")
	public String validTest(@ModelAttribute("testBean") @Valid TestBean testBean,BindingResult br){//BindingResult必须紧随@valid之后,每一个@valid对应一个BindingResult
		if(br.hasErrors()){
			return "/validTest/testPage";
		}
		return "";
	}
}
