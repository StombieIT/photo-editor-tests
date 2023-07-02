package page;

import model.Coordinate;
import model.PhotoLayerAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.StringJoiner;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.webdriver;

public class PhotoEditorLayer extends ScreeningPage {
    private static final Actions SELENIUM_ACTIONS = new Actions(webdriver().object());
    private static final int TRANSITION_TIME = 600;
    private static final int VERTEX_OFFSET = 20;
    private static final By MEDIA_CROP = By.xpath(".//msg-media-crop");
    private static final By EDITING_IMAGE = By.xpath(".//*[contains(@data-tsid, 'editing-image')]");
    private static final By CROP_BUTTON = By.xpath(".//msg-button[contains(@data-tsid, 'crop')]");
    private static final By SAVE_BUTTON = By.xpath(".//msg-button[contains(@data-tsid, 'photo_layer_save_button')]");
    private static final By ROTATE_BUTTON = By.xpath(".//msg-button[contains(@data-tsid, 'rotate')]");
    private static final By FLIP_BUTTON = By.xpath(".//msg-button[contains(@data-tsid, 'flip')]");
    private static final By CLOSE_BUTTON = By.xpath(".//msg-button[contains(@class, 'close')]");
    private static final By UNDO_BUTTON = By.xpath(".//msg-button[contains(@class, 'undo')]");
    private static final By DONE_BUTTON = By.xpath(".//msg-button[contains(@data-tsid, 'photo_layer_done_button')]");
    private static final By CROPPER = By.xpath(".//*[contains(@data-tsid, 'cropper')]");
    private static final By VERTEX_NW = By.xpath(".//*[contains(@data-id, 'nw')]");
    private static final By VERTEX_NE = By.xpath(".//*[contains(@data-id, 'ne')]");
    private static final By VERTEX_SE = By.xpath(".//*[contains(@data-id, 'se')]");
    private static final By VERTEX_SW = By.xpath(".//*[contains(@data-id, 'sw')]");
    private static final String FILENAME_DELIMITER = "_";
    private static final String FILE_EXTENSION = ".png";
    private final ChatRoom intialChatRoom;

    PhotoEditorLayer(ChatRoom initialChatRoom) {
        this.intialChatRoom = initialChatRoom;
    }

    public ChatRoom close() {
        $(CLOSE_BUTTON).shouldBe(visible.because("Не найдена кнопка закрытия фоторедактора")).click();
        intialChatRoom.isLoaded();
        intialChatRoom.photoLayerActionsHistory.clear();
        return intialChatRoom;
    }

    public ChatRoom escape() {
        $(BODY).shouldBe(visible.because("Не найдено тело страницы")).sendKeys(Keys.ESCAPE);
        intialChatRoom.isLoaded();
        return intialChatRoom;
    }

    public PhotoEditorLayer crop() {
        $(CROP_BUTTON).shouldBe(visible.because("Не найдена кнопка обрезки фото")).click();
        sleep(TRANSITION_TIME);
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.CROP);
        return this;
    }

    public PhotoEditorLayer crop(int count) {
        return repeat(count, this::crop);
    }

    public PhotoEditorLayer rotate() {
        $(ROTATE_BUTTON).shouldBe(visible.because("Не найдена кнопка поворота фото")).click();
        sleep(TRANSITION_TIME);
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.ROTATE);
        return this;
    }

    public PhotoEditorLayer rotate(int count) {
        return repeat(count, this::rotate);
    }

    public PhotoEditorLayer flip() {
        $(FLIP_BUTTON).shouldBe(visible.because("Не найдена кнопка флипа фото")).click();
        sleep(TRANSITION_TIME);
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.FLIP);
        return this;
    }

    public PhotoEditorLayer flip(int count) {
        return repeat(count, this::flip);
    }

    public PhotoEditorLayer makePhotoScreenshot() throws IOException {
        StringJoiner photoFilenameJoiner = new StringJoiner(FILENAME_DELIMITER);
        intialChatRoom.photoLayerActionsHistory.stream().map(PhotoLayerAction::getHint).forEach(photoFilenameJoiner::add);
        screenShotTo(
            $(MEDIA_CROP).shouldBe(visible.because("Не найдено редактируемое фото")),
            photoFilenameJoiner.toString() + FILE_EXTENSION
        );
        return this;
    }

    public PhotoEditorLayer createCrop(Coordinate from, Coordinate to) {
        SELENIUM_ACTIONS.moveToElement($(EDITING_IMAGE).shouldBe(visible.because("Не найдено редактируемое изображение")), from.getX(), from.getY())
                .clickAndHold()
                .moveByOffset(to.getX(), to.getY())
                .perform();
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.CREATE_CROP);
        return this;
    }

    public PhotoEditorLayer moveCrop(Coordinate by) {
        SELENIUM_ACTIONS.moveToElement($(CROPPER).shouldBe(visible.because("Не найдена рамка обрезки фото")), VERTEX_OFFSET, VERTEX_OFFSET)
                .clickAndHold()
                .moveByOffset(by.getX(), by.getY())
                .perform();
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.MOVE_CROP);
        return this;
    }

    public PhotoEditorLayer resizeCropByNWVertex(Coordinate by) {
        SELENIUM_ACTIONS.moveToElement($(VERTEX_NW).shouldBe(visible.because("Не найдена левая верхняя вершина рамки обрезки фото")))
                .clickAndHold()
                .moveByOffset(by.getX(), by.getY())
                .perform();
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.RESIZE_BY_NW);
        return this;
    }

    public PhotoEditorLayer resizeCropByNEVertex(Coordinate by) {
        SELENIUM_ACTIONS.moveToElement($(VERTEX_NE).shouldBe(visible.because("Не найдена правая верхняя вершина рамки обрезки фото")))
                .clickAndHold()
                .moveByOffset(by.getX(), by.getY())
                .perform();
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.RESIZE_BY_NE);
        return this;
    }

    public PhotoEditorLayer resizeCropBySEVertex(Coordinate by) {
        SELENIUM_ACTIONS.moveToElement($(VERTEX_SE).shouldBe(visible.because("Не найдена правая нижняя вершина рамки обрезки фото")))
                .clickAndHold()
                .moveByOffset(by.getX(), by.getY())
                .perform();
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.RESIZE_BY_SE);
        return this;
    }

    public PhotoEditorLayer resizeCropBySWVertex(Coordinate by) {
        SELENIUM_ACTIONS.moveToElement($(VERTEX_SW).shouldBe(visible.because("Не найдена левая нижняя вершина рамки обрезки фото")))
                .clickAndHold()
                .moveByOffset(by.getX(), by.getY())
                .perform();
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.RESIZE_BY_SW);
        return this;
    }

    public PhotoEditorLayer checkUndo() {
        $(UNDO_BUTTON).shouldBe(visible.because("Не найдена кнопка отмены изменений"));
        return this;
    }

    public ChatRoom save() {
        $(SAVE_BUTTON).shouldBe(visible.because("Не найдена кнопка завершения редактирования")).click();
        return intialChatRoom;
    }

    public PhotoEditorLayer done() {
        $(DONE_BUTTON).shouldBe(visible.because("Не найдена кнопка сохранения изменений")).click();
        sleep(TRANSITION_TIME);
        intialChatRoom.photoLayerActionsHistory.addLast(PhotoLayerAction.DONE);
        return this;
    }

    @Override
    protected void isLoaded() {
        $(MEDIA_CROP).shouldBe(visible.because("Не найден фоторедактор"));
    }

    private PhotoEditorLayer repeat(int count, Runnable action) {
        for (int i = 0; i < count; i++) {
            action.run();
        }
        return this;
    }
}
