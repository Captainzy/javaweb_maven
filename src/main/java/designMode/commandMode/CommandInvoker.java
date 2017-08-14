package designMode.commandMode;

public class CommandInvoker {
	private Command command;
	public CommandInvoker(Command command){
		this.command = command;
	}
	public void testMethod(){
		command.execute();
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	
	
}
