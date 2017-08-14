package designMode.factoryMode;

import designMode.factoryMode.cpu.Cpu;
import designMode.factoryMode.cpu.IntelCpu;
import designMode.factoryMode.mainBoard.IntelMainBoard;
import designMode.factoryMode.mainBoard.MainBoard;

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
