package page;

import model.OKUser;
import org.openqa.selenium.By;
import ru.stombie.base.LoadablePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OKAuthorizationPage extends LoadablePage {
    private static final By ENTER_BUTTON = By.xpath(".//msg-l10n[contains(@data-tsid, 'login_via_ok_button')]");
    private static final By LOGIN_INPUT = By.xpath(".//input[contains(@data-tsid, 'login_input')]");
    private static final By PASSWORD_INPUT = By.xpath(".//input[contains(@data-tsid, 'password_input')]");

    public MainPage logInWith(OKUser user) {
        $(LOGIN_INPUT).shouldBe(visible.because("Не найдено поле логина")).sendKeys(user.getLogin());
        $(PASSWORD_INPUT).shouldBe(visible.because("Не найдено поле пароля")).sendKeys(user.getPassword());
        $(ENTER_BUTTON).shouldBe(visible.because("Не найдена кнопка входа")).click();
        return new MainPage();
    }

    @Override
    protected void isLoaded() {
        $(ENTER_BUTTON).shouldBe(visible.because("Не найдена кнопка входа"));
    }
}
