package edu.bowiestate.hotelManagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HomePageTest extends AbstractSeleniumTest{


    @ValueSource(strings = {"Manager","Receptionist"})
    @ParameterizedTest
    public void homeDisplaysExpectedSections(String user) {
        loginWithUser(user);
        List<WebElement> sections = webDriver.findElements(By.className("card-body"));
        assert(sections.size() == 2);
        WebElement sectionTitle = sections.get(0).findElement(By.tagName("h2"));
        assert(sectionTitle.getText().equalsIgnoreCase("Today's Reservations"));
        assert(sections.get(0).findElement(By.tagName("table")).isDisplayed());

        sectionTitle = sections.get(1).findElement(By.tagName("h2"));
        assert(sectionTitle.getText().equalsIgnoreCase("HouseKeeping Tasks"));
        assert(sections.get(1).findElement(By.tagName("table")).isDisplayed());
    }

    @Test
    public void houseKeepHomeDisplaysExpectedSections() {
        loginWithUser("HouseKeep");
        List<WebElement> sections = webDriver.findElements(By.className("card-body"));
        assert(sections.size() == 2);
        assert(sections.get(0).isDisplayed());
        assert(sections.get(1).isDisplayed());

        WebElement sectionTitle = sections.get(0).findElement(By.tagName("h2"));
        assert(sectionTitle.getText().equalsIgnoreCase("Your Open Tasks"));
        assert(sections.get(0).findElement(By.tagName("table")).isDisplayed());

        sectionTitle = sections.get(1).findElement(By.tagName("h2"));
        assert(sectionTitle.getText().equalsIgnoreCase("Unowned Tasks"));
        assert(sections.get(1).findElement(By.tagName("table")).isDisplayed());
    }
}
