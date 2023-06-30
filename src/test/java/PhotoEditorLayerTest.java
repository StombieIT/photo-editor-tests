import model.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.AuthorizationPage;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

public class PhotoEditorLayerTest {
    private static final String BASE_URL = "http://localhost:3000";
    private static final String UPLOADING_IMAGE_PATH = "/Users/vladislav.yartsev/dev/personal/photo-editor-tests/src/test/resources/uploadingImage.jpeg";
    private static MainPage mainPage;

    @BeforeAll
    public static void globalSetUp() {
        open(BASE_URL);
        webdriver().object().manage().window().maximize();
        mainPage = new AuthorizationPage().logInViaOK().logInWith(Users.okBot());
    }

    @Test
    public void openLayerTest() {
        mainPage.openFirstChatRoom()
                .upload(UPLOADING_IMAGE_PATH)
                .openEditorForLastUploadedImage()
                .close();
    }

    @Test
    public void closeAndEscapeTest() {
        mainPage.openFirstChatRoom()
                .upload(UPLOADING_IMAGE_PATH)
                .openEditorForLastUploadedImage()
                .close()
                .openEditorForLastUploadedImage()
                .escape();
    }
}
