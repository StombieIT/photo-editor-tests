package page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.stombie.base.LoadablePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends LoadablePage {
    private static final By SETTINGS_BUTTON = By.xpath(".//msg-button[contains(@data-tsid, 'msg_settings_button')]");
    private static final By CONVERSATION_LIST = By.xpath(".//*[contains(@data-tsid, 'conversation_list')]");

    public ProfileDrawer openProfile() {
        $(SETTINGS_BUTTON).shouldBe(visible.because("Не найдена кнопка профиля")).click();
        return new ProfileDrawer(this);
    }

    @Override
    protected void isLoaded() {
        $(CONVERSATION_LIST).shouldBe(visible.because("Не найден список чатов"));
    }
}
