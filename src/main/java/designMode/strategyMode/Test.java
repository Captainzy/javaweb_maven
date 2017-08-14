package designMode.strategyMode;

public class Test {
	public static void main(String[] args){
		Context c = new Context(new StrategyA());
		c.test();
		
		c.setStrategy(new StrategyB());
		c.test();
	}
	
}
