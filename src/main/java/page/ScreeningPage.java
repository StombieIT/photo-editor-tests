package page;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.io.FileUtils;
import ru.stombie.base.LoadablePage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ScreeningPage extends LoadablePage {
    private static final String DEFAULT_DIR = "screenshots";
    private static final String DELIMITER = "/";

    private final String dir;

    ScreeningPage() {
        this(DEFAULT_DIR);
    }

    ScreeningPage(String dir) {
        this.dir = dir;
    }

    protected File screenShotTo(SelenideElement element, String filename) throws IOException {
        File buildScreenshotFile = element.screenshot();
        Path dirPath = Paths.get(dir);
        if (!dirPath.toFile().exists()) {
            Files.createDirectories(dirPath);
        }
        File screenshotFile = new File(dir + DELIMITER + filename);
        if (screenshotFile.exists()) {
            Files.delete(screenshotFile.toPath() );
        }
        FileUtils.copyFile(buildScreenshotFile, screenshotFile);
        return screenshotFile;
    }
}
