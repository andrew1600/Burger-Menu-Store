package eric_burgers;


import eric_burgers.GUI.HomePage;
import eric_burgers.objects.Category;
import eric_burgers.objects.Item1;
import eric_burgers.objects.Order;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONItemsTest {
    private JSONItems obj;
    @BeforeEach
    void Setup() {
        obj = new JSONItems();
        obj.setconfig("src/test/resources/PermanentInformation.json");
    }


    @Test
    void TestItemCheck() {
        Map<Integer, Item1> map = obj.itemCheck();

        assertNotNull(map);
    }

    @Test
    void TestnameCheck() {
        ArrayList<String> nameList = obj.nameCheck();

        assertNotNull(nameList);
    }

    @Test
    void TestidChecker() {
        Item1 item = obj.idChecker(1);

        assertNotNull(item);
    }
    @Test
    void CreateOrderHistory() {
        HashMap<Integer, Integer> food = new HashMap<>();
        food.put(1, 1);

        Order order = new Order(food, 4, 15, food);

        obj.CreateOrderHistory(order);

        Order order2 = obj.getOrderHistory().get(15);

        assertNotNull(obj.getOrderHistory().get(15));

    }

    @Test
    void TestClearOrderHistory() {
        obj.ClearOrderHistory();
        assertTrue(obj.getOrderHistory().isEmpty());

    }

    @Test
    void TestgetOrderHistory() {

        HashMap<Integer, Integer> food = new HashMap<>();
        food.put(1, 1);

        Order order = new Order(food, 4, 15, food);

        obj.CreateOrderHistory(order);


        HashMap<Integer, Order> hist = obj.getOrderHistory();

        assertFalse(hist.isEmpty());

    }

    @Test
    void TestgetAmountofOrders() {
        obj.ClearOrderHistory();
        HashMap<Integer, Integer> food = new HashMap<>();
        food.put(1, 1);

        Order order = new Order(food, 4, 15, food);

        obj.CreateOrderHistory(order);

        assertEquals(1, obj.getAmountOfOrders());
    }

    /*
    @Test
    void TestCreateItem() {
        obj.createItem("test",4, 15, 2, "Test");
        assertEquals("test", obj.idChecker(15).getName());


    }

     */

    @Test
    void TestgetAdmin() {
        HashMap<String, String> map = obj.getAdmins();

        assertTrue(map.get("admin").equals("password"));
    }

    @Test
    void TestAddAdmin() {
        obj.addAdmin("test", "123");
        HashMap<String, String> map = obj.getAdmins();

        assertTrue(map.get("test").equals("123"));

    }

    @Test
    void TestRemoveItem() {
        obj.createItem("test",4, 23, 2, "Test");
        obj.removeItem(23);

        //assertNull(obj.idChecker(23));
    }

    @Test
    void TestEditItem(){
        obj.createItem("test",4, 1, 2, "Test");
        obj.editItem("test", 4, 1, 2, "Test");
        obj.itemCheck();
        assertEquals(obj.idChecker(1).getName(), "test");
        obj.removeItem(1);
    }


    @Test
    void TestCreateCategories() {
        obj.createCategory("test", 7);
        assertTrue(obj.CategoryIDSearch(7).equals("test"));
    }


    @Test
    void TestCategoryNameCheck() {
        obj.removeCategory(2);
        obj.createCategory("Test", 2);
        assertTrue(obj.CategoryNameCheck().contains("Test"));
    }

    @Test
    void TestGetItemsOfCategory() {
        obj.createCategory("test", 9);
        obj.createItem("test", 1, 16,9,"test" );
        HashMap<Integer, Item1> x = obj.getItemsOfCategory(9);
        Item1 a = x.get(16);
        assertEquals(a.getName(), "test");
        obj.removeItem(16);


    }

    @Test
    void TestEditCategory() {
        obj.createCategory("test", 1);
        obj.editCategory("test2", 1);

        HashMap<Integer, Category> x = obj.getCategories();

        assertEquals(x.get(1).getCategoryName(), "test2");
    }
    @Test
    void TestRemoveCategory() {
        obj.createCategory("test", 1);
        obj.removeCategory(1);

        HashMap<Integer, Category> x = obj.getCategories();

        assertFalse(x.containsKey(1));

    }






}
