package designMode.factoryMode;

import designMode.factoryMode.cpu.Cpu;
import designMode.factoryMode.mainBoard.MainBoard;

/**
 * @author zouyang
 * @time 2017年2月16日 上午10:42:46
 * @description 
 * 	抽象工厂模式与工厂方法模式的最大区别就在于，工厂方法模式针对的是一个产品等级结构；而抽象工厂模式则需要面对多个产品等级结构。
 * 	可以看出，抽象工厂模式其实就是多个相似功能的工厂的集合。
 *	优点：
 * 	1.分离接口和实现
　　客户端使用抽象工厂来创建需要的对象，而客户端根本就不知道具体的实现是谁，客户端只是面向产品的接口编程而已。也就是说，客户端从具体的产品实现中解耦。
	2.使切换产品族变得容易
　　因为一个具体的工厂实现代表的是一个产品族，比如上面例子的从Intel系列到AMD系列只需要切换一下具体工厂。
	
	缺点：
	1.不太容易扩展新的产品
　　如果需要给整个产品族添加一个新的产品，那么就需要修改抽象工厂，这样就会导致修改所有的工厂实现类。
 */
public interface AbstractFactory {
	  /**
     * 创建CPU对象
     * @return CPU对象
     */
    public Cpu createCpu();
    /**
     * 创建主板对象
     * @return 主板对象
     */
    public MainBoard createMainboard();
}
