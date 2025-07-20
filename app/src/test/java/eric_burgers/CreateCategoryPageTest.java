package eric_burgers;

import eric_burgers.GUI.CreateCategoryPage;
import eric_burgers.GUI.HomePage;
import eric_burgers.objects.Category;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;


public class CreateCategoryPageTest extends ApplicationTest {
    private HomePage homePage;
    private Stage mockStage2;

    private CreateCategoryPage pageTest;

    @BeforeEach
    public void setUp() {
        Stage mockStage = Mockito.mock(Stage.class);
        homePage = new HomePage(mockStage);
        mockStage2 = Mockito.mock(Stage.class);
        pageTest = new CreateCategoryPage(mockStage2, homePage);
    }
    @Test void testCreateScene() {
        assertNotNull(pageTest.createScene(), "");
    }

    @Test void testVboxCategoryHelper() {
        VBox vbox = new VBox();
        assertNotNull(pageTest.vboxCategoryHelper(1, new Category("string", 1)), "");
    }
    @Test void testVboxCategoryCreator() {
        CreateCategoryPage pageTest = new CreateCategoryPage(mockStage2, homePage);
        assertNotNull(pageTest.createScene(), "");
    }
}
