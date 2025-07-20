package eric_burgers;

import eric_burgers.GUI.HomePage;
import eric_burgers.GUI.LoginPage;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginPageTest extends ApplicationTest {

    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        Stage mockStage = Mockito.mock(Stage.class);
        homePage = new HomePage(mockStage);
        loginPage = new LoginPage(mockStage, homePage);
    }

    @Test
    public void testCreateScene() {
        Platform.runLater(() -> {
            assertNotNull(loginPage.createScene());
        });
    }

}
