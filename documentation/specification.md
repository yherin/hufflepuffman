## [Initial] Specification
### User actions
| User Action  | Expected Result |  Possible Error Situations |
| -----------  | --------------- | -------------------------- |
| Specify file for operation | File is ready to be operated on | Invalid file given|
| Compress file | Compressed file is created in current directory | Technical errors|
| Decompress file| Decompressed file is created in current directory| Technical errors|

Exception handling must be good - in no situation will the program crash.

### Use cases
![Use Case Diagram](/documentation/Use-Case-Diagram.png)


## Project structure

### High level class diagram
![High Level Class Diagram](/documentation/High-level-class-diagram.png)
