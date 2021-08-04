package mainDriver;

import java.io.IOException;
import java.net.UnknownHostException;

import backgroundProcess.OperationsExecutor;
import backgroundProcess.RobotConnection;

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
	OperationsExecutor op_ex = new OperationsExecutor();
	
	/**
	 * Creates an instance of RobotConnection.
	 * calls method startConnection
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws connection not possible
	 * 
	 * */
	private String openConnection() {
		try {
			return RobotConnection.getInstance().startConnection();
		} catch (UnknownHostException e) {
			return "Internal error. Unknown host.";
		} catch (IOException e) {
			return "Internal error. "+ e.toString();
		}
		
	}
	/**
	 * Uses the robot connection to send the message "home" to the MockRobot.
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws robot could not initialize
	 * 
	 * */
	private String initialize() {
		try {
			return RobotConnection.getInstance().callHome();
		} catch (UnknownHostException e) {
			return "Internal error. "+ e.toString();
		} catch (IOException e) {
			return "Internal error. "+ e.toString();
		}
	}
	
	/**
	 * Uses OperationExceutor to send appropriate message to the MockRobot.
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws exception describing why it was unsuccessful
	 * 
	 * */
	private String executeOperation(String operation, String[] paramNames, int[] paramValues) {
		return op_ex.execute(operation, paramNames, paramValues);
	}
	
	/**
	 * Uses the robot connection to send the message "home" to the MockRobot.
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws could not close connection
	 * 
	 * */
	private String abort() {
		try {
			return RobotConnection.getInstance().stopConnection();
		} catch (UnknownHostException e) {
			return "Internal error. "+ e.toString();
		} catch (IOException e) {
			return "Internal error. "+ e.toString();
		}
	}
}
