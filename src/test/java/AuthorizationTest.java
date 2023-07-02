import model.OKUser;
import model.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.AuthorizationPage;
import page.ProfileDrawer;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AuthorizationTest {
    private static final String BASE_URL = "http://localhost:3000";
    private static OKUser user;
    private AuthorizationPage authorizationPage;

    @BeforeAll
    public static void globalSetUp() {
        user = Users.okBot();
    }

    @BeforeEach
    public void setUp() {
        open(BASE_URL);
        webdriver().object().manage().window().maximize();
        authorizationPage = new AuthorizationPage();
    }

    @Test
    public void logInViaOK() throws IOException {

        ProfileDrawer profileDrawer = authorizationPage.logInViaOK().logInWith(user).openProfile();
        String profileName = profileDrawer.profileName();
        assertThat(profileName, equalTo(user.getName() + " " + user.getSurname()));
    }
}
