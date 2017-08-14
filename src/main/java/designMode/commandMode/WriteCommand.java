package designMode.commandMode;

public class WriteCommand  implements Command{
	private CommandReceiver receiver;
	public WriteCommand(CommandReceiver receiver){
		this.receiver = receiver;
	}
	@Override
	public void execute() {
		receiver.methodWrite();
	}
	
}
