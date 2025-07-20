package eric_burgers;

import eric_burgers.objects.Item1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Item1Test {

    @Test void assertItem(){
        Item1 item = new Item1("special burger", 4,2,1,"hi");
        assertEquals(item.getName(),"special burger");
        assertEquals(item.getPrice(),4);
        assertEquals(item.getID(),2);
        assertEquals(item.getCategory(),1);
        assertEquals(item.getDescription(),"hi");
        item.setName("name");
        assertEquals("name", item.getName());
        item.setPrice(6);
        assertEquals(6, item.getPrice());
    }
}
