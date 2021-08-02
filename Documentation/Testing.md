# Testing

  

## Possible Errors per interface method 
---
### 1. `OpenConnection()` 
- cannot establish connection
- connection is already established  
### 2.  `Initialize()`
- robot could not 'home' (for any reason)
- robot is already 'homed'
- connection hasn't been established
### 3. `ExecuteOperation(String operation, String[] parameterNames, String[] parameterValues)`
- *operation* does not exist
- "transfer" is missing parameters
- both parameters in "transfer" have the same *name*
- number of parameters does not match
- cannot "pick" anything up if already holding something
- cannot "place" if hand is empty
- *parameterName* does not exist
- parameterNames of parameterValues have more items than the operation needs
### 4. `Abort()`
- clicked button by accident
    For this case I would add a confirmation stage, but in this case it will disconect the robot immediatly 

## Tests 
---
Lists of tests followed by expected output
### 1. `OpenConnection()`   
   This methods will not be tested because the MockRobot is not real so it is imposible to establish a connection
- [x] connection established succesfully
  
  `return` "" 
  
 - [x] error trying to connect 

     `return` "Connection could not be established"
- [x] wrong port number 

    This error should never occur because the port number has already been established
- [x] connection terminated succesfully

    `return` ""

### 2. `Initialize()`

None of the following tests wii be written since the outputs depend on the response from the MockRobot:
- [x] connection has not been established yet
    

- [x] robot could not home 

    `return` "Robot could not be initialized"
- [x] robot is already homed

    `return` "Robot is already ininitial positon"

### 3. `ExecuteOperation(String operation, String[] parameterNames, String[] parameterValues)`
Some methods within this main classes will be tested to make sure the message sent to the **MockRobot** is correct

- [ ] *operation* does not exist

    `return` "The name of the operation does not exist. Try *PICK*, *PLACE* or *TRANSFER*"
- [ ] "transfer" has 1 parameterNames and 2 parameterValues

    `return` "*TRANSFER* must contain "Source Location" and "Destination Location" as parameter names"
- [ ] "transfer" has 2 parameterNames and 1 parameterValues

    `return` "*TRANSFER* needs 2 parameter Values, one for source location and one for destination location"
- [ ] "transfer" has 2 parameterNames called the same and 2 parameterValues 

    `return` "*TRANSFER* must contain "Source Location" and "Destination Location" as parameter names"

- [ ] "transfer" has 3 parameterNames and 2 parameterValues

    `return` "*TRANSFER* must contain ONE "Source Location" and ONE "Destination Location" as parameter names"
- [ ] Successfully "pick" something 

    `return` ""
- [ ] successfully "transfer" something

    `return` ""
- [ ] successfully "place" something

    `return` ""
- [ ] try to "pick" something while holding something

    `return` "**MockRobot** is already holding something. Try *PLACE* to place the item"
- [ ] try to place something when the hand is empty 

    `return` "**MockRobot** is not holding anything. Try *PICK* to pick up an item"
- [ ] try to call an *operation* with a mispelt name

   `return` "The name of the operation does not exist. Try *PICK*, *PLACE* or *TRANSFER*"
- [ ] try and send unknown/wrong names for parameters for "pick"

    `return` "*PICK* most contain only one parameter named "Source Location" "

- [ ] try and send unknown/wrong names for parameters for "place"

    `return` "*PLACE* most contain only one parameter named "Destination Location" "
- [ ] try "pick" and "place" with more then one item in the values array

    `return` "*PICK* and *PLACE* must contain only one parameter values"

### 4. `Abort()`

This will not be tested because the connection cannot be established in the first place
- [x] connection terminated succesfully

    `return` ""