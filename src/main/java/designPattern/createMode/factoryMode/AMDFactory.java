package designPattern.createMode.factoryMode;

import designPattern.createMode.factoryMode.cpu.AMDCpu;
import designPattern.createMode.factoryMode.cpu.Cpu;
import designPattern.createMode.factoryMode.mainBoard.AMDMainBoard;
import designPattern.createMode.factoryMode.mainBoard.MainBoard;

public class AMDFactory implements AbstractFactory{

	@Override
	public Cpu createCpu() {
		return new AMDCpu();
	}

	@Override
	public MainBoard createMainboard() {
		return new AMDMainBoard();
	}
	
}
