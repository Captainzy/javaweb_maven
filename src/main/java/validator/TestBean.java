package validator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class TestBean {
	/*	
  	Bean Validation 中内置的 constraint     
	@Null   被注释的元素必须为 null     
	@NotNull    被注释的元素必须不为 null     
	@AssertTrue     被注释的元素必须为 true     
	@AssertFalse    被注释的元素必须为 false     
	@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值     
	@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值     
	@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值     
	@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值     
	@Size(max=, min=)   被注释的必须是一个字符串，字符串长度必须在指定的范围内     
	@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内     
	@Past   被注释的元素必须是一个过去的日期     
	@Future     被注释的元素必须是一个将来的日期     
	@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式     
	Hibernate Validator 附加的 constraint     
	@NotBlank(message =)   验证字符串非null，且长度必须大于0     
	@Email  被注释的元素必须是电子邮箱地址     
	@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内     
	@NotEmpty   被注释的字符串的必须非空     
	@Range(min=,max=,message=)  被注释的元素必须在合适的范围内  
	*/
	@Min(value = 0,message="年龄最小为0岁")
	@Max(value = 150,message="{error.age.max}")
	private int age;
	@NotBlank(message="姓名不能为空")
	private String name;
	@Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message="手机号码格式不正确")
	private String telPhone;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	
}
