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
		public String toString() {
			return name + "%" + value;
		}
	}
	
	private enum OperationName {pick, place, transfer	}
	private enum ParameterName{ Sourcelocation, DestinationLocation }
	private Queue<Operation> queue;
	private String process_status;
	private int process_in_progress_id; 
	private boolean empty_hand = true; //The robot's hand is assumed to be empty at the begining
	private boolean operation_in_progress = false; //when the class is initialized, no function has been called
	
	
	/**
	 * Will create a new executor
	 * 
	 * */
	public void OperationExecutor() {}
	/**
	 * Returns a string saying the status of the process in progress.
	 * 
	 * @return "In Progress"
	 * @return "Finished Successfully"
	 * @return "Terminated With Error"
	 * 	 * */
	private String checkStatus() {
		return RobotConnection.getInstance().sendMessage("status%"+this.process_in_progress_id);
	}
	
	/**
	 * Adds an operation to the queue. 
	 * The operations will be called in FIFO order.
	 * */
	private void addItemToQueue(Operation o) {
		queue.add(o);
	}
	
	/**
	 * Adds operation to the queue, and it will happen as soon as any other operations have finished
	 * The parameters are the inputs by the user.
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
	 * Checks if the string coincides with a real operation
	 * @return operationName as a string 
	 * */
	private String checkOperation(String operation) {
		
	} 
	/**
	 * Checks the number of parameters according to the operation.
	 * Checks that the parameter name coincides with one of the pre-existing ones
	 * 
	 * @param string to be checked
	 * @param int possition within the array (to be used when creaing a "pick" or "place" for transfering 
	 * 
	 * @return int possition in the array if it was an existing one
	 * @exception ParameterNameUnexistant
	 * */
	private int checkParameter(String parameter_name, int array_position) {
		return array_position;
	}
	
	/**
	 * @param int for the position //to be used with Parameter position
	 * @param values array, to check for the value at position specified
	 * 
	 * @return int value for creating a new operation
	 * 
	 * */
	private int getValue(int position, int[] values_array) {}
	/**
	 * creates a "pick" or "place" operation
	 * 
	 * if the operation is "transfer" it can be used to create 2 separate operations
	 * 
	 * @param Name is a sting that has already been confirmed 
	 * @param value already chosen according to parameter name
	 * 
	 * @return a queue with 1 or 2 items, depending on the operation
	 * 
	 * pick will return a pick
	 * place will return a place 
	 * */
	private Operation createOperation(String name, int value){
		
		return new Operation(name, value);
	
	}
	
	/**
	 * Checks ig there is any operation in progress.
	 * If there is no operation in progress it send the next string message to the robot.
	 * 
	 * to be used when the button is clicked. 
	 * 
	 * @return String when the operations were successfull
	 * */
	private String sendOperationMessage() {
		return "";
	}
}
