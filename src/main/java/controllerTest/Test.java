package controllerTest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import common.utils.HttpClientUtil;
import controllerTest.service.TestService;
import springFramework.typeConversion.Child;
import springFramework.typeConversion.IntegerToString;
import springFramework.typeConversion.PeopleToAnimal;
import springFramework.validation.Person;
import springFramework.validation.PersonValidator;

/**
 * @ClassName: Test
 * @Description: TODO(杩欓噷鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜绫荤殑浣滅敤)
 * @author zouyang
 * @date 2016骞�鏈�8鏃�涓嬪崍4:06:32
 * 
 */

@Controller
@RequestMapping("/Test")
public class Test {
	
	@Autowired
	private TestService testService;
	@Autowired
	private IntegerToString integerToString;
	@Autowired
	private PeopleToAnimal peopleToAnimal;

	@InitBinder
	private void initBinder(WebDataBinder dataBinder){
		dataBinder.setValidator(new PersonValidator());
	}
	
	@RequestMapping(value="/validationTest",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String validationTest(@Validated Person person,BindingResult brs){
		if(brs.hasErrors()){
			return brs.toString();
		}else{
			return "校验通过";
		}
	}
	
	@RequestMapping(value="/scrollTest")
	public String scrollTest(){
		return "scrollTest/scroll";
	}
	
	@RequestMapping(value="/test",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String test(HttpServletRequest request,HttpServletResponse response){
		//aop测试
		testService.test();
		//类型转换测试   -01
		int i = 5;
		System.out.println(integerToString.convert(i));
		//类型转换测试   -02
		springFramework.typeConversion.People p = new springFramework.typeConversion.People();
		p.setAge("17");
		p.setName("Tom");
		Child child = peopleToAnimal.getConverter(Child.class).convert(p);
		System.out.println(child.toString());
		return JSON.toJSONString("测试效果");
	}
	
	@RequestMapping(value="/getUserInfo",produces="text/html;charset=UTF-8")
	public String getUserInfo(HttpServletRequest request){
//		request.getSession().setAttribute("userName", "zouyang");
//		request.setAttribute("password", "12345");
		return "index";
	}
	
	@RequestMapping(value="/getUserInfoNew",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String getUserInfoNew(HttpServletRequest request){
		request.getSession().setAttribute("userName", "TEST");
//		request.setAttribute("password", "123");
		return JSON.toJSONString("success");
	}
	@RequestMapping(value="/testUserInfo")
	public @ResponseBody String testUserInfo(HttpServletRequest request){
		String user = request.getSession().getAttribute("userName").toString();
		return JSON.toJSONString("success");
	}
	@RequestMapping(value="/{varName}/getPathVariable/{varValue}/{methodName}")
	public @ResponseBody String getPathVariable(@PathVariable String varName,@PathVariable String varValue,@PathVariable String methodName){
		System.out.println("varName:"+varName+"\nvarValue:"+varValue+"\nmethodName:"+methodName);	
		return JSON.toJSONString("success");
	}
	
	@RequestMapping(value="/testDuanxin",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String testDuanxin(){
		String[] numbers = {"18113152327"};
		String msg = "今晚上一起吃饭啊，老地方";
		StringBuffer jsonStr = new StringBuffer("[");
		for(int i = 0;i<numbers.length;i++){
			String str = "{\"msg\":\""+msg+"\",\"phone\":\""+numbers[i]+"\"}";
			if(i < numbers.length-1){
				jsonStr.append(str+",");
			}else{
				jsonStr.append(str+"]");
			}
		}
		System.out.println(jsonStr);
		Map<String,String> m = new HashMap<String,String>();
		m.put("userMsgList", jsonStr.toString());
//		m.put("inforList", "123456");
		String result = HttpClientUtil.httpPost("http://192.168.6.181:8080/DuanXinService/sendMsg/sendMsgToGroup", m);
		System.out.println(jsonStr.toString());
		return result;
	}

}
