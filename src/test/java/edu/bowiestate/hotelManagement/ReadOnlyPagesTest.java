package edu.bowiestate.hotelManagement;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadOnlyPagesTest extends AbstractSeleniumTest {

    @ValueSource(strings = {"Manager"})
    @ParameterizedTest
    public void vacancyListLoads(String credType) throws InterruptedException {
        loginWithUser(credType);
        webDriver.findElement(By.id("vacancyListLink")).click();
        Thread.sleep(100);
        assert (webDriver.findElement(By.id("unoccupied-rooms-List")).isDisplayed());
        assert (webDriver.findElement(By.id("occupied-rooms-List")).isDisplayed());
    }
}
