package page;

import model.OKUser;
import org.openqa.selenium.By;
import ru.stombie.base.LoadablePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class OKAuthorizationPage extends LoadablePage {
    private static final By ENTER_BUTTON = By.xpath(".//*[contains(@value, 'Войти')]");
    private static final By FIELD_EMAIL = By.id("field_email");
    private static final By FIELD_PASSWORD = By.id("field_password");
    private static final By ACCEPT_BUTTON = By.xpath(".//*[contains(@name, 'button_accept_request')]");

    public MainPage logInWith(OKUser user) {
        $(FIELD_EMAIL).shouldBe(visible.because("Не найдено поле почты")).sendKeys(user.getEmail());
        $(FIELD_PASSWORD).shouldBe(visible.because("Не найдено поле пароля")).sendKeys(user.getPassword());
        $(ENTER_BUTTON).shouldBe(visible.because("Не найдена кнопка входа")).click();
        $(ACCEPT_BUTTON).shouldBe(visible.because("Не найдена кнопка принятия разрешения входа")).click();
        switchTo().window(0);
        return new MainPage();
    }

    @Override
    protected void isLoaded() {
        $(ENTER_BUTTON).shouldBe(visible.because("Не найдена кнопка входа"));
    }
}
