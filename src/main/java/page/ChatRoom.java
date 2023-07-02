package page;

import model.PhotoLayerAction;
import org.openqa.selenium.By;
import ru.stombie.base.LoadablePage;

import java.util.Deque;
import java.util.LinkedList;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ChatRoom extends LoadablePage {
    final Deque<PhotoLayerAction> photoLayerActionsHistory = new LinkedList<>();
    private static final By CONVERSATION_HEADER = By.xpath(".//msg-toolbar[contains(@data-tsid, 'conversation_header')]");
    private static final By FILE_UPLOAD_INPUT = By.xpath(".//input[contains(@data-tsid, 'file_upload_input')]");
    private static final By MEDIA_ATTACHMENT = By.xpath(".//img[contains(@data-tsid, 'media_attachment')]");
    private static final By UPLOAD_BAR = By.xpath(".//msg-progress[contains(@data-tsid, 'upload-bar')]");

    public ChatRoom upload(String path) {
        $(FILE_UPLOAD_INPUT).shouldBe(exist.because("Не найдено поле для загрузки файла")).sendKeys(path);
        $(MEDIA_ATTACHMENT).shouldBe(visible.because("Не найден ни один аттачмент загрузки изображения")).
            $(UPLOAD_BAR).shouldNot(exist.because("Найден бар загрузки"));
        return this;
    }

    public PhotoEditorLayer openEditorForLastUploadedImage() {
        $$(MEDIA_ATTACHMENT).last().shouldBe(visible.because("Не найден ни один загруженный аттачмент изображения"))
            .click();
        return new PhotoEditorLayer(this);
    }

    @Override
    protected void isLoaded() {
        $(CONVERSATION_HEADER).shouldBe(visible.because("Не найден заголовок комнаты чата"));
    }
}
