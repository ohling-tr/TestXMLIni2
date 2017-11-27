
public class SomeSystem {

	private double driveStraightP;
	private double driveStraightI;
	private double driveStraightD;
	private double gyroTurnP;
	private double gyroTurnI;
	private double gyroTurnD;
	private double driveDistanceP;
	private double driveDistanceI;
	private double driveDistanceD;
		
	public SomeSystem() {
		
		setSpeedController("leftDrive");
		setSpeedController("rightDrive");
		
		driveStraightP = TestXMLIni.robotMap.getPIDP("driveStraight");
		driveStraightI = TestXMLIni.robotMap.getPIDI("driveStraight");
		driveStraightD = TestXMLIni.robotMap.getPIDD("driveStraight", 0.75);
		System.out.println("driveStraightPID: " + driveStraightP + " " + driveStraightI + " " + driveStraightD);
		
		gyroTurnP = TestXMLIni.robotMap.getPIDP("gyroTurn");
		gyroTurnI = TestXMLIni.robotMap.getPIDI("gyroTurn");
		gyroTurnD = TestXMLIni.robotMap.getPIDD("gyroTurn", 0.9);
		System.out.println("gyroTurnPID: " + gyroTurnP + " " + gyroTurnI + " " + gyroTurnD);
		
		driveDistanceP = TestXMLIni.robotMap.getPIDP("driveDistance");
		driveDistanceI = TestXMLIni.robotMap.getPIDI("driveDistance");
		driveDistanceD = TestXMLIni.robotMap.getPIDD("driveDistance", 0.6);
		System.out.println("driveDistancePID: " + driveDistanceP + " " + driveDistanceI + " " + driveDistanceD);
		
	}
	
	private void setSpeedController(String speedController) {
		
		int iPort = TestXMLIni.robotMap.getSpeedControllerPort(speedController);
		String model = TestXMLIni.robotMap.getSpeedControllerModel(speedController);
		switch (model) {
			case "UNKNOWN":
				System.out.println("construct default on port " + iPort);
				break;
			case "CANTalon":
				System.out.println("construct CANTalon on port " + iPort);
				break;
			case "VictorSP":
				System.out.println("construct VictorSP on port " + iPort);
				break;
			case "TalonSRX":
				System.out.println("construct TalonSRX on port " + iPort);
				break;
			default:
				System.out.println("not defined " + model + " on port " + iPort);		
		}
		
	}
}
