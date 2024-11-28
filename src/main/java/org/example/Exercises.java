package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Exercises {
    private WebDriver driver;
    private String baseURL;

   @Before
    public void setUp() {
        baseURL = "https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_login_page/9a83bda125cd7398f9f482a3d6d45ea4/static/attachments/reference_page.html";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL);
    }

   @Test
   public void invalidCredentials(){
       WebElement email=driver.findElement(By.id("email-input"));
       email.sendKeys("test@email.com");
       WebElement pass=driver.findElement(By.id("password-input"));
       pass.sendKeys("password");
       WebElement logIn=driver.findElement(By.id("login-button"));
       logIn.click();

       WebElement error=driver.findElement(By.xpath("//div[contains(@class,'error')]"));
       //o si no puedo usar:
       // WebElement error=driver.findElement(By.className("error"));
       Assert.assertEquals("You shall not pass! Arr!",error.getText());
   }

   @Test
   public void blankFields(){
       String msgs[]= {"Email is required","Password is required"};
       WebElement email=driver.findElement(By.id("email-input"));
       WebElement pass=driver.findElement(By.id("password-input"));
       WebElement logIn=driver.findElement(By.id("login-button"));
       logIn.click();

       List<WebElement> validations=driver.findElements(By.className("validation"));
       //for (WebElement msg: validations){
       Assert.assertEquals(msgs[0],validations.get(0).getText());
       Assert.assertEquals(msgs[1],validations.get(1).getText());
   }

   @Test
   public void invalidEmail(){
       WebElement email=driver.findElement(By.id("email-input"));
       email.sendKeys("pacmail.com.ar");
       //Pattern pattern = java.util.regex.Pattern.compile("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$");
        /*
        ^ - The beginning of the string.
        ( - Start of a capturing group.
        [a-zA-Z0-9._%-]+ - Matches one or more of the following characters: letters (both uppercase and lowercase), numbers, periods, underscores, percent signs, and hyphens.
        @ - Matches the “@” symbol.
        [a-zA-Z0-9.-]+ - Matches one or more of the following characters: letters (both uppercase and lowercase), numbers, periods, and hyphens.
        \. - Matches a literal period character.
        [a-zA-Z]{2,} - Matches two or more letters (both uppercase and lowercase).
        ) - End of the capturing group.
        $ - The end of the string.*/


       WebElement logIn=driver.findElement(By.id("login-button"));
       logIn.click();
       //Matcher matcher = pattern.matcher(email.getText());
       //Assert.assertTrue(matcher.matches());
       WebElement validation=driver.findElement(By.className("validation")); //me agarra el primer mensaje
       Assert.assertEquals("Enter a valid email",validation.getText());


   }
    @Test
    public void TabAndEnter(){
        try {
            Actions action=new Actions(driver);
            action.sendKeys(Keys.TAB).perform();

            WebElement email = driver.findElement(By.id("email-input"));
            Assert.assertEquals(email,driver.switchTo().activeElement());
            email.sendKeys(Keys.TAB);

            WebElement pass = driver.findElement(By.id("password-input"));
            Assert.assertEquals(pass,driver.switchTo().activeElement());
            pass.sendKeys(Keys.TAB);

            WebElement logIn = driver.findElement(By.id("login-button"));
            logIn.sendKeys(Keys.ENTER);

            /*SI USO EXPLICIT WAIT EN LUGAR DE IMPLICIT WAIT:
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("validation")));*/

            WebElement msg= driver.findElement(By.className("validation"));
            Assert.assertTrue(msg.isDisplayed());
        }catch(Exception e){
            Assert.fail("Element not found");
        }
    }

   @After
    public void tearDown() {
        driver.quit();
    }
}
