package page;

import model.OKUser;
import org.openqa.selenium.By;
import ru.stombie.base.LoadablePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OKAuthorizationPage extends LoadablePage {
    private static final By ENTER_BUTTON = By.cssSelector("[data-l='t,sign_in']");
    private static final By FIELD_EMAIL = By.id("field_email");
    private static final By FIELD_PASSWORD = By.id("field_password");

    public OKAuthorizationPage logInWith(OKUser user) {
        $(FIELD_EMAIL).shouldBe(visible.because("Не найдено поле почты")).sendKeys(user.getEmail());
        $(FIELD_PASSWORD).shouldBe(visible.because("Не найдено поле пароля")).sendKeys(user.getPassword());
        $(ENTER_BUTTON).shouldBe(visible.because("Не найдена кнопка входа")).click();
        return this;
    }

    @Override
    protected void isLoaded() {
        $(ENTER_BUTTON).shouldBe(visible.because("Не найдена кнопка входа"));
    }
}
