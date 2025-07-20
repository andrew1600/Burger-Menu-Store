package eric_burgers;

import eric_burgers.objects.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    @Test void categoryHasName() {
        Category categoryUnderTest = new Category("categoryName", 1);
        assertEquals("categoryName", categoryUnderTest.getCategoryName(), "Category name should match category object name");
    }
    @Test void categoryHasID() {
        Category categoryUnderTest = new Category("categoryName", 1);
        assertEquals(1, categoryUnderTest.getCategoryID(), "Category ID should match category object id");
    }
}
