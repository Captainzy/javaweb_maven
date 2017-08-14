package springFramework.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
public class PersonValidator implements Validator {

	@Override
	public boolean supports(Class<?> classType) {
		return Person.class.equals(classType);
	}

	@Override
	public void validate(Object target, Errors errs) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errs, "name", "name is empty", "姓名不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errs, "gender", "gender is empty","性别不能为空");
		Person p = (Person) target;
		if(p.getAge()==0){
			errs.rejectValue("age","age is not right", "年龄不正确");
		}
		if(p.getAge()>18){
			p.setComment("成年人");
		}else{
			p.setComment("未成年人");;
		}
	}


}
