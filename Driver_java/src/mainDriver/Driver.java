package mainDriver;

public class Driver {

	/**
	 * This Class will connect the driver to the GUI.
	 * Since GUI is not real, these class is not used.
	 * */
	public static void main(String[] args) {
		
	}
	
	/*Each of the following methods could be their own class so, 
	 * that could make them easier to identify in the code and do edits if necessary.
	 * (i.e. if the driver will be used for another robot and any of the parameters has to be changed.) 
	 * 
	 * */
	
	
	/**
	 * Creates an instance of RobotConnection.
	 * calls method startConnection
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws connection not possible
	 * 
	 * */
	private String openConnection() {
		return "";//RobotConnetion.getInstance().createConnection();
		
	}
	/**
	 * Uses the robot connection to send the message "home" to the MockRobot.
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws robot could not initialize
	 * 
	 * */
	private String initialize() {
		return "";
	}
	
	/**
	 * Uses OperationExceutor to send appropriate message to the MockRobot.
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws exception describing why it was unsuccessful
	 * 
	 * */
	private String executeOperation(String operation, String[] paramNames, String[] paramValues) {
		return "";
	}
	
	/**
	 * Uses the robot connection to send the message "home" to the MockRobot.
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws could not close connection
	 * 
	 * */
	private String abort() {
		return "";
	}
}
