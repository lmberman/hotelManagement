package edu.bowiestate.hotelManagement;

import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
public class AddNewEmployeeTest extends AbstractSeleniumTest {

    @Test
    public void addNewEmployee() throws InterruptedException {
        loginWithUser("Manager");
        webDriver.findElement(By.id("newEmployeeLink")).click();
        WebElement newEmployeeForm = webDriver.findElement(By.className("card"))
                .findElements(By.className("card-body"))
                .get(1)
                .findElement(By.tagName("form"));
        WebElement firstnameInput = newEmployeeForm.findElement(By.id("firstname"));
        WebElement middleInitialInput = newEmployeeForm.findElement(By.id("middle"));
        WebElement lastnameInput = newEmployeeForm.findElement(By.id("lastname"));
        WebElement addressInput = newEmployeeForm.findElement(By.id("autocomplete"));
        WebElement phoneNum = newEmployeeForm.findElement(By.id("telephone"));

        firstnameInput.sendKeys("Bob");
        middleInitialInput.sendKeys("M");
        lastnameInput.sendKeys("Ross");
        addressInput.sendKeys("4777 Marlboro Pike, Capitol Heights, MD 20743");
        Thread.sleep(600);
        new WebDriverWait(webDriver, Duration.ofMinutes(2))
                .until(ExpectedConditions.elementToBeClickable(addressInput)).click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        Thread.sleep(600);
        addressInput.click();
        Thread.sleep(600);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        addressInput.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(600);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
        addressInput.sendKeys(Keys.ENTER);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));

        Thread.sleep(6000);
        phoneNum.sendKeys("301-669-6401");

        newEmployeeForm.findElement(By.tagName("button")).click();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        assert(webDriver.getCurrentUrl().endsWith("/credentials"));

        WebElement credentialsForm = webDriver.findElement(By.className("card"))
                .findElements(By.className("card-body"))
                .get(1)
                .findElement(By.tagName("form"));
        WebElement passwordInput = credentialsForm.findElement(By.id("password"));
        WebElement employeeRoleSelect = credentialsForm.findElement(By.id("employeeRole"));
        passwordInput.sendKeys("SomethingS");
        employeeRoleSelect.findElements(By.tagName("option")).get(1).click();

        credentialsForm.findElement(By.tagName("button")).click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        assert(webDriver.getCurrentUrl().endsWith("/creation/confirm"));

    }
}
