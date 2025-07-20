package eric_burgers;

import eric_burgers.GUI.HomePage;
import eric_burgers.GUI.MenuPage;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MenuPageTest extends ApplicationTest {

    private HomePage homePage;
    private MenuPage menuPage;

    @BeforeEach
    public void setUp() {
        Stage mockStage = Mockito.mock(Stage.class);
        homePage = new HomePage(mockStage);
        menuPage = new MenuPage(mockStage, homePage);
    }

    @Test
    public void testCreateScene() {
        Platform.runLater(() -> {
            assertNotNull(menuPage.createScene());
        });
    }
}
