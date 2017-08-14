package designMode.facadeMode;

public class Test {
	public static void main(String[] args){
		//一个抽象出来的例子，一个客户端（你）是怎样通过一个 facade（计算机）来访问一个复杂的系统（计算机的内部，比如说 CPU 和硬盘驱动器）。
		 ComputerFacade computer = new ComputerFacade();
	     computer.start();
	}
}
