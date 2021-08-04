package backgroundProcess;

import java.io.*;
import java.net.*;

/**
 * using Singleton pattern, 
 * creates a connection (TCP/IP) to the robot that remains open until the user clicks "Abort" in the UI
 *
 *
 * Used the following example as a guide:
 * https://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip
 * */
public class RobotConnection {

	private int ip_address = 1000;
	private static RobotConnection instance;
	private boolean connected = false;
	private Socket socket;
	private OutputStream output_stream;
	private BufferedReader input_stream_reader; // reads the string returned by the MockRobot
	private int process_id;
	private boolean homed = false;
	private boolean operation_in_progress = false;
	private boolean empty_hand = true;
	
	/**s
	 * Calls method to start the connection
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * */
	private RobotConnection() throws UnknownHostException, IOException{
		establishConnection();
	}
	
	/**Returns an instance of the robot connection
	 * 
	 * Robot connection can send a message to initialize the robot and finish the connection 
	 * 
	 * @return the instance of RobotConnection created
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * */
	public static RobotConnection getInstance() throws UnknownHostException, IOException {
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
	 * @throws connection not possible //unimplemented
	 * */
	public String startConnection() {
		try {
			RobotConnection.getInstance();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "Unknow host name";
		} catch (IOException e) {
			e.printStackTrace();
			return "could not establish connection";
		}
		return "";
	}
	
	/**
	 * Starts the connection as a client to the robot API
	 * 
	 * When the connection is successful:
	 * Connected = true
	 * It also creates an in and out
	 * @throws UnknownHostException 
	 * 
	 * @throw IOException
	 * @throw errorCreatingConnection
	 * */
	private void establishConnection() throws UnknownHostException, IOException {
		socket = new Socket("MockRobot", ip_address);
		output_stream = socket.getOutputStream();
		InputStream in= socket.getInputStream();
		input_stream_reader = new BufferedReader(new InputStreamReader(in)); //Reads the whole message as a string
		
	}
	
	/**
	 * Sends a message to the robot.
	 * It assumes the string is already in the correct format.
	 * 
	 * @return message that robot gave in response.
	 * @throws IOException 
	 * @exception the message was not understood
	 * 
	 * */
	public String sendMessage(String formated_string) throws IOException {
		//First send item to Mock Robot and wait for return.
		PrintWriter writer = new PrintWriter(output_stream );
		writer.println(formated_string);
		return input_stream_reader.readLine(); //Read the next line
	}
	
	/**
	 * Stops the connection between the driver and the robot.
	 * 
	 * Method for: abort() 
	 * 
	 * @return an empty string if the process was successful, error message otherwise
	 * @throws IOException - connection could not be terminated
	 * */
	public String stopConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "Connection could not be terminated";
		}
		connected = false;
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
	 * @throws IOException 
	 * @throws robor is already in position
	 * @throws robot could not home
	 * */
	public String callHome() throws IOException {
		
		String id = sendMessage("home");
		if(Integer.valueOf(id) < 0) { //if the number returned is negative
			return "Another process is in progress";
		}
		homed = true;
		operation_in_progress = true;
		process_id = Integer.valueOf(id);
		return "";
	}
	/**return the ID for the operation that was queued 
	 * use only with "pick" and "place"
	 * 
	 * For use when "TRANSFER" is called
	 * 
	 * @return The process id for the new process occurring. 
	 * 
	 * @throws IOException 
	 * */
	public int waitForProcessToEnd(String process_to_send) throws IOException {
			while(true) {
				if(operation_in_progress = true) {
				String id = this.sendMessage(process_to_send);
				int id_int = Integer.valueOf(id);
				if(id_int >= 0) { //the process finished and a new one started
					return id_int;
				}
			}
		}
	}
	/**
	 * Access to operation_in_progress boolean
	 * 
	 * @return true when there is an operation in progress
	 * @return false the system is free
	 * */
	public boolean get_operation_in_progress() {
		return this.operation_in_progress;
	}
	/**
	 * Access to empty_hand boolean
	 * @return true when the hand is empty
	 * @return false when the hand is holding an item
	 * 
	 * It's assumes the hand is empty to begin with
	 * */
	public boolean get_empty_hand() {
		return this.operation_in_progress;
	}
	
	/**
	 * USed to change the value of the empty hand 
	 * (FOR methods "pick" and "place" to use)
	 * 
	 * switches values
	 * */
	public void change_empty_hand() {
		if(empty_hand) empty_hand = false;
		if(!empty_hand) empty_hand= true;
	}
	
}
