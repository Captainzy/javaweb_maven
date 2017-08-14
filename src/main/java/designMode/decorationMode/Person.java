package designMode.decorationMode;

//定义被装饰者，被装饰者初始状态有些自己的装饰  
public class Person implements Human {  

  @Override  
  public void wearClothes() {  
      System.out.println("穿什么呢。。");  
  }  

} 