import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class KinKartaTest {

WebDriver driver;

@BeforeClass
    void setUp(){
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("https://www.amazon.com");
}

@Test
    void alexaTC() throws InterruptedException {
//write Alexa and click
    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Alexa"+ Keys.ENTER);


//scroll Page
    JavascriptExecutor js=(JavascriptExecutor)driver;
    WebElement scrollDown=driver.findElement(By.xpath("//span[contains(text(),'Related searches')]"));
    js.executeScript("arguments[0].scrollIntoView();", scrollDown);
    Thread.sleep(2000);

    //click second page
    driver.findElement(By.linkText("2")).click();
    Thread.sleep(2000);
    //click 3.th item
    driver.findElement(By.xpath("//span[contains(text(),'Echo Wall Clock - see timers at a glance - require')]")).click();
    Thread.sleep(2000);
    //click add to card
    driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
    Thread.sleep(2000);


//verify added to cart
    Assert.assertTrue(driver.getPageSource().contains("Added to Cart"));





}

@AfterClass
    void tearDown() throws InterruptedException {

    Thread.sleep(5000);
    driver.quit();
}









}
