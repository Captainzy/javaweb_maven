package designMode.commandMode;

public class TestClient {

	public static void main(String[] args) {
		/**
		 * 命令模式：
		 * 所有的命令会实现一个相同的命令接口Command，实现其中的执行方法，然后不同的命令类（ReadCommand、WriteCommand）里都有一个接收者（实现者）类(CommandReceiver)，
		 * 这个接收者类实际上内部具有很多方法(readMethod()、writeMethod())，而这些方法就与不同的命令类相联系，在具体的命令类里通过调用接收者类的实现方法
		 * (ReadCommand类的execute()方法里调用了CommandReceiver的readMethod()方法)，这样就可以实现在不同的命令类里调用我们想调用的方法，这样以后我们想新增一些命令
		 * 的时候就不会对之前的命令产生影响，比如：我们要增加一个删除命令，那么我们只需要在CommandReceiver类里面新增一个deleteMethod()方法，新建一个DeleteCommand类，
		 * 在DeleteCommand类的execute()方法里执行CommandReceiver的deleteMethod()方法，在调用的时候只需要将DeleteCommand命令传入CommandInvoker就可以了。
		 * 这样就降低了请求者Invoker与实现者Receiver之间的耦合，Invoker想要调用某个方法的时候，只需要传入特定的命令就行了，而不用去更改自己内部的代码。
		 * 
		 */
		CommandReceiver receiver = new CommandReceiver();
		ReadCommand readCommand = new ReadCommand(receiver);
		CommandInvoker invoker = new CommandInvoker(readCommand);
		invoker.testMethod();
		
		WriteCommand writeCommand = new WriteCommand(receiver);
		invoker.setCommand(writeCommand);
		invoker.testMethod();
	}

}
