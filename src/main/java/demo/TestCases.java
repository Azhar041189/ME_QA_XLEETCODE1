package demo;

import java.lang.annotation.ElementType;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    // @SuppressWarnings("deprecation")
    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://leetcode.com/");
        String URL = driver.getCurrentUrl();
        if (URL.contains("leetcode")) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        driver.get(("https://leetcode.com/"));
        WebElement Viewque = driver.findElement(By.xpath("//p[contains(text(), \"View Questions\")][1]"));
        Viewque.click();
        String ProblemURL = driver.getCurrentUrl();
        if (ProblemURL.contains("problemset")) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
        Thread.sleep(3000);
        List<WebElement> questionsList = driver
                .findElements(By.xpath("//div[@class='truncate']/a[not(contains(. , '632'))]"));
        System.out.println("First 5 Questions");
        for (int i = 0; i < 5 ; i++) {
            WebElement question = questionsList.get(i);
            String questionTitle = question.getText();

            // Print the question title
            System.out.println(questionTitle);
        }
        if (questionsList.size() > 0 && questionsList.get(0).getText().equals("1. Two Sum")) {
            System.out.println("Test Passed: The first question is 'Two Sum'.");
        } else {
            System.out.println("Test Failed: The first question is not 'Two Sum'.");
        }
        System.out.println("end Test case: testCase02");
    }
    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        driver.get("https://leetcode.com/problems/two-sum/");
             
        String URL = driver.getCurrentUrl();
        if (URL.contains("two-sum")) {
            System.out.println("The URL of the problem contains \"two-sum\"");
        } else {
            System.out.println("Fail");
        }
        System.out.println("end Test case: testCase03");
    }
    public void testCase04() throws InterruptedException {
        try {
        System.out.println("Start Test case: testCase04");
        driver.get("https://leetcode.com/");
        WebElement problemLink = driver.findElement(By.xpath("//p[contains(text(), \"View Questions\")][1]"));
        problemLink.click();
        Thread.sleep(3000);
        WebElement TwoSumURl = driver.findElement(By.xpath("//a[@href='/problems/two-sum']"));
        TwoSumURl.click();
        Thread.sleep(3000);
        WebElement submissionTab = driver.findElement(By.xpath("(//div[text()='Submissions'])[2]"));
        submissionTab.click();
        WebElement RegisterorSignIn = driver.findElement(By.xpath("//a[contains(text(), \"Register or Sign In\")]"));
        String text = RegisterorSignIn.getText();
        if(text.equalsIgnoreCase("Register or Sign In")){
            System.out.println("The message \"Register or Sign In\" is displayed when you click on the submissions tab.");
        }else{
            System.out.println("Register or Sign In' is not displayed.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
        System.out.println("end Test case: testCase04");
    }
}
