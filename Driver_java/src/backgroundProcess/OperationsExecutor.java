package backgroundProcess;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Queue;

/**
 * Checks the input from the user before sending the formated message to the robot.
 * */
public class OperationsExecutor {
	//All fields are private so they can only be acces through accesor methods
	
	/** Small nested class to hold the value of the operation and return a string ready to be sent to the MockRobot*/
	public class Operation{
		private String name;
		private int value;
		
		
		Operation(String n, int v){
			this.name = n;
			this.value = v;
		}
		/**@return formated string 
		 * */
		@Override public String toString() {
			return name + "%" + value;
		}
	}
	
	private enum OperationName {pick, place, transfer	}
	private enum ParameterName{ Sourcelocation, DestinationLocation }
	
	private String process_status;
	private int process_in_progress_id; 

	
	/**
	 * Will create a new executor
	 * Create this class at the beginning and re-use every time the button is called
	 * */
	public void OperationExecutor() {}
	/**
	 * Method used by "transfer" 
	 * 
	 * Infinite loop that breaks when there is no operation in progress
	 * 
	 * @return true when system is free to execute a new command
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * */
	private boolean checkStatus() throws UnknownHostException, IOException {
		while(true) {
			if(!RobotConnection.getInstance().get_operation_in_progress()) return true; 
		}
	}
	
	
	/**
	 * Will do all checks to make sure the text is correct.
	 * Send a message to the robot and returns an empty string.
	 * 
	 * Robot will continue to run the process until it is finished.
	 * 
	 * @param operation "pick", "place" or "transfer"
	 * @param parameterNames[] a String array with the following text: "SourceLocation" or "DestinationLocation"
	 * @param parameterValues[] an Integer array with 1 or 2 values
	 * 
	 * @return empty string if the item was sent successfully
	 * @return "Another item is in progress"
	 * */
	public String execute(String operation, String[] parameterNames, int[] parameterValues) {
		
		switch(operation) {
			case "pick": try {
							return this.pick(parameterNames, parameterValues);
						} catch (UnknownHostException e) {
							return "Could not find host.";
						} catch (ParametersDontMatch e) {
							return e.toString();
						} catch (IOException e) {
							return "Internal Error. IO Exception";
						}
			case "place": try {
							return this.place(parameterNames, parameterValues);
						} catch (UnknownHostException e) {
							return "Could not find host.";
						} catch (ParametersDontMatch e) {
							return e.toString();
						} catch (IOException e) {
							return "Internal Error. IO Exception";
						}
			case "transfer": try {
							return this.place(parameterNames, parameterValues);
						} catch (UnknownHostException e) {
							return "Could not find host.";
						} catch (ParametersDontMatch e) {
							return e.toString();
						} catch (IOException e) {
							return "Internal Error. IO Exception";
						}
			default: OperationUnknown exception = new OperationUnknown();
						return exception.toString();
			}
		
	}
	
	
	
	
	
	/**
	 * Checks all items are correct before creating a "pick" operation and sending it to the Robot 
	 * 
	 * @param paramNames = "SourceLocation" //ignore case
	 * @param paramValues should have length of 1
	 * 
	 * @return an empty string when successful
	 * @exception ParametersDontMatch
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws Exception 
	 * */
	private String pick(String[] paramNames, int[] paramValues) throws ParametersDontMatch, UnknownHostException, IOException {
		//
		if(paramNames.length != 1 || paramValues.length != 1) throw new ParametersDontMatch("pick");
		if(!(paramNames[0].replaceAll("\\s","")).equalsIgnoreCase("Sourcelocation")) throw new ParametersDontMatch("pick");
		
		if(!RobotConnection.getInstance().get_empty_hand()) return "Cannot pickup. Holding something";
		
		Operation op = new Operation("pick",paramValues[0]);
		String command  = RobotConnection.getInstance().sendMessage(op.toString());

		try {
			process_in_progress_id =Integer.valueOf(command);
		}catch(NumberFormatException v) {
			return "Internal error. String could not be formated as integer";
		}
		
				
		if(process_in_progress_id < 0) { //if the number returned is negative there is an operation in progress
			return "Another process is in progress";
		}
		RobotConnection.getInstance().change_empty_hand();
		return "";
	}
	
	/**
	 * Checks all items are correct before creating a "place" operation  and sending it to the Robot 
	 * 
	 * @param paramNames = "DestinationLocation" //ignore case
	 * @param paramValues should have length of 1
	 * 
	 * @return an empty string when successful
	 * @exception ParametersDontMatch
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * */
	private String place(String[] paramNames, int[] paramValues) throws ParametersDontMatch, UnknownHostException, IOException {
		//
		if(paramNames.length != 1 || paramValues.length != 1) throw new ParametersDontMatch("place");
		if(!(paramNames[0].replaceAll("\\s","")).equalsIgnoreCase("Destinationlocation")) throw new ParametersDontMatch("place");
		
		if(RobotConnection.getInstance().get_empty_hand()) return "Cannot place without holding something first";
		
		Operation op = new Operation("place",paramValues[0]);
		String command  = RobotConnection.getInstance().sendMessage(op.toString());

		try {
			process_in_progress_id =Integer.valueOf(command);
		}catch(NumberFormatException v) {
			return "Internal error. String could not be formated as integer";
		}
		
				
		if(process_in_progress_id < 0) { //if the number returned is negative there is an operation in progress
			return "Another process is in progress";
		}
		RobotConnection.getInstance().change_empty_hand();
		return "";
	}
	/**
	 * Checks all items are correct 
	 * Calls a "pick" operation >> waits for it to finish >> calls for a "place" operation
	 * 
	 * @param paramNames = "DestinationLocation" and "SourceLocation" //ignore case
	 * @param paramValues should have length of 2
	 * 
	 * @return an empty string when successful
	 * 
	 * @exception ParametersDontMatch
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * */
	private String transfer(String[] paramNames, int[] paramValues) throws ParametersDontMatch, UnknownHostException, IOException {
		if(paramNames.length != 2 || paramValues.length != 2) throw new ParametersDontMatch("place");
		
		if(!(paramNames[0].replaceAll("\\s","")).equalsIgnoreCase("Destinationlocation")) throw new ParametersDontMatch("place");
		
		
		if("" .equals(pick(new String[] {paramNames[0]},new int[] {paramValues[0]}))) {
			process_in_progress_id = RobotConnection.getInstance().waitForProcessToEnd("pick%"+ paramNames[0]);
			return "";
			
		}
		
		pick(new String[] {paramNames[1]},new int[] {paramValues[1]});
		process_in_progress_id = RobotConnection.getInstance().waitForProcessToEnd("place%"+ paramNames[0]);
		return "";
		
		
		
	}
	/** For Testing private method "pick"
	 * @throws IOException 
	 * @throws ParametersDontMatch 
	 * @throws UnknownHostException 
	 * */
	public String test_pick(String[] paramNames, int[] paramValues) throws UnknownHostException, ParametersDontMatch, IOException {
		return pick(paramNames, paramValues);
	}
	/** 
	 * 	For Testing private method "place"	 
	 * @throws IOException 
	 * @throws ParametersDontMatch 
	 * @throws UnknownHostException 
	 *  */
	public String test_place(String[] paramNames, int[] paramValues) throws UnknownHostException, ParametersDontMatch, IOException {
		return pick(paramNames, paramValues);
	}
	/** 	For Testing private method "transfer"
	 * @throws IOException 
	 * @throws ParametersDontMatch 
	 * @throws UnknownHostException 
	 * */
	public String test_transfer(String[] paramNames, int[] paramValues) throws UnknownHostException, ParametersDontMatch, IOException {
		return pick(paramNames, paramValues);
	}
}
