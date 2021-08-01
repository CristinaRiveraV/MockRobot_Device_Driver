# Testing

  

## Possible Errors per interface method 
---
1. ### `OpenConnection()` 
- cannot establish connection
- connection is already established  
2. ### `Initialize()`
- robot could not 'home' (for any reason)
- robot is already 'homed'
- connection hasn't been established
3. ### `ExecuteOperation(String operation, String[] parameterNames, String[] parameterValues)`
- *operation* does not exist
- "transfer" is missing parameters
- both parameters in "transfer" have the same *name*
- number of parameters does not match
- cannot "pick" anything up if already holding something
- cannot "place" if hand is empty
- *parameterName* does not exist
- parameterNames of parameterValues have more items than the operation needs
4. ### `Abort()`
- clicked button by accident
    For this case I would add a confirmation stage, but in this case it will disconect the robot immediatly 

## List of unit Tests per interface method
FOllowed by description of how they were solved
---
1. ### `OpenConnection()` 
- [ ] connection established succesfully
- [ ] error trying to connect
- [ ] wrong port number 
- [ ] connection terminated succesfully

2. ### `Initialize()`
- [ ] connection has not been established yet
- [ ] robot could not home 
- [ ] robot is already homed

3. ### `ExecuteOperation(String operation, String[] parameterNames, String[] parameterValues)`
- [ ]*operation* does not exist
- [ ] "transfer" has 1 parameterNames and 2 parameterValues
- [ ] "transfer" has 2 parameterNames and 1 parameterValues
- [ ] "transfer" has 2 parameterNames called the same and 2 parameterValues 
- [ ] "transfer" has 1 parameterNames and 2 parameterValues
- [ ] "transfer" has 3 parameterNames and 2 parameterValues
- [ ] Successfully "pick" something 
- [ ] successfully "transfer" something
- [ ] successfully "place" something
- [ ] try to "pick" something while holding something
- [ ] try to place something wyhen the hand is empty 
- [ ] try to call an *operation* with a mispelt name
- [ ] try ans send unknown names for parameters
- [ ] try "pick" and "place" with more then one item in 

4. ### `Abort()`
- [ ] connection terminated succesfully