package eric_burgers;

import eric_burgers.objects.Order;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test void assertOrderTest(){
        HashMap<Integer,Integer> ordermap = new HashMap<Integer,Integer>();
        ordermap.put(1,1);
        ordermap.put(2,3);
        HashMap<Integer,Integer> itemprices = new HashMap<Integer,Integer>();
        itemprices.put(1,2);
        itemprices.put(2,4);
        Order order = new Order(ordermap, 20, 1, itemprices);
        assertEquals(20, order.getTotalAmount());
        assertEquals(1, order.getOrderNum());
        order.setTime("time");
        assertEquals("time", order.getTime());
    }
}
