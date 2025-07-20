package eric_burgers;

import eric_burgers.GUI.HomePage;
import eric_burgers.objects.Order;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.HashMap;

public class HomePageTest extends ApplicationTest{
    private HomePage homePage;
    JSONItems json;

    @BeforeEach
    public void setUp() {
        json = new JSONItems();
        Stage mockStage = Mockito.mock(Stage.class);
        homePage = new HomePage(mockStage);
    }

    @Test
    public void testLoggedInStatus() {
        Platform.runLater(() -> {
            homePage.loggedInStatus(true);
            assertTrue(homePage.getLoggedInStatus());
            homePage.loggedInStatus(false);
            assertFalse(homePage.getLoggedInStatus());
        });
    }

    @Test
    public void testUsername() {
        Platform.runLater(() -> {
            homePage.setUsername("testUser");
            assertEquals("testUser", homePage.getUsername());
        });
    }
    @Test
    public void testCreateScene() {
        Platform.runLater(() -> {
            assertNotNull(homePage.createScene());
        });
    }

    @Test
    public void testAddToCart() {
        Platform.runLater(() -> {
            homePage.addToCart(1);
            homePage.addToCart(2);
            homePage.addToCart(1);
            assertEquals(2, homePage.getCartList().get(1));
            assertEquals(1, homePage.getCartList().get(2));

            homePage.removeFromCart(1);
            homePage.removeFromCart(4);
            homePage.removeFromCart(2);
            assertEquals(1, homePage.getCartList().get(1));

        });
    }
    @Test
    public void testCreateScenes() {
        Platform.runLater(() -> {
            homePage.addToCart(1);
            assertNotNull(homePage.createMenuPageScene());
            assertNotNull(homePage.createPaymentScene());
            assertNotNull(homePage.createCartScene());
            assertNotNull(homePage.createConfirmationScene());
            assertNotNull(homePage.createAdminScene());
            assertNotNull(homePage.createNewItemScene());
            assertNotNull(homePage.createAccountPageScene());

        });
    }
    @Test
    public void testCreateMoreScenes() {
        Platform.runLater(() -> {
            assertNotNull(homePage.createEditItemPageScene());
            assertNotNull(homePage.createOrderHistoryPageScene());
            assertNotNull(homePage.createCreateCategoryPage());
            assertNotNull(homePage.createEditCategoryPage());
            assertNotNull(homePage.createItemViewPage());
        });
    }

    @Test
    public void testVboxOrderHistoryCreator() {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> map1 = new HashMap<>();
        map.put(1,1);
        map.put(2,2);
        map1.put(1,1);
        map1.put(2,2);
        Order order = new Order(map, 20, 3, map1);
        Platform.runLater(() -> {
            assertNotNull(homePage.vboxorderHistoryCreator(new VBox(), order));
        });
    }
    @Test
    public void testUpdateOrderNumber() {
        Platform.runLater(() -> {
            homePage.updateOrderNumber();
            int orderNumber = json.getAmountOfOrders();
            assertEquals(orderNumber + 1, homePage.getOrderNumber());
        });
    }

    @Test
    public void testGetIndividualPrice() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,2);
        Platform.runLater(() -> {
            assertEquals(4, homePage.getIndividualPrice(2, map));
        });
    }
    @Test
    public void testGetTotalPrice() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(2,2);
        Platform.runLater(() -> {
            assertEquals(4, homePage.getTotalPrice( map));
        });
    }
}