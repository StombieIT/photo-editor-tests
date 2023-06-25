import model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.AuthorizationPage;
import page.ProfileDrawer;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AuthorizationTest {
    private static final String BASE_URL = "https://tamtam.chat";
    private AuthorizationPage authorizationPage;

    @BeforeEach
    public void setUp() {
        open(BASE_URL);
        authorizationPage = new AuthorizationPage();
    }

    @Test
    public void logInViaOK() {
        ProfileDrawer profileDrawer = authorizationPage.logInViaOK().logInWith(Users.okBot()).openProfile();
        String profileName = profileDrawer.profileName();
        String botEmail = Users.okBot().getEmail();
        assertThat(profileName, equalTo(botEmail + " " + botEmail));
    }
}
