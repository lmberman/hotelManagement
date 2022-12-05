package edu.bowiestate.hotelManagement;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginControllerTest extends AbstractSeleniumTest {

    @ValueSource(strings = {"EmptyCredentials", "MissingPassword", "InvalidCredentials",
            "Manager", "Receptionist", "HouseKeep"})
    @ParameterizedTest
    public void attempt_to_login(String credType) throws InterruptedException {
        loginWithUser(credType);
    }

    @ValueSource(strings = {"Manager", "Receptionist", "HouseKeep"})
    @ParameterizedTest
    public void logout(String credType) {
        loginWithUser(credType);
        WebElement logoutLink = webDriver.findElement(By.id("logoutLink"));
        logoutLink.click();
        assert(webDriver.getCurrentUrl().equalsIgnoreCase("http://localhost:9000/login"));
    }
}
