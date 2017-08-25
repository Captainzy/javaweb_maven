package designMode.createMode.factoryMode;

import designMode.createMode.factoryMode.cpu.Cpu;
import designMode.createMode.factoryMode.cpu.IntelCpu;
import designMode.createMode.factoryMode.mainBoard.IntelMainBoard;
import designMode.createMode.factoryMode.mainBoard.MainBoard;

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
