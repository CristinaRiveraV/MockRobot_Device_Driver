package backgroundProcess;

/**
 * 
 */


@SuppressWarnings("serial")
public class OperationUnknown extends Exception {

	OperationUnknown(){}
	
    public String toString(){
		     return ("The name of the operation does not exist. \n Try PICK, PLACE or TRANSFER") ;
	}
}
