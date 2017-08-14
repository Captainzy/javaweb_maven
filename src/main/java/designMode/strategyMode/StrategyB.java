package designMode.strategyMode;

/**
 * @author zouyang
 * @time 2017年4月5日 下午2:51:27
 * @description 具体策略类B
 */
public class StrategyB implements Strategy {

	@Override
	public void operater() {
		System.out.println("策略模式 strategy B 执行。。。。");
	}

}
