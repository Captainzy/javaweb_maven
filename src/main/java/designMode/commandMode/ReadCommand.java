package designMode.commandMode;

public class ReadCommand implements Command{
	private CommandReceiver receiver;
	public ReadCommand(CommandReceiver receiver){
		this.receiver = receiver;
	}
	@Override
	public void execute() {
		receiver.methodRead();
	}

}
