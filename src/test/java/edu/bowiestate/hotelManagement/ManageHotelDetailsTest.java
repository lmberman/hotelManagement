package edu.bowiestate.hotelManagement;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

@SpringBootTest
public class ManageHotelDetailsTest extends AbstractSeleniumTest {

    @Test
    public void updateHotelDetails() throws InterruptedException {
        loginWithUser("Manager");
        webDriver.findElement(By.id("hotelDetailsLink")).click();
        WebElement webDetailsForm = webDriver.findElement(By.id("hotelDetails"))
                .findElement(By.tagName("form"));
        List<WebElement> cardBodies = webDetailsForm.findElements(By.className("card-body"));
        assert(cardBodies.size() == 3);
        WebElement startDaySelect = cardBodies.get(0).findElement(By.id("startDayOfOperation"));
        WebElement endDaySelect = cardBodies.get(0).findElement(By.id("endDayOfOperation"));
        WebElement startHourSelect = cardBodies.get(1).findElement(By.id("startHourOfOperation"));
        WebElement endHourSelect = cardBodies.get(1).findElement(By.id("endHourOfOperation"));
        WebElement singleRoomPrice = cardBodies.get(2).findElement(By.id("singleRoomPrice"));
        WebElement doubleRoomPrice = cardBodies.get(2).findElement(By.id("doubleRoomPrice"));
        WebElement suiteRoomPrice = cardBodies.get(2).findElement(By.id("suiteRoomPrice"));
        WebElement submitButton = webDetailsForm.findElement(By.tagName("button"));

        startDaySelect.findElements(By.tagName("option")).get(1).click();
        endDaySelect.findElements(By.tagName("option")).get(1).click();
        startHourSelect.findElements(By.tagName("option")).get(1).click();
        endHourSelect.findElements(By.tagName("option")).get(1).click();
        singleRoomPrice.sendKeys("30.00");
        doubleRoomPrice.sendKeys("50.00");
        suiteRoomPrice.sendKeys("80.00");

        WebDriverWait wait2 = new WebDriverWait(webDriver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .elementToBeClickable(webDetailsForm.findElement(By.tagName("button"))));

        JavascriptExecutor jse2 = (JavascriptExecutor)webDriver;
        jse2.executeScript("arguments[0].scrollIntoView()", submitButton);

        Thread.sleep(2000);
        submitButton.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        assert(webDriver.findElement(By.id("updateSuccessMessage")).isDisplayed());
    }
}
