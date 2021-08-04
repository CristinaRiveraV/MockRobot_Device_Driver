package backgroundProcess;

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
	 * */
	private boolean checkStatus() {
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
	 * @param parameterNames[] "SourceLocation" or "DestinationLocation"
	 * 
	 * @return empty string if the item was added succesfully
	 * */
	public String execute(String operation, String[]parameterNames, int[]parameterValues) {
		return "";
	}
	
	
	
	/**
	 * Checks ig there is any operation in progress.
	 * If there is no operation in progress it send the next string message to the robot.
	 * 
	 * to be used when the button is clicked. 
	 * 
	 * @return String when the operations were successfull
	 * */
	private String sendOperationMessage(Operation o) {
		return RobotConnection.getInstance().sendMessage(o.toString());
	}
	
	/**
	 * Checks all items are correct before creating a "pick" operation
	 * 
	 * @param paramNames = "SourceLocation" //ignore case
	 * @param paramValues should have length of 1
	 * 
	 * @return an empty string when successful
	 * @exception WrongParamNumber
	 * @exception WrongParamName 
	 * */
	private String pick(String[] paramNames, int[] paramValues) {
		return "";
	}
	
	/**
	 * Checks all items are correct before creating a "place" operation
	 * 
	 * @param paramNames = "DestinationLocation" //ignore case
	 * @param paramValues should have length of 1
	 * 
	 * @return an empty string when successful
	 * @exception WrongParamNumber
	 * @exception WrongParamName 
	 * */
	private String place(String[] paramNames, int[] paramValues) {
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
	 * @exception WrongParamNumber
	 * @exception WrongParamName 
	 * */
	private String transfer(String[] paramNames, int[] paramValues) {
		return "";
	}
	/** For Testing private method "pick"
	 * */
	public String test_pick(String[] paramNames, int[] paramValues) {
		return pick(paramNames, paramValues);
	}
	/** 
	 * 	For Testing private method "place"	 
	 *  */
	public String test_place(String[] paramNames, int[] paramValues) {
		return pick(paramNames, paramValues);
	}
	/** 	For Testing private method "transfer"
	 * */
	public String test_transfer(String[] paramNames, int[] paramValues) {
		return pick(paramNames, paramValues);
	}
}
