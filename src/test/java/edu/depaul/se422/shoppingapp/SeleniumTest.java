package edu.depaul.se422.shoppingapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import static org.assertj.core.api.Assertions.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    //This is a class containing my selenium test...
    //In order to run, first launch the local host
    //Then, simply press Play on the below main function.

    public static void main(String args[]){
        WebDriver driver = initializeDriver();
        navigateToSite(driver);
        findAndFillFields(driver);
        verifyAllFields(driver);
        findAllButtons(driver);
        clickAllButtons(driver);
        driver.quit();
    }

    public static WebDriver initializeDriver(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static void navigateToSite(WebDriver driver){
        driver.navigate().to("http://localhost:8080/?#");
    }

    public static void findAndFillFields(WebDriver driver){
        //This finds all web element text fields.  Asserts that they were found.
        //Then we send keys to each respective field.
        WebElement customerName = driver.findElement(By.id("customer-name"));
        assertThat(customerName).isNotNull();
        WebElement state = driver.findElement(By.id("state"));
        assertThat(state).isNotNull();
        WebElement shipping = driver.findElement(By.id("shipping"));
        assertThat(shipping).isNotNull();
        WebElement name = driver.findElement(By.id("name"));
        assertThat(name).isNotNull();
        WebElement unitPrice = driver.findElement(By.id("unit_price"));
        assertThat(unitPrice).isNotNull();
        WebElement quantity = driver.findElement(By.id("quantity"));
        assertThat(quantity).isNotNull();

        customerName.sendKeys("Sal");
        state.sendKeys("OH");
        shipping.sendKeys("STANDARD");
        name.sendKeys("Product Name");
        unitPrice.sendKeys("10.99");
        quantity.sendKeys("3");

    }

    public static void verifyAllFields(WebDriver driver){
        //This verifies that the text in each field is present (i.e. keys have been sent)
        WebElement customerName = driver.findElement(By.id("customer-name"));
        WebElement state = driver.findElement(By.id("state"));
        WebElement shipping = driver.findElement(By.id("shipping"));
        WebElement name = driver.findElement(By.id("name"));
        WebElement unitPrice = driver.findElement(By.id("unit_price"));
        WebElement quantity = driver.findElement(By.id("quantity"));

        assertThat(customerName.getAttribute("value")).isEqualTo("Sal");
        assertThat(state.getAttribute("value")).isEqualTo("OH");
        assertThat(shipping.getAttribute("value")).isEqualTo("STANDARD");
        assertThat(name.getAttribute("value")).isEqualTo("Product Name");
        assertThat(unitPrice.getAttribute("value")).isEqualTo("10.99");
        assertThat(quantity.getAttribute("value")).isEqualTo("3");
    }

    public static void findAllButtons(WebDriver driver){
        //This verifies that all buttons are present.
        WebElement addItemButton = driver.findElement(By.id("add-item-btn"));
        assertThat(addItemButton).isNotNull();
        WebElement currentTotalButton = driver.findElement(By.id("get-price-btn"));
        assertThat(currentTotalButton).isNotNull();
        WebElement checkoutButton = driver.findElement(By.id("checkout-btn"));
        assertThat(checkoutButton).isNotNull();
    }

    public static void clickAllButtons(WebDriver driver){
        //Clicks all buttons, adding item and getting total and checking out.
        WebElement addItemButton = driver.findElement(By.id("add-item-btn"));
        WebElement currentTotalButton = driver.findElement(By.id("get-price-btn"));
        WebElement checkoutButton = driver.findElement(By.id("checkout-btn"));

        addItemButton.click();
        currentTotalButton.click();
        checkoutButton.click();
    }

}
