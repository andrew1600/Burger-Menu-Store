package eric_burgers.objects;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
/**
 * Holds details about a unique order
 */
public class Order {
    private HashMap<Integer, Integer> orderMap;
    private HashMap<Integer, Integer> itemPrices;
    private int totalAmount;
    private int OrderNum;
    private LocalDateTime time;
    private String formattedDateTime;
    /**
     * Constructor
     */
    public Order(HashMap<Integer, Integer> orderMap,int totalAmount, int OrderNum, HashMap<Integer, Integer> itemPrices){
        this.orderMap = orderMap;
        this.totalAmount = totalAmount;
        this.OrderNum = OrderNum;
        this.itemPrices = itemPrices;
        this.time = java.time.LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.formattedDateTime = time.format(dateTimeFormatter);
    }
    /**
     * gets the order map
     * @return returns a hashmap of the item IDs and the quantity of that item
     */
    public HashMap<Integer, Integer> getMap(){return orderMap;}
    /**
     * gets the total amount of the order
     * @return returns the total amount of the order
     */
    public int getTotalAmount(){return totalAmount;}
    /**
     * gets the order ID
     * @return returns the order ID
     */
    public int getOrderNum(){return OrderNum;}
    /**
     * gets the time the order was placed
     * @return returns the time the order was placed
     */
    public String getTime(){return formattedDateTime;}
    /**
     * sets the time the order was placed
     * @param time the time the order was placed
     */

    public void setTime(String time){this.formattedDateTime = time;}
    /**
     * gets the hash map of the item IDs and their price at the time of ordering
     * @return returns a hashmap of the item IDs and the price of that item at time of order
     */
    public HashMap<Integer, Integer> getItemPrices(){return itemPrices;}
}
