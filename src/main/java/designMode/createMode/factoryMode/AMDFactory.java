package designMode.createMode.factoryMode;

import designMode.createMode.factoryMode.cpu.AMDCpu;
import designMode.createMode.factoryMode.cpu.Cpu;
import designMode.createMode.factoryMode.mainBoard.AMDMainBoard;
import designMode.createMode.factoryMode.mainBoard.MainBoard;

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
