package page;

import org.openqa.selenium.By;
import ru.stombie.base.LoadablePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.webdriver;

public class AuthorizationPage extends LoadablePage {
    private static final By OK_BUTTON = By.xpath(".//*[contains(@data-tsid, 'login_via_ok_button')]");
    private static final By PHONE_INPUT = By.xpath(".//*[contains(@data-tsid, 'phone_input')]");

    public OKAuthorizationPage logInViaOK() {
        $(OK_BUTTON).shouldBe(visible.because("Не найдена кнопка входа через Одноклассники")).click();
        return new OKAuthorizationPage();
    }

    @Override
    protected void isLoaded() {
        $(PHONE_INPUT).shouldBe(visible.because("Не найдено главное поле входа"));
    }
}
