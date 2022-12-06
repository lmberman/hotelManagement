package edu.bowiestate.hotelManagement;

import edu.bowiestate.hotelManagement.reservation.Reservation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

@SpringBootTest
public class ReservationTest extends AbstractSeleniumTest {


    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void checkoutReservationFromHomePage(String credType) {
        loginWithUser(credType);
        performActionOnCurrentDayReservation("checkout");
    }

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void checkInReservationFromHomePage(String credType) {
        loginWithUser(credType);
        performActionOnCurrentDayReservation("checkIn");
    }

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void cancelReservationFromHomePage(String credType) {
        loginWithUser(credType);
        performActionOnCurrentDayReservation("cancel");
    }

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void cancelReservationFromUpcomingTab(String credType) {
        loginWithUser(credType);
        webDriver.findElement(By.id("upcomingReservationsLink")).click();
        performActionOnFutureReservation("cancel");
    }

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void checkoutReservationFromCurrentReservationsTab(String credType) {
        loginWithUser(credType);
        webDriver.findElement(By.id("currentReservationsLink")).click();
        performActionOnCurrentDayReservation("checkout");
    }

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void checkInReservationFromCurrentReservationsTab(String credType) {
        loginWithUser(credType);
        webDriver.findElement(By.id("currentReservationsLink")).click();
        performActionOnCurrentDayReservation("checkIn");
    }

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void updateReservationFromHomePage(String credType) {
        loginWithUser(credType);
        performActionOnCurrentDayReservation("update");
    }

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void updateReservationFromCurrentReservationsTab(String credType) {
        loginWithUser(credType);
        webDriver.findElement(By.id("currentReservationsLink")).click();
        performActionOnCurrentDayReservation("update");
    }

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void updateReservationFromUpcomingTab(String credType) {
        loginWithUser(credType);
        webDriver.findElement(By.id("upcomingReservationsLink")).click();
        performActionOnFutureReservation("update");
    }

    private void performActionOnCurrentDayReservation(String action) {
        performActionOnReservation(action, "Current_Day_Reservations");
    }

    private void performActionOnFutureReservation(String action) {
        performActionOnReservation(action, "upcoming_reservations");
    }

    private void performActionOnReservation(String action, String table) {
        List<WebElement> reservations = getReservations(table);
        for (WebElement reservation : reservations) {
            List<WebElement> reservationData = reservation.findElements(By.tagName("td"));
            WebElement status = reservationData.get(7);
            Reservation.ReservationStatus status1 = Reservation.ReservationStatus.valueOf(status.getText());
            String reservationStartDate = reservationData.get(5).getText();
            String reservationEndDate = reservationData.get(6).getText();
            if (action.equalsIgnoreCase("checkout") && status1 == Reservation.ReservationStatus.IN_PROGRESS) {
                String confirmationNum = getConfirmationNumberAndClickActionButton(action, reservationData);
                assertReservationsDoNotContainConfirmationNum(confirmationNum, table);
                break;
            } else if (action.equalsIgnoreCase("checkIn") && status1 == Reservation.ReservationStatus.OPEN) {
                String confirmationNum = getConfirmationNumberAndClickActionButton(action, reservationData);
                List<WebElement> reservations2 = getReservations(table);
                for (WebElement reservation2 : reservations2) {
                    List<WebElement> reservationData2 = reservation2.findElements(By.tagName("td"));
                    if (reservationData2.get(0).getText().equalsIgnoreCase(confirmationNum)) {
                        assert (reservationData2.get(7).getText().equalsIgnoreCase(Reservation.ReservationStatus.IN_PROGRESS.name()));
                        break;
                    }
                }
                break;
            } else if (action.equalsIgnoreCase("cancel") && status1 == Reservation.ReservationStatus.OPEN) {
                String confirmationNum = getConfirmationNumberAndClickActionButton(action, reservationData);
                assertReservationsDoNotContainConfirmationNum(confirmationNum, table);
                break;
            } else if (action.equalsIgnoreCase("update")) {
                if ((table.equalsIgnoreCase("Current_Day_Reservations") &&
                        status1 == Reservation.ReservationStatus.IN_PROGRESS) ||
                        (table.equalsIgnoreCase("upcoming_reservations") &&
                                status1 == Reservation.ReservationStatus.OPEN)) {
                    String confirmationNum = getConfirmationNumberAndClickActionButton(action, reservationData);
                    assert (webDriver
                            .getCurrentUrl()
                            .equalsIgnoreCase("http://localhost:9000/reservation/" + confirmationNum + "/update"));
                    WebElement updateForm = webDriver.findElement(By.id("reservationDetails")).findElement(By.tagName("form"));
                    WebElement dataBody = updateForm.findElement(By.className("card-body"));
                    WebElement roomNumSelect = dataBody.findElement(By.id("roomNum"));
                    List<WebElement> roomOptions = roomNumSelect.findElements(By.tagName("option"));
                    WebElement startDateSelect = dataBody.findElement(By.id("startDate"));
                    WebElement endDateSelect = dataBody.findElement(By.id("endDate"));
                    WebElement updateButton = updateForm.findElement(By.tagName("button"));

                    roomOptions.get(roomOptions.size() - 1).click();
                    startDateSelect.sendKeys(reservationStartDate);
                    endDateSelect.sendKeys(reservationEndDate);
                    updateButton.click();
                    webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    assert (webDriver.findElement(By.id("updateSuccessMessage")).isDisplayed());

                    break;
                }
                break;
            }

        }
    }

    private List<WebElement> getReservations(String tableName) {
        WebElement currentDayReservations = webDriver.findElement(By.id(tableName));
        WebElement reservationsTable = currentDayReservations.findElement(By.tagName("table")).findElement(By.tagName("tbody"));
        List<WebElement> reservations = reservationsTable.findElements(By.tagName("tr"));
        return reservations;
    }

    private String getConfirmationNumberAndClickActionButton(String action, List<WebElement> reservationData) {
        String confirmationNum = reservationData.get(0).getText();
        WebElement actions = reservationData.get(8);
        WebElement link = actions.findElement(By.id(action));
        link.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return confirmationNum;
    }

    private void assertReservationsDoNotContainConfirmationNum(String confirmationNum, String tableName) {
        List<WebElement> reservations2 = getReservations(tableName);
        for (WebElement reservation2 : reservations2) {
            List<WebElement> reservationData2 = reservation2.findElements(By.tagName("td"));
            assert (!reservationData2.get(0).getText().equalsIgnoreCase(confirmationNum));
        }
    }

}
