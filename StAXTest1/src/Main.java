
public class Main {

	private static String fileName;
	public static Autonomous autonomous;
	private static GetCommandCmdGrp autoGetCommand;
	
	public static void main(String[] args) {
		if ( args.length > 0 ) {
			fileName = args[0];
		} else {
			fileName = "";
		}
		
		int cmdID = 0;
		String cmdName = "";
		
		autonomous = new Autonomous(fileName);
		autoGetCommand = new GetCommandCmdGrp();
		
		while (autonomous.hasEvent()) {
			autoGetCommand.execute();
		}

	}

}
