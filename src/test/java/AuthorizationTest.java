import model.OKUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.AuthorizationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class AuthorizationTest {
    private static final String BASE_URL = "https://tamtam.chat";

    @BeforeEach
    public void setUp() {
        open(BASE_URL);
    }

    @Test
    public void logInViaOK() {
        new AuthorizationPage().logInViaOK().logInWith(new OKUser("kobyl17", "testQA_1"));
        sleep(100_000_000_000L);
    }
}
