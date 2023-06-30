import model.OKUser;
import model.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.AuthorizationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

public class PhotoEditorLayerTest {
    private static final String BASE_URL = "http://localhost:3000";
    private static final String UPLOADING_IMAGE_PATH = "/Users/vladislav.yartsev/dev/personal/photo-editor-tests/src/test/resources/uploadingImage.jpeg";
    private static OKUser user;
    private AuthorizationPage authorizationPage;

    @BeforeAll
    public static void globalSetUp() {
        user = Users.okBot();
    }

    @BeforeEach
    public void setUp() {
        open(BASE_URL);
        webdriver().object().manage().window().maximize();
        authorizationPage = new AuthorizationPage();
    }

    @Test
    public void openLayerTest() {
        authorizationPage.logInViaOK().logInWith(user)
                .openFirstChatRoom()
                .upload(UPLOADING_IMAGE_PATH)
                .openEditorForLastUploadedImage();
    }

    @Test
    public void closeAndEscapeTest() {
        authorizationPage.logInViaOK().logInWith(user)
                .openFirstChatRoom()
                .upload(UPLOADING_IMAGE_PATH)
                .openEditorForLastUploadedImage()
                .close()
                .openEditorForLastUploadedImage()
                .escape();
    }
}
