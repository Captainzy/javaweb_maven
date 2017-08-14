package springFramework.typeConversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class PeopleToAnimal implements ConverterFactory<People, Animal> {

	@Override
	public <T extends Animal> Converter<People, T> getConverter(Class<T> targetClass) {
		return new PeopleToAnimalConverter<>(targetClass);
	}
	
	public final class PeopleToAnimalConverter<T extends Animal> implements Converter<People, T>{

		private Class<T> targetClass;
		public PeopleToAnimalConverter(Class<T> targetClass){
			this.targetClass = targetClass;
		}
		@Override
		public T convert(People people) {
			int age = Integer.valueOf(people.getAge());
			Child child = new Child();
			if(age<18){
				child.setSpecies("小孩");
				child.setAge(people.getAge());
				child.setName(people.getName());
			}else{
				child.setSpecies("不是小孩");
			}
			return (T) child;
		}
		
	}
	
}
