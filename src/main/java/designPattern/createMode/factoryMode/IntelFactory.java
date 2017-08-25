package designPattern.createMode.factoryMode;

import designPattern.createMode.factoryMode.cpu.Cpu;
import designPattern.createMode.factoryMode.cpu.IntelCpu;
import designPattern.createMode.factoryMode.mainBoard.IntelMainBoard;
import designPattern.createMode.factoryMode.mainBoard.MainBoard;

public class IntelFactory implements AbstractFactory{

	@Override
	public Cpu createCpu() {
		return new IntelCpu();
	}

	@Override
	public MainBoard createMainboard() {
		return new IntelMainBoard();
	}
	
}
