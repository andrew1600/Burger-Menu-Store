package eric_burgers;

import eric_burgers.GUI.HomePage;
import eric_burgers.GUI.ItemViewPage;
import eric_burgers.objects.Item1;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemViewPageTest extends ApplicationTest{
    private HomePage homePage;
    private ItemViewPage itemViewPage;

    @BeforeEach
    public void setUp() {
        Stage mockStage = Mockito.mock(Stage.class);
        homePage = new HomePage(mockStage);
        itemViewPage = new ItemViewPage(mockStage, homePage);
    }

    @Test
    public void testCreateScene() {
        Platform.runLater(() -> {
            assertNotNull(itemViewPage.createScene());
        });
    }

    @Test
    public void testSetItem() {
        Item1 item = new Item1("special burger", 4,2,1,"hi");
        itemViewPage.setItem(item);
        assertEquals("special burger", item.getName());
        assertEquals(4, item.getPrice());
        assertEquals("hi", item.getDescription());
        assertEquals(2, item.getID());

    }

}
