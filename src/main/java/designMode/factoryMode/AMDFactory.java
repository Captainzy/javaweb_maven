package designMode.factoryMode;

import designMode.factoryMode.cpu.AMDCpu;
import designMode.factoryMode.cpu.Cpu;
import designMode.factoryMode.mainBoard.AMDMainBoard;
import designMode.factoryMode.mainBoard.MainBoard;

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
