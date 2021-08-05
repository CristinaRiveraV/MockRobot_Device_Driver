# MockRobot_Device_Driver

## Description

The MockRobot is a robotic arm that moves objects to and from various locations around a system.  The robot can be controlled remotely by sending commands from a UI, implemented by the MockRObot_Device_Driver. 

### Requirements

The user will press a button mapped to one of the Device Driver functions below after typing in any needed parameters.  The UI program requires that each function returns either an empty string if the operation completed successfully, or a string with a description of an error that occurred during the function call, which will be displayed to the user.

[Driver Interface Details](https://docs.google.com/document/d/1Hmib_VlGv-B80Fw2MoMsBSeRfoPw23OI/edit?usp=sharing&ouid=114781554892759520269&rtpof=true&sd=true)

Extra information:

- [MockRobot Device API](https://drive.google.com/file/d/18cUXNJ3LKrwjKRp7UfoA_x9PXySLczRu/view?usp=sharing) 

## Assumptions
- MockRobot cannot pick up a thing if already holding something.
- MockRobot cannot place an object if it is not holding anything.
- Pre knowledge of Singleton Pattern (used for the class creating the connection to the MockRobot)

## Documentation

[Class Diagram](https://github.com/CristinaRiveraV/MockRobot_Device_Driver/blob/Development/Documentation/MockRobotDriver_ClassDiagram.png) Shows the Class Diagram used to write the driver.

[Source Code Document (Java)]() Explains some of the methods more in detail. This could potentially be used for other object oriented languages.

[Testing](https://github.com/CristinaRiveraV/MockRobot_Device_Driver/blob/Development/Documentation/Testing.md) Document describing possible tests. 

## License

[Apache Licence 2.0](https://github.com/CristinaRiveraV/MockRobot_Device_Driver/blob/main/LICENSE)

## Contact

cristina.rivera.valdez@gmail.com