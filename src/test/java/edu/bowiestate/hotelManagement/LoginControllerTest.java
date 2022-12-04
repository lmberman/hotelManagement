package edu.bowiestate.hotelManagement;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginControllerTest extends AbstractSeleniumTest {

    @ValueSource(strings = {"EmptyCredentials","MissingPassword","InvalidCredentials",
            "Manager","Receptionist","HouseKeep"})
    @ParameterizedTest
    public void attempt_to_login(String credType) throws InterruptedException {
       loginWithUser(credType);
    }

//    @Test
//    public void attempt_to_login_missingPassword_creds() throws InterruptedException {
//        webDriver.get("http://localhost:9000/login");
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
//        WebElement usernameInput = webDriver.findElement(By.id("username"));
//        usernameInput.sendKeys("something");
//        WebElement signInButton = webDriver.findElement(By.id("SignIn"));
//        signInButton.click();
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        WebElement errorMessage = webDriver.findElement(By.id("invalidCredentialsError"));
//        assert(errorMessage.isDisplayed());
//    }
//
//    @Test
//    public void attempt_to_login_invalid_creds() throws InterruptedException {
//        webDriver.get("http://localhost:9000/login");
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
//        WebElement usernameInput = webDriver.findElement(By.id("username"));
//        usernameInput.sendKeys("something");
//        WebElement passwordInput = webDriver.findElement(By.id("password"));
//        usernameInput.sendKeys("password");
//        WebElement signInButton = webDriver.findElement(By.id("SignIn"));
//        signInButton.click();
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        WebElement errorMessage = webDriver.findElement(By.id("invalidCredentialsError"));
//        assert(errorMessage.isDisplayed());
//    }
//
//    @CsvSource(value = {"10012345,Management Home","10012346,Reception Home","10012347,House Keeping Home"})
//    @ParameterizedTest
//    public void attempt_to_login_manager_creds(String employeeId, String titleName) throws InterruptedException {
//        webDriver.get("http://localhost:9000/login");
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
//        WebElement usernameInput = webDriver.findElement(By.id("username"));
//        usernameInput.sendKeys(employeeId);
//        WebElement passwordInput = webDriver.findElement(By.id("password"));
//        passwordInput.sendKeys("PASS");
//        WebElement signInButton = webDriver.findElement(By.id("SignIn"));
//        signInButton.click();
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
//        String landingPage = webDriver.getCurrentUrl();
//        WebElement title = webDriver.findElement(By.className("card-title"));
//        assert(landingPage.equals("http://localhost:9000/home"));
//        assert(title.getText().equals(titleName));
//    }
}
