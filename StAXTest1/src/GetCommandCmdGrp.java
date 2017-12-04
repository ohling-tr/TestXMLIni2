
public class GetCommandCmdGrp {

	public GetCommandCmdGrp() {
		
	}
	
	public void execute() {
		boolean haveCmd = false;
		String cmdName = Main.autonomous.getCommand();
		switch (cmdName) {
		case "drive":
			System.out.println("addSequential(" + cmdName + "(" + Main.autonomous.getSpeed() + "," + Main.autonomous.getDistance() + ")");
			haveCmd = true;
			break;
		case "turn":
			break;
		case "if":
			break;
		case "then":
			break;
		case "else":
			break;
		case "findtarget":
			break;
		case "turntarget":
			break;
		case "wait":
			break;
		default:
			if (cmdName != "") {
				System.out.println("default not handled for " + cmdName);
			}
		}
		if (haveCmd) {
			System.out.println("addSequential(autoGetCommand())");
			haveCmd = false;
		}
	}
}
