package backgroundProcess;

@SuppressWarnings("serial")
public class ParametersDontMatch extends Exception {

	String operation;
	ParametersDontMatch(String operation){ this.operation =operation;}
	
    public String toString(){
    	if(operation.equals("pick"))
		     return ("\"pick\" needs:  \n"
		     		+ "1 paramNames = \"SourceLocation\" "
		     		+ "and 1 paramValues") ;
    	if(operation.equals("place"))
		     return ("\"place\" needs:  \n"
		     		+ "1 paramNames = \"DestinationLocation\" "
		     		+ "and 1 paramValues") ;
     	if(operation.equals("transfer"))
		     return ("\"transfer\" needs:  \n"
		     		+ "2 paramNames = 1 \"SourceLocation\" and 1 \"DestinationLocation\" "
		     		+ "and 2 paramValues") ;
     	return "";
	}

}
