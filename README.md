WiseApp Automation Testing

This project contains an automated test script written in Java using Selenium WebDriver and TestNG for the Wise platform. The script performs login, navigates to the classroom, schedules a session, and verifies session details.

Prerequisites

Before running the script, ensure you have the following installed:

1. Java Development Kit (JDK)- Version 8 or above
2. Maven - For dependency management
3. Google Chrome The browser for executing the tests
4. ChromeDriver- Compatible with your installed version of Chrome
5. TestNG- Testing framework

## Project Setup

1. Clone the Repository
   `
   git clone https://github.com/msuday48/Wise_App_Assiangment.git
   cd <repository_folder>
  

2. Install Dependencies
   Ensure you have a `pom.xml` file with the necessary dependencies. If not, add the following:

   <dependencies>
       <dependency>
           <groupId>org.seleniumhq.selenium</groupId>
           <artifactId>selenium-java</artifactId>
           <version>4.8.0</version>
       </dependency>
       <dependency>
           <groupId>org.testng</groupId>
           <artifactId>testng</artifactId>
           <version>7.4.0</version>
           <scope>test</scope>
       </dependency>
   </dependencies>
   

   Run the following command to install dependencies:
  
   mvn clean install
   

3. Set Up ChromeDriver
   - Download ChromeDriver from [here](https://sites.google.com/a/chromium.org/chromedriver/downloads).
   - Extract and place the ChromeDriver executable in your system PATH or specify its location in the code.

Running the Test

1. Navigate to the Source Directory
   Ensure you're in the directory where `WiseApp.java` is located:
   
   cd src/main/java/org/Wise_App
   

2. Compile the Code
   Compile the Java file:
   
   javac WiseApp.java
   

3. Run the Script
   Execute the compiled class:
   
   java org.Wise_App.WiseApp
   ```

## Test Flow

Test Steps
Login as a Tutor:

The script navigates to the Wise platform and logs in using the provided phone number (+911111100000) and OTP (0000).

It verifies that the institute name "Testing Institute" is displayed.

Navigate to Classroom:

The script navigates to the "Group Courses" tab and selects the "Classroom for Automated Testing" classroom.

It verifies that the classroom name is correct.

Schedule a Session:

The script navigates to the "Live Sessions" tab and schedules a session for today at 10:00 PM.

It handles alert popups if they appear during the scheduling process.

Assert Session Details:

The script verifies the session details (date, time, status, and instructor name) using soft assertions.

If any assertion fails, the script continues execution and reports all failures at the end.


Expected Output

Navigated to the Wise platform successfully.
Clicked on 'Continue with Mobile' button.
Entered phone number successfully.
Clicked on 'Get OTP' button.
OTP entered successfully.
Clicked on 'Verify' button.
Verified institute name: Testing Institute
Clicked on 'Group Courses' button.
Custom popup closed successfully.
Navigated to 'Classroom for Automated Testing' page.
Verified classroom name: Classroom for Automated testing
Clicked on 'Live Sessions' tab.
 Clicked on 'Schedule Session' button 
Continuing with the next steps of the test...
Clicked on 'Add session' button.
Entered session time successfully.
Alert popup dismissed.
Entered PM successfully.
Clicked on 'Create' button.
Continuing execution.
Session Date: 03 Feb 25
Session Time: 10:00 PM
Session Status: Upcoming
Instructor Name: Wise Tester
Browser closed successfully.

