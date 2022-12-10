package edu.bowiestate.hotelManagement;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractSeleniumTest {

    @Autowired
    protected WebDriver webDriver;

    private Map<String, Credentials> credentials;

    private Map<String, String> expectedHomeScreenName;

    protected String managerUser = "Manager";
    protected String receptionist = "Receptionist";
    protected String houseKeepUser = "HouseKeep";

    protected Set<String> managerTabs;
    protected Set<String> receptionistTabs;
    protected Set<String> houseKeepTabs;

    @BeforeEach
    public void beforeTests() {
        credentials = new HashMap<>();
        credentials.put("EmptyCredentials", new Credentials("", ""));
        credentials.put("MissingPassword", new Credentials("something", ""));
        credentials.put("InvalidCredentials", new Credentials("something", "password"));
        credentials.put("Manager", new Credentials("10012345", "PASS"));
        credentials.put("Receptionist", new Credentials("10012346", "PASS"));
        credentials.put("HouseKeep", new Credentials("10012347", "PASS"));

        expectedHomeScreenName = new HashMap<>();
        expectedHomeScreenName.put("Manager", "Management Home");
        expectedHomeScreenName.put("Receptionist", "Reception Home");
        expectedHomeScreenName.put("HouseKeep", "House Keeping Home");

        managerTabs = new HashSet<>() {
            {
                add("Home");
                add("Manage Hotel Details");
                add("Vacancy List");
                add("Current Reservations");
                add("Upcoming Reservations");
                add("House-Keeping Tasks");
                add("Add Employee");
                add("Logout");
            }};
        receptionistTabs = new HashSet<>() {
            {
                add("Home");
                add("Current Reservations");
                add("Upcoming Reservations");
                add("House-Keeping Tasks");
                add("Logout");
            }};
        houseKeepTabs = new HashSet<>() {
            {
                add("Home");
                add("Logout");
            }};
    }
    @After
    public void afterTests(){
        webDriver.quit();
    }

    private void login(Credentials credentials) {
        webDriver.get("http://localhost:9000/login");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement usernameInput = webDriver.findElement(By.id("username"));
        usernameInput.sendKeys(credentials.getUsername());
        WebElement passwordInput = webDriver.findElement(By.id("password"));
        passwordInput.sendKeys(credentials.getPassword());
        WebElement signInButton = webDriver.findElement(By.id("SignIn"));
        signInButton.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    protected void loginWithUser(String usertype) {
        login(credentials.get(usertype));
        String expectedHomePage = expectedHomeScreenName.get(usertype);
        if(expectedHomePage == null || expectedHomePage.isEmpty()) {
            WebElement errorMessage = webDriver.findElement(By.id("invalidCredentialsError"));
            assert(errorMessage.isDisplayed());
        } else {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            String landingPage = webDriver.getCurrentUrl();
            WebElement title = webDriver.findElement(By.className("card-title"));
            assert(landingPage.equals("http://localhost:9000/home"));
            assert(title.getText().equals(expectedHomePage));
        }
    }

    public Set<String> getExpectedTabsForUser(String user) {
        if(user.equalsIgnoreCase("Manager")) {
            return managerTabs;
        } else if (user.equalsIgnoreCase("Receptionist")) {
            return receptionistTabs;
        } else if (user.equalsIgnoreCase("HouseKeep")){
            return houseKeepTabs;
        }
        return null;
    }

    @AfterEach
    public void after() {
        webDriver.close();
    }

}
