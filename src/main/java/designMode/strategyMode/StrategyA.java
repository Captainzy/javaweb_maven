package designMode.strategyMode;

/**
 * @author zouyang
 * @time 2017年4月5日 下午2:51:14
 * @description 具体策略类A 
 */
public class StrategyA implements Strategy {

	@Override
	public void operater() {
		System.out.println("策略模式 strategy A 执行。。。。");
	}

}
