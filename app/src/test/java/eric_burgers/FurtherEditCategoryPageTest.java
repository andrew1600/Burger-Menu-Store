package eric_burgers;
import eric_burgers.objects.Item1;
import eric_burgers.GUI.FurtherEditCategoryPage;
import eric_burgers.GUI.HomePage;
import eric_burgers.objects.Category;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class FurtherEditCategoryPageTest extends ApplicationTest {
    private HomePage homePage;
    private Stage mockStage2;

    private FurtherEditCategoryPage pageTest;

    @BeforeEach
    public void setUp() {
        Category category = new Category("string", 1);
        Stage mockStage = Mockito.mock(Stage.class);
        homePage = new HomePage(mockStage);
        mockStage2 = Mockito.mock(Stage.class);
        pageTest = new FurtherEditCategoryPage(mockStage2, homePage);
        pageTest.setCategoryToChange(category);
    }

    @Test
    void createScene() {
        assertNotNull(pageTest.createScene(), "");
    }

    @Test
    void setCategoryToChange() {
        Category newCategory = new Category("string1", 1);
        pageTest.setCategoryToChange(newCategory);
        assertEquals("string1", pageTest.getCategoryToChange().getCategoryName(), "");
    }

    @Test void setItemtoChange() {
        Item1 newItem = new Item1("name", 1, 1, 1, "description");
        pageTest.setItemtoChange(newItem);
        assertEquals(newItem, pageTest.getItemtoChange(), "");
    }

    @Test void setPageType() {
        boolean newPageType = true;
        pageTest.setPageType(newPageType);
        assertEquals(newPageType, pageTest.getPageType(), "");
    }
}