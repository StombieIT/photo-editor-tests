import com.codeborne.selenide.ex.ElementNotFound;
import model.Coordinate;
import model.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.AuthorizationPage;
import page.ChatRoom;
import page.PhotoEditorLayer;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhotoEditorLayerTest {
    private static final int OFFSET = 70;
    private static final String BASE_URL = "http://localhost:3000";
    private static final String UPLOADING_IMAGE_PATH = "/Users/vladislav.yartsev/dev/personal/photo-editor-tests/src/test/resources/uploadingImage.jpeg";
    private static ChatRoom chatRoomWithUploadedImage;

    @BeforeAll
    public static void globalSetUp() {
        open(BASE_URL);
        webdriver().object().manage().window().maximize();
        chatRoomWithUploadedImage = new AuthorizationPage().logInViaOK().logInWith(Users.okBot())
                .openFirstChatRoom().upload(UPLOADING_IMAGE_PATH);
    }

    @Test
    public void checkOpenLayer() {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage().close();
    }

    @Test
    public void checkCloseAndEscape() {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .close()
                .openEditorForLastUploadedImage()
                .escape();
    }

    @Test
    public void checkRotate() throws IOException {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .rotate()
                .makePhotoScreenshot()
                .close();
    }

    @Test
    public void checkFlip() throws IOException {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .flip()
                .makePhotoScreenshot()
                .close();
    }

    @Test
    public void checkCrop() throws IOException {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .crop()
                .makePhotoScreenshot()
                .close();
    }

    @Test
    public void checkCreatingCrop() throws IOException {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .createCrop(Coordinate.by(15), Coordinate.by(200))
                .makePhotoScreenshot()
                .close();
    }

    @Test
    public void checkMovingCrop() throws IOException {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .crop()
                .makePhotoScreenshot()
                .moveCrop(Coordinate.by(OFFSET))
                .makePhotoScreenshot()
                .moveCrop(Coordinate.by(-OFFSET))
                .makePhotoScreenshot()
                .close();
    }

    @Test
    public void checkResizeByNWVertex() throws IOException {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .crop()
                .makePhotoScreenshot()
                .resizeCropByNWVertex(Coordinate.by(OFFSET))
                .makePhotoScreenshot()
                .resizeCropByNWVertex(Coordinate.by(-OFFSET))
                .makePhotoScreenshot()
                .close();
    }

    @Test
    public void checkResizeByNEVertex() throws IOException {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .crop()
                .makePhotoScreenshot()
                .resizeCropByNEVertex(Coordinate.by(-OFFSET, OFFSET))
                .makePhotoScreenshot()
                .resizeCropByNEVertex(Coordinate.by(OFFSET, -OFFSET))
                .makePhotoScreenshot()
                .close();
    }

    @Test
    public void checkResizeBySWVertex() throws IOException {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .crop()
                .makePhotoScreenshot()
                .resizeCropBySWVertex(Coordinate.by(OFFSET, -OFFSET))
                .makePhotoScreenshot()
                .resizeCropBySWVertex(Coordinate.by(-OFFSET, OFFSET))
                .makePhotoScreenshot()
                .close();
    }

    @Test
    public void checkResizeBySEVertex() throws IOException {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .crop()
                .makePhotoScreenshot()
                .resizeCropBySEVertex(Coordinate.by(-OFFSET))
                .makePhotoScreenshot()
                .resizeCropBySEVertex(Coordinate.by(OFFSET))
                .makePhotoScreenshot()
                .close();
    }

    @Test
    public void checkUndoAfterRotate() {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .rotate()
                .checkUndo()
                .close();
    }

    @Test
    public void checkUndoAfter4Rotates() throws IOException {
        PhotoEditorLayer photoEditorLayer = chatRoomWithUploadedImage.openEditorForLastUploadedImage().rotate(4).makePhotoScreenshot();
        assertThrows(ElementNotFound.class, photoEditorLayer::checkUndo);
        photoEditorLayer.close();
    }

    @Test
    public void checkUndoAfterFlip() {
        chatRoomWithUploadedImage.openEditorForLastUploadedImage()
                .flip()
                .checkUndo()
                .close();
    }

    @Test
    public void checkUndoAfter2Flips() throws IOException {
        PhotoEditorLayer photoEditorLayer = chatRoomWithUploadedImage.openEditorForLastUploadedImage().flip(2).makePhotoScreenshot();
        assertThrows(ElementNotFound.class, photoEditorLayer::checkUndo);
        photoEditorLayer.close();
    }

    @Test
    public void checkUndoAfter2Crops() throws IOException {
        PhotoEditorLayer photoEditorLayer = chatRoomWithUploadedImage.openEditorForLastUploadedImage().crop(2).makePhotoScreenshot();
        assertThrows(ElementNotFound.class, photoEditorLayer::checkUndo);
        photoEditorLayer.close();
    }
}
