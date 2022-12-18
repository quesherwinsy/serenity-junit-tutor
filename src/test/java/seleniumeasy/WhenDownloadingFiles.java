package seleniumeasy;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.configuration.SessionLocalTempDirectory;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import seleniumeasy.pageobjects.DownloadPage;
import java.io.File;
import java.nio.file.Paths;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenDownloadingFiles {
    @Managed(driver= "chrome", uniqueSession = true)
    WebDriver driver;

    DownloadPage downloadPage;

    @Test
    public void weCanDownloadFileToOurHardDrive() throws InterruptedException {
        downloadPage.open();
        downloadPage.downloadSampleFile();
        // create file object and download file to local directory
        // using hard coded path is bad
        // File downloadedFile = Paths.get("/Users/home/Downloads/sample.png").toFile();
        // use temporary directory for downloads
        File downloadedFile = SessionLocalTempDirectory.forTheCurrentSession().resolve("sample.png").toFile();
        System.out.println(downloadedFile);
        // Await utility, wait until downloaded file exist in temporary directory
        await().atMost(20, SECONDS).until(downloadedFile::exists);
        assertThat(downloadedFile.getName()).isEqualTo("sample.png");
        // note temp directory currently not working
    }
}
