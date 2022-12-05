package edu.bowiestate.hotelManagement;

import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReservationTest extends AbstractSeleniumTest {

    @Test
    public void checkoutReservationFromHomePage(){
        loginWithUser("Receptionist");
    }

    @Test
    public void checkInReservationFromHomePage() {
        loginWithUser("Receptionist");
    }

    @Test
    public void updateReservationFromHomePage() {
        loginWithUser("Receptionist");
    }

    @Test
    public void checkoutReservationFromCurrentReservationsTab(){
        loginWithUser("Receptionist");
        webDriver.findElement(By.id("currentReservationsLink")).click();
    }

    @Test
    public void checkInReservationFromCurrentReservationsTab() {
        loginWithUser("Receptionist");
        webDriver.findElement(By.id("currentReservationsLink")).click();
    }

    @Test
    public void updateReservationFromCurrentReservationsTab() {
        loginWithUser("Receptionist");
        webDriver.findElement(By.id("currentReservationsLink")).click();
    }

    @Test
    public void updateReservationFromUpcomingTab() {
        loginWithUser("Receptionist");
        webDriver.findElement(By.id("upcomingReservationsLink")).click();
    }

    @Test
    public void cancelReservationFromUpcomingTab() {
        loginWithUser("Receptionist");
        webDriver.findElement(By.id("upcomingReservationsLink")).click();
    }


}
