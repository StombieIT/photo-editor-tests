package page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.stombie.base.LoadablePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends LoadablePage {
    private static final By FOLDER_PANEL = By.xpath(".//msg-folder-panel[contains(@data-tsid, 'folders_tab')]");
    private static final By AVATAR = By.xpath(".//msg-avatar");
    private static final By CONVERSATION_LIST = By.xpath(".//msg-chats-list[contains(@data-tsid, 'conversation_list')]");
    private static final By CONVERSATION_ITEM = By.xpath(".//msg-chats-list-item[contains(@data-tsid, 'conversation_item')]");

    public ProfileDrawer openProfile() {
        $(FOLDER_PANEL).shouldBe(visible.because("Не найдена панель папок"))
            .$(AVATAR).shouldBe(visible.because("Не найден аватар")).click();
        return new ProfileDrawer(this);
    }

    public ChatRoom openFirstChatRoom() {
        $(CONVERSATION_ITEM).shouldBe(visible.because("Не найден ни один чат")).click();
        return new ChatRoom();
    }

    @Override
    protected void isLoaded() {
        $(CONVERSATION_LIST).shouldBe(visible.because("Не найден список чатов"));
    }
}
