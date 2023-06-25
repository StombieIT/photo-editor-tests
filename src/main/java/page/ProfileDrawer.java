package page;

import org.openqa.selenium.By;
import ru.stombie.base.LoadablePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfileDrawer extends LoadablePage {
    private static final By PROFILE_DRAWER = By.xpath(".//msg-drawer[contains(@data-tsid, 'profile_drawer')]");
    private static final By PROFILE_NAME = By.xpath(".//msg-parsed-text[contains(@data-tsid, 'profile_name')]");
    private static final By OVERLAY = By.xpath(".//*[contains(@class, 'drawer-overlay')]");
    private final MainPage initialPage;

    public ProfileDrawer(MainPage initialPage) {
        this.initialPage = initialPage;
    }

    public MainPage close() {
        $(OVERLAY).shouldBe(visible.because("Не найден оверлей профиля")).click();
        return initialPage;
    }

    public String profileName() {
        return $(PROFILE_NAME).shouldBe(visible.because("Не найдено имя профиля")).text();
    }

    @Override
    protected void isLoaded() {
        $(PROFILE_DRAWER).shouldBe(visible.because("Не найдена панель профиля"));
    }
}
