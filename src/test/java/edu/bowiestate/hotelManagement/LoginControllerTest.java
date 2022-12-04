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
}
