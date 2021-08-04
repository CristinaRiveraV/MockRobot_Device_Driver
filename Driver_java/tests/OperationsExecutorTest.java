
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import backgroundProcess.OperationsExecutor;
import backgroundProcess.ParametersDontMatch;

/**
 * @author Cristina Rivera
 *
 * A list of this tests can be found here: https://github.com/CristinaRiveraV/MockRobot_Device_Driver/blob/Development/Documentation/Testing.md
 *
 * This tests are to make sure the driver checks for all possible human errors when typing the arguments
 */
class ExecutingOperations {

	/**
	 * Test method for {@link backgroundProcess.OperationsExecutor#execute(java.lang.String, java.lang.String[], int[])}.
	 * 
	 * @test Name of operation Does not exist
	 * @test successful pick, place and transfer
	 *
	 * 
	 *
	 */
	@Test
	@DisplayName("Successfull operations")
	final void testExecute() {
		OperationsExecutor tester = new OperationsExecutor();
			 
		//test successfully send message
		assertEquals("", tester.execute("pick", new String[] {"Source Location"}, new int[] {10}) );
		assertEquals("", tester.execute("place", new String[] {"Destination Location"}, new int[] {11}) );
		//Transfer might not be possible without response from the MockRobot
		assertEquals("", tester.execute("transfer", new String[] {"sourceLocation","Destination Location"}, new int[] {11}));
	}

	/**
	 * Test method for {@link backgroundProcess.OperationsExecutor#test_pick(java.lang.String[], int[])}.
	 *
	 * @test parameter name incorrect
	 * @test more than 1 parameter name
	 * @test more than 1 parameter value
	 * @test successfully send message
	 */
	 
	private void assertEquals(String string, String execute) {
		// TODO Auto-generated method stub
		
	}

	@Test
	final void testTest_pick() throws UnknownHostException, ParametersDontMatch, IOException {
	
		 OperationsExecutor tester = new OperationsExecutor();
		 
		//test parameter name incorrect:
		  Assertions.assertThrows(ParametersDontMatch.class, () -> {
			  tester.test_pick(new String[] {"Destination Location"}, new int[] {10});
			  });
		  Assertions.assertThrows(ParametersDontMatch.class, () -> {
			  tester.test_pick(new String[] {"Some Location"}, new int[] {10});
			  });
		  
		//test more than 1 parameter name
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_pick(new String[] {"Some Location", "AnotherLocation"}, new int[] {10});
		});
		
		//test more than 1 parameter value
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_pick(new String[] {"Source Location"}, new int[] {10,12});
			  });

		//test successfully send message
		assertEquals("", tester.test_pick(new String[] {"Source Location"}, new int [] {10}));
	}

	

	/**
	 * Test method for {@link backgroundProcess.OperationsExecutor#test_place(java.lang.String[], int[])}.
	 * @throws IOException 
	 * @throws ParametersDontMatch 
	 * @throws UnknownHostException 
	 *
	 * 
	 * @test parameter name incorrect
	 * @test more than 1 parameter name
	 * @test more than 1 parameter value
	 * @test succesfully send message
	 */
	@Test
	final void testTest_place() throws UnknownHostException, ParametersDontMatch, IOException {
		OperationsExecutor tester = new OperationsExecutor();
		
		tester.test_pick(new String[]{"Source Location"}, new int[] {11}); //The MockRobot must be holding something
		
		
		//test parameter name incorrect:
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_place(new String[] {"Source Location"}, new int[] {10});
		});
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_place(new String[] {"Some Location"}, new int[] {10});
		});
	
		//test more than 1 parameter name
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_place(new String[] {"Some Location", "AnotherLocation"}, new int[] {10});
		});
		
		//test more than 1 parameter value
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_place(new String[] {"Destination Location"}, new int[] {10,13});
		});
		
		//test successfully send message
		assertEquals("", tester.test_place(new String[] {"Destination Location"},new int[] {10}));
	}

	/**
	 * Test method for {@link backgroundProcess.OperationsExecutor#test_transfer(java.lang.String[], int[])}.
	 * @throws IOException 
	 * @throws ParametersDontMatch 
	 * @throws UnknownHostException 
	 *
	 * @test parameter name incorrect
	 * @test more than 3 parameter name
	 * @test only 1 parameter name  
	 * @test more than 3 parameter value
	 * @test only 1 parameter value
	 * @test succesfully send message
	 */
	@Test
	final void testTest_transfer() throws UnknownHostException, ParametersDontMatch, IOException {
		OperationsExecutor tester = new OperationsExecutor();
		 
		//test parameter name incorrect:
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_transfer(new String[] {"Source Location","Some Location"}, new int[] {10,11});
		});
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_transfer(new String[] {"Some Location","Source Location"}, new int[] {10,11});
		});
		
		//test more than 2 parameter name
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_transfer(new String[] {"Some Location", "AnotherLocation", "Source Location"}, new int[] {10,11});
		});

		//test only 1 parameter name
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_transfer(new String[] {"Source Location"}, new int[] {10,11});
		});
	
	    //test more than 2 parameter value
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_transfer(new String[] {"Destination Location","Source Location"}, new int[] {10,11,12});
		});
		
		//test only 1 parameter value
		Assertions.assertThrows(ParametersDontMatch.class, () -> {
			tester.test_transfer(new String[] {"Destination Location","Source Location"}, new int[] {10});
		});
	  
		//test successfully send message
		assertEquals("", tester.test_place(new String[] {"Source Location", "Destination Location"}, new int[] {10,11}));
		assertEquals("", tester.test_place(new String[]{"Destination Location", "Source Location"}, new int[] {11,10}));
	}

}
