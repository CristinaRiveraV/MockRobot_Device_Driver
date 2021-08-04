package backgroundProcess;


/**
 * using Singleton pattern, 
 * creates a connection (TCP/IP) to the robot that remains open until the user clicks "Abort" in the UI
 *
 * */
public class RobotConnection {

	private int ip_address = 1000;
	private static RobotConnection instance;
	private boolean connected = false;
	//private outputStream
	//private inputStream
	private int process_id;
	private boolean homed = false;
	private boolean operation_in_progress = false;
	private boolean empty_hand = true;
	
	/**
	 * Calls method to start the connection
	 * */
	private void RobotConnection(){
		establishConnection();
	}
	
	/**Returns an instance of the robot connection
	 * 
	 * Robot connection can send a message to initialize the robot and finish the connection 
	 * 
	 * @return the instance of RobotConnection created
	 * */
	public static RobotConnection getInstance() {
		if(instance == null) {
			//Make sure that there is no more than one instance
			synchronized (RobotConnection.class){
				if(instance == null) {
					instance = new RobotConnection();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Calls for an instance of roboConnection (which will create one if it is the first time it's called
	 * 
	 * Method for: openConnection() 
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws connection not possible
	 * */
	public String startConnection() {
		return "";
	}
	
	/**
	 * Starts the connection as a client to the robot API
	 * 
	 * When the connection is successful:
	 * conneceted = true
	 * */
	private void establishConnection() {}
	
	/**
	 * Sends a message to the robot.
	 * It assumes the string is already in the correct format.
	 * 
	 * @return message that robot gave in response.
	 * @exception the message was not understood
	 * 
	 * */
	public String sendMessage(String formated_tring) {
		return "";
	}
	
	/**
	 * Stops the conncetion between the driver and the robot.
	 * 
	 * Method for: abort() 
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws connection could not be finished
	 * */
	public String stopConnection() {
		return "";
	}
	
	/**
	 * @return true if connection is established
	 * @return false when the connection has not been started
	 * */
	public boolean checkConnection() {
		return connected;
	}
	
	/**
	 * @return true if robot has been "homed"
	 * @return false when robot has not be "homed"
	 * */
	public boolean checkHomedStatus() {
		return homed;
	}
	
	/**
	 * Sends mesage for robot to "home"
	 * 
	 * Method for: initialize() 
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws robor is already in position
	 * @throws robot could no home
	 * */
	public String callHome() {
		return sendMessage("home");
	}
	/**return true when the process has finished
	 * */
	public boolean waitForProcessToEnd(int process_in_progress_id) {
		if(operation_in_progress = true) {
			while(true) {
				String status = this.sendMessage("status%"+process_in_progress_id);
				if(status.equals("Finished Successfully") || status.equals("Terminated With Error")) {
					this.operation_in_progress = false;
					return true;
				}
			}
		}else return true;
	}
}
