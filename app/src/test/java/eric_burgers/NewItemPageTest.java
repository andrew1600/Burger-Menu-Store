package eric_burgers;

import eric_burgers.GUI.HomePage;
import eric_burgers.GUI.NewItemPage;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NewItemPageTest extends ApplicationTest {
    private NewItemPage newItemPage;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        Stage mockStage = Mockito.mock(Stage.class);
        homePage = new HomePage(mockStage);
        newItemPage = new NewItemPage(mockStage, homePage);
    }

    @Test
    public void testCreateScene() {
        Platform.runLater(() -> {
            assertNotNull(newItemPage.createScene());
        });
    }

    @Test
    public void testGetNextID() {
        int x = newItemPage.getNextID();
        assertNotNull(x);
    }

    @Test
    public void testGetNewCategory() {
        assertNotNull(newItemPage.getNewCategory());
    }

    @Test
    public void testSetNewCatrgory() {
        newItemPage.setNewCategory(3);
        assertEquals(newItemPage.getNewCategory(), 3);
    }

}
