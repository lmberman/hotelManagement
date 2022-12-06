package edu.bowiestate.hotelManagement;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadOnlyPagesTest extends AbstractSeleniumTest {

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void vacancyListLoads(String credType) {
        loginWithUser(credType);
        assert (webDriver.findElement(By.id("unoccupied-rooms-list")).isDisplayed());
        assert (webDriver.findElement(By.id("occupied-rooms-list")).isDisplayed());
    }
}
