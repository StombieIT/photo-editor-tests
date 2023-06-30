package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PhotoEditorLayer extends ScreeningPage {
    private static final By MEDIA_CROP = By.xpath(".//msg-media-crop");
    private static final By CLOSE_BUTTON = By.xpath(".//msg-button[contains(@class, 'close')]");
    private final ChatRoom intialChatRoom;

    PhotoEditorLayer(ChatRoom initialChatRoom) {
        this.intialChatRoom = initialChatRoom;
    }

    public ChatRoom close() {
        $(CLOSE_BUTTON).shouldBe(visible.because("Не найдена кнопка закрытия фоторедактора")).click();
        intialChatRoom.isLoaded();
        return intialChatRoom;
    }

    public ChatRoom escape() {
        $(BODY).shouldBe(visible.because("Не найдено тело страницы")).sendKeys(Keys.ESCAPE);
        intialChatRoom.isLoaded();
        return intialChatRoom;
    }

    @Override
    protected void isLoaded() {
        $(MEDIA_CROP).shouldBe(visible.because("Не найден фоторедактор"));
    }
}
