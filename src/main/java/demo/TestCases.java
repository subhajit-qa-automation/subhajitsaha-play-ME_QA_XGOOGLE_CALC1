package demo;

import java.time.Duration;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static java.util.concurrent.TimeUnit.SECONDS;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
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
        driver.manage().timeouts().implicitlyWait(30, SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.google.com");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("google")) {
            System.out.println("Pass: URL contains 'google'.");
        } else {
            System.out.println("Fail: URL does not contain 'google'. Actual: " + currentUrl);
        }

        WebElement searchBar = driver.findElement(By.id("APjFqb"));
        searchBar.sendKeys("calculator");
        searchBar.submit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@id='cwos'])[1]")));

        // Verify the initial display shows zero
        WebElement calculatorDisplay = driver.findElement(By.xpath("(//span[@id='cwos'])[1]"));
        String initialDisplay = calculatorDisplay.getText();

        // Check if the initial display is as expected
        if ("0".equals(calculatorDisplay.getText())) {
            System.out.println("Pass: Initial display shows zero (0).");
        } else {
            System.out.println("Fail: Initial display does not show zero (0). Actual: " + calculatorDisplay.getText());
        }

        System.out.println("End Test case: Verify the Homepage URL and Initial Display");
        System.out.println("end Test case: testCase02");



    }

    public void testCase02_VerifyAdditionAndSubtraction() throws InterruptedException {

        System.out.println("Start Test case: testCase02");
        driver.get("https://www.google.com");
        WebElement searchBar = driver.findElement(By.id("APjFqb"));
        searchBar.sendKeys("calculator");
        searchBar.submit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@id='cwos'])[1]")));

        // Performing Addition
        String result = "12";

        WebElement five5 = driver.findElement(By.xpath("//div[text()='5']"));
        five5.click();
        Thread.sleep(2000);
        WebElement plusPlus = driver.findElement(By.xpath("//div[text()='+']"));
        plusPlus.click();
        WebElement seven7 = driver.findElement(By.xpath("//div[text()='7']"));
        seven7.click();
        Thread.sleep(2000);
        WebElement equals = driver.findElement(By.xpath("//div[text()='=']"));
        equals.click();
        Thread.sleep(1000);

        WebElement sum = driver.findElement(By.xpath("(//span[@id='cwos'])[1]"));
        Thread.sleep(2000);

        if (result.equals(sum.getText())) {
            System.out.println("Displayed result is correct");
        } else {
            System.err.println("Displayed result is not correct: " + sum.getText());
        }

        // Performing Subtraction -->> 15 - 8 = 7

        WebElement allClearButton = driver.findElement(By.xpath("//div[text()='AC']"));
        allClearButton.click();
        Thread.sleep(2000);

        WebElement oneBtn = driver.findElement(By.xpath("//div[text()='1']"));
        oneBtn.click();
        Thread.sleep(2000);
        WebElement fiveBtn = driver.findElement(By.xpath("//div[text()='5']"));
        fiveBtn.click();
        WebElement minusBtn = driver.findElement(By.cssSelector("div[aria-label='minus']"));
        minusBtn.click();
        Thread.sleep(2000);

        WebElement eightBtn = driver.findElement(By.xpath("//div[text()='8']"));
        eightBtn.click();
        Thread.sleep(2000);
        equals.click();
        Thread.sleep(2000);
        WebElement seven = driver.findElement(By.xpath("//span[text()='7']"));

        String sub = "7";
        if (sub.equals(seven.getText())) {
            System.out.println("Displayed subtraction is correct");
        } else {
            System.err.println("Displayed subtraction is not correct: " + seven.getText());
        }


    }

    public void testCase03_Verify_The_AllClear_AC_ButtonAndMultiplicationOperation() throws InterruptedException {

        System.out.println("Start Test case: testCase03");
        driver.get("https://www.google.com");
        WebElement searchBar = driver.findElement(By.id("APjFqb"));
        searchBar.sendKeys("calculator");
        searchBar.submit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@id='cwos'])[1]")));

        // Performing a multiplication operation, 10 x 3 = 30

        WebElement one1Btn = driver.findElement(By.xpath("//div[text()='1']"));
        one1Btn.click();
        Thread.sleep(2000);
        WebElement zero = driver.findElement(By.xpath("//div[text()='0']"));
        zero.click();
        WebElement multiply = driver.findElement(By.cssSelector("div[aria-label='multiply']"));
        multiply.click();
        WebElement three = driver.findElement(By.xpath("//div[text()='3']"));
        three.click();
        WebElement equalsEq = driver.findElement(By.xpath("//div[text()='=']"));
        equalsEq.click();
        Thread.sleep(3000);

        String displayedResult = driver.findElement(By.xpath("//span[text()='30']")).getText();
        if ("30".equals(displayedResult)) {
            System.out.println("Pass: Displayed multiplication is correct (30).");
        } else {
            System.out.println("Fail: Displayed multiplication is not correct. Actual: " + displayedResult);
        }
        WebElement allClearBtn = driver.findElement(By.xpath("//div[text()='AC']"));
        allClearBtn.click();
        Thread.sleep(2000);

        WebElement calculatorDisplay = driver.findElement(By.xpath("(//span[@id='cwos'])[1]"));
        String displayAfterAC = calculatorDisplay.getText();
        if (!displayAfterAC.equals("0")) {
            System.err.println("Display not cleared after AC: " + displayAfterAC);
        }

    }

    public void testCase04_VerifyDivisionOperation() throws InterruptedException {

        System.out.println("Start Test case: testCase04");
        driver.get("https://www.google.com");
        WebElement searchBar = driver.findElement(By.id("APjFqb"));
        searchBar.sendKeys("calculator");
        searchBar.submit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@id='cwos'])[1]")));

        // Performing Division ===>> 20/4 = 5

        WebElement two = driver.findElement(By.xpath("//div[text()='2']"));
        two.click();
        WebElement zero0 = driver.findElement(By.xpath("//div[text()='0']"));
        zero0.click();
        Thread.sleep(2000);

        WebElement divide = driver.findElement(By.cssSelector("div[aria-label='divide']"));
        divide.click();
        WebElement four = driver.findElement(By.xpath("//div[text()='4']"));
        four.click();
        WebElement equalsEqq = driver.findElement(By.xpath("//div[text()='=']"));
        equalsEqq.click();
        Thread.sleep(3000);

        String fiveE = "5";
        WebElement sum5 = driver.findElement(By.xpath("//span[text()='5']"));

        if (!fiveE.equals(sum5)) {
            System.out.println("Displayed division is correct");
        } else {
            System.out.println("Displayed division is not correct");
        }
    }
}







