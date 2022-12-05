package edu.bowiestate.hotelManagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class NavigationBarTest extends AbstractSeleniumTest{

    @ValueSource(strings = {"Manager","Receptionist","HouseKeep"})
    @ParameterizedTest
    public void navBarContainsExpectedTabs(String user) {
        loginWithUser(user);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement navBarTabList = webDriver.findElement(By.tagName("ul"));
        List<WebElement> tabs = navBarTabList.findElements(By.tagName("li"));
        Set<String> expectedTabs = getExpectedTabsForUser(user);
        assert(tabs.size() == expectedTabs.size());
        for(int i=0; i<expectedTabs.size(); i++) {
            WebElement tab = tabs.get(i);
            WebElement tabLink = tab.findElement(By.className("nav-link"));
            assert(expectedTabs.contains(tabLink.getText()));
        }
    }

}
