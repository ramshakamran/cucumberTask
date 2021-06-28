# ReadMe!

#### Prerequisite 
Java 1.8
Maven

#### How to Run
1. Clone the project
2. Go to project root folder
3. Type following commands
`mvn clean install` and    `mvn test`
4. Test results will be displayed in the terminal as well as in target/destination html file will be available
5. Test cases (feature files) are present inside the Java folder followed by feature folder. (\src\test\java\features)
6. Pages are present inside the pageObjects folder. (\src\main\java\PageObjects)
7. Test scenarios for API Automation are present in MarketCapAPI.feature.
8. Test scenarios for UI Automation are present in MarketCapUI.feature.



#### Troubleshoot

1. If the tests failed due to browser incapabilities you need to download the chromedriver.exe and place it in drivers folder
- Chrome Driver (Mac / Linux / Windows):
http://chromedriver.storage.googleapis.com/index.html
