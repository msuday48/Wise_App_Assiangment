package org.Wise_App;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
public class WiseApp {
    public static void main(String[] args) {
        // Initialize Chrome browser
        WebDriver driver = new ChromeDriver();

        // Maximize browser window to full screen
        driver.manage().window().maximize();

        // Initialize WebDriverWait with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize SoftAssert for soft assertions
        SoftAssert softAssert = new SoftAssert();

        try {
            // Step 1: Navigate to the Wise platform
            driver.get("https://staging-web.wise.live");
            System.out.println("Navigated to the Wise platform successfully.");

            // Step 2: Perform login as a tutor
            performLogin(driver, wait, softAssert);

            // Step 3: Go to the classroom
            navigateToClassroom(driver, wait, softAssert);

            // Step 4: Schedule a session
            scheduleSession(driver, wait);

            // Step 5: Assert the session details
            assertSessionDetails(driver, wait);

            // Step 6: Perform all soft assertions
            softAssert.assertAll();
            System.out.println("All assertions passed successfully.");

        } catch (Exception e) {
            // Handle exceptions and log the error
            System.err.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the browser

            System.out.println("Browser closed successfully.");
        }
    }

    // Method to perform login as a tutor
    private static void performLogin(WebDriver driver, WebDriverWait wait, SoftAssert softAssert) throws InterruptedException {
        // Click on login using phone number
        WebElement phoneLoginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//span[normalize-space()='Continue with Mobile'])[1]")));
        phoneLoginButton.click();
        System.out.println("Clicked on 'Continue with Mobile' button.");

        // Enter the phone number
        WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Phone number']")));
        phoneNumberField.sendKeys("1111100000");
        System.out.println("Entered phone number successfully.");

        // Click on the "Get OTP" button
        WebElement getOtpButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='Get OTP']")));
        getOtpButton.click();
        System.out.println("Clicked on 'Get OTP' button.");

        // Enter OTP
        enterOTP(driver, wait);

        // Click on the "Verify" button
        WebElement verifyButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//span[normalize-space()='Verify'])[1]")));
        verifyButton.click();
        System.out.println("Clicked on 'Verify' button.");

        // Verify the institute name is displayed
        WebElement instituteNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='name text--24 font-weight--600 ml-3'])[1]")));
        String actualInstituteName = instituteNameElement.getText();
        softAssert.assertEquals(actualInstituteName, "Testing Institute", "Institute name mismatch!");
        System.out.println("Verified institute name: " + actualInstituteName);
    }

    // Method to enter OTP
    private static void enterOTP(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        WebElement otpField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@class='otp-field-box--0'])[1]")));
        otpField1.sendKeys("0");

        WebElement otpField2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@class='otp-field-box--1'])[1]")));
        otpField2.sendKeys("0");

        WebElement otpField3 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@class='otp-field-box--2'])[1]")));
        otpField3.sendKeys("0");

        WebElement otpField4 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//input[@class='otp-field-box--3'])[1]")));
        otpField4.sendKeys("0");
        System.out.println("OTP entered successfully.");
    }

    // Method to navigate to the classroom
    private static void navigateToClassroom(WebDriver driver, WebDriverWait wait, SoftAssert softAssert) throws InterruptedException {
        // Click on the "Group Courses" button
        WebElement groupCoursesButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='card-content d-flex py-sm-5 py-3 px-4 border-radius--8 card-hover-effect'])[2]")));
        groupCoursesButton.click();
        System.out.println("Clicked on 'Group Courses' button.");

        // Close the custom popup if it appears
        WebElement closePopupButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='v-icon notranslate v-icon--link mdi mdi-close theme--light']")));
        closePopupButton.click();
        System.out.println("Custom popup closed successfully.");

        // Navigate to the "Classroom for Automated Testing" page
        WebElement classroomAutomationTestingLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[normalize-space()='Classroom for Automated testing'])[1]")));
        classroomAutomationTestingLink.click();
        System.out.println("Navigated to 'Classroom for Automated Testing' page.");

        // Verify the classroom page is loaded
        WebElement classroomElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='text--24 font-weight--600'])[1]")));
        String classroomName = classroomElement.getText();
        softAssert.assertEquals(classroomName, "Classroom for Automated testing", "Classroom name mismatch!");
        System.out.println("Verified classroom name: " + classroomName);
    }

    // Method to schedule a session
    private static void scheduleSession(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        // Navigate to the Live Sessions tab
        WebElement liveSessionsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[normalize-space()='Live Sessions'])[1]")));
        liveSessionsTab.click();
        System.out.println("Clicked on 'Live Sessions' tab.");

        try {
            // Attempt to click the first element
            WebElement scheduleSessionButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//button[@class='mt-2 mt-sm-0 v-btn v-btn--has-bg theme--light v-size--default small primary-bg '])[1]")));
            scheduleSessionButton.click();
            System.out.println("✅ Clicked on 'Schedule Session' button (first element).");
        }
        catch (Exception e1) {
            System.out.println("First element not found or not clickable. Attempting to click the second element...");

            try {
                // Attempt to click the second element if the first one fails
                WebElement scheduleSessionButtonAlt = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//span[normalize-space()='Schedule Sessions'])[1]")));
                scheduleSessionButtonAlt.click();
                System.out.println("✅ Clicked on 'Schedule Session' button (second element).");
            }
            catch (Exception e2) {
                // Log error but continue execution
                System.err.println("❌ Both elements not found or not clickable. Continuing execution...");
            }
        }

        // Continue with the rest of the script
        System.out.println("Continuing with the next steps of the test...");

        // Click on the "Add session" button
        WebElement addSessionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='mb-3 px-2 v-btn v-btn--block v-btn--has-bg theme--light v-size--default small secondary-bg ']")));
        addSessionButton.click();
        System.out.println("Clicked on 'Add session' button.");

        Thread.sleep(1000);
        // Enter the session time
        WebElement timeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[10]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/input[1]")));
        Thread.sleep(1000);
        timeField.click();
        Thread.sleep(1000);
        timeField.clear();
        Thread.sleep(1000);
        timeField.sendKeys("10:00");
        Thread.sleep(1000);
        timeField.sendKeys(Keys.ENTER);
        System.out.println("Entered session time successfully.");

        Thread.sleep(1000);

            // XPath for the cancel button on the alert popup
            String cancelButtonXPath = "//button[@class='v-btn v-btn--block v-btn--has-bg theme--light v-size--default medium primary-bg ']";

            try {
                // Click on PM selection
                WebElement PM_selection = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"main-scrollable-parent\"]/div/div[1]/div[2]/div[1]/div/div[10]/div[1]/div/div/div[4]")));
                PM_selection.click();
                System.out.println("Entered PM successfully.");

                // Click on the Create button
                WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@class='v-btn v-btn--has-bg theme--light v-size--default medium primary-bg ']")));
                createButton.click();
                System.out.println("Clicked on 'Create' button.");

                // Check if the alert popup appears
                try {
                    WebElement cancelButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cancelButtonXPath)));
                    if (cancelButton.isDisplayed()) {
                        cancelButton.click();
                        System.out.println("Alert popup dismissed.");

                        WebElement PM_selections = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//*[@id=\"main-scrollable-parent\"]/div/div[1]/div[2]/div[1]/div/div[10]/div[1]/div/div/div[4]")));
                        PM_selections.click();
                        System.out.println("Entered PM successfully.");

                        // Click on the Create button
                        WebElement createButtons = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[@class='v-btn v-btn--has-bg theme--light v-size--default medium primary-bg ']")));
                        createButtons.click();
                        System.out.println("Clicked on 'Create' button.");
                    }
                } catch (Exception e) {
                    System.out.println("Alert popup not present, continuing execution.");
                }

                // Continue execution without the popup appearing the second time
                System.out.println("Continuing execution.");

            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());

        }
            Thread.sleep(1000);
    }

        // Method to assert session details
    private static void assertSessionDetails(WebDriver driver, WebDriverWait wait) {
        SoftAssert softAssert = new SoftAssert();

// Locating elements
        WebElement sessionDate = driver.findElement(By.xpath("//div[contains(@class, 'session-card')]//div[contains(text(),'Feb')]"));
        WebElement sessionTime = driver.findElement(By.xpath("//div[starts-with(normalize-space(), '10:')]"));
        WebElement sessionStatus = driver.findElement(By.xpath("//span[@class='v-chip__content']"));
        WebElement instructorName = driver.findElement(By.xpath("(//div[normalize-space()='Wise Tester'])[1]"));  // Corrected XPath for Instructor Name

// Printing actual values before assertions
        System.out.println("Session Date: " + sessionDate.getText());
        System.out.println("Session Time: " + sessionTime.getText());
        System.out.println("Session Status: " + sessionStatus.getText());
        System.out.println("Instructor Name: " + instructorName.getText());

// Soft Assertions
        softAssert.assertEquals(sessionDate.getText(), "02  Feb 25", "Session Date mismatch! Actual: " + sessionDate.getText());
        softAssert.assertEquals(sessionTime.getText(), "10:00 PM", "Session Time mismatch! Actual: " + sessionTime.getText());
        softAssert.assertEquals(sessionStatus.getText(), "Upcoming", "Session Status mismatch! Actual: " + sessionStatus.getText());
        softAssert.assertEquals(instructorName.getText(), "Wise Tester", "Instructor Name mismatch! Actual: " + instructorName.getText());

// /Report all assertion failures
        softAssert.assertAll();
    }
                }
