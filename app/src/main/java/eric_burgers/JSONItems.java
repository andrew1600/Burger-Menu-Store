package eric_burgers;
import java.io.FileReader;
import eric_burgers.objects.Category;
import eric_burgers.objects.Order;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileWriter;
import java.io.IOException;
import eric_burgers.objects.Item1;
import java.util.ArrayList;
import java.util.HashMap;
import static java.sql.Types.NULL;
/**
 * Reads and writes the JSON file
 */
public class JSONItems {
    private final HashMap<Integer, Item1> itemIdList;
    private String config;
    public JSONItems(){
        config = "src/main/resources/PermanentInformation.json";
        itemIdList = new HashMap<>();
        itemCheck();
    }
    public HashMap<Integer, Item1> itemCheck() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(config));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray itemsArray = (JSONArray) jsonObject.get("Items");
            for (Object item : itemsArray) {
                JSONObject itemObject = (JSONObject) item;
                String name = (String) itemObject.get("Name");
                int price = ((Long) itemObject.get("Price")).intValue();
                int ID = ((Long) itemObject.get("ID")).intValue();
                int category = ((Long) itemObject.get("Category")).intValue();
                String description = (String) itemObject.get("Description");
                if(price != NULL && !name.isEmpty() && ID != NULL && category != NULL) {
                    Item1 newItem = new Item1(name, price, ID, category, description);
                    itemIdList.put(newItem.getID(), newItem);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return itemIdList;
    }
    public void setconfig(String name){
        config = name;
    }
    public ArrayList<String> nameCheck() {
        ArrayList<String> nameList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(config));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray itemsArray = (JSONArray) jsonObject.get("Items");
            for (Object item : itemsArray) {
                JSONObject itemObject = (JSONObject) item;
                String name = (String) itemObject.get("Name");
                nameList.add(name);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return nameList;
    }
    public Item1 idChecker(int ID){
        return itemIdList.get(ID);
    }
    public void CreateOrderHistory(Order order){
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(config);
            JSONObject json = (JSONObject) parser.parse(reader);
            reader.close();
            JSONObject newOrder = new JSONObject();
            newOrder.put("Total Amount", order.getTotalAmount());
            newOrder.put("Order Number", order.getOrderNum());
            newOrder.put("Date", order.getTime());
            HashMap<Integer, Integer> orderItems = order.getMap();
            JSONArray items = new JSONArray();
            for (HashMap.Entry<Integer, Integer> entry : orderItems.entrySet()) {
                JSONObject obj = new JSONObject();
                int ID = entry.getKey();
                int quant = entry.getValue();
                obj.put("ID", ID);
                obj.put("Quantity", quant);
                obj.put("Price", idChecker(ID).getPrice());
                items.add(obj);
            }
            newOrder.put("Order Items", items);
            JSONArray orderHistory = (JSONArray) json.get("Order History");
            orderHistory.add(newOrder);
            FileWriter writer = new FileWriter(config);
            json.writeJSONString(writer);
            writer.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void ClearOrderHistory(){
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(config);
            JSONObject json = (JSONObject) parser.parse(reader);
            reader.close();
            JSONArray orderHistory = (JSONArray) json.get("Order History");
            orderHistory.clear();
            FileWriter writer = new FileWriter(config);
            json.writeJSONString(writer);
            writer.close();
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public HashMap<Integer, Order> getOrderHistory(){
        HashMap<Integer, Order> orderDetails = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(config));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray itemsArray = (JSONArray) jsonObject.get("Order History");
            for (Object item : itemsArray) {
                HashMap<Integer, Integer> itemList = new HashMap<>();
                HashMap<Integer, Integer> itemPrices = new HashMap<>();
                JSONObject itemObject = (JSONObject) item;
                int totalPrice = ((Long) itemObject.get("Total Amount")).intValue();
                String date = (String) itemObject.get("Date");
                int orderNumber = ((Long) itemObject.get("Order Number")).intValue();
                JSONArray orderItemsArray = (JSONArray) itemObject.get("Order Items");
                for (Object i : orderItemsArray) {
                    JSONObject itemObject1 = (JSONObject) i;
                    int quant = ((Long) itemObject1.get("Quantity")).intValue();
                    int ID = ((Long) itemObject1.get("ID")).intValue();
                    int price = ((Long) itemObject1.get("Price")).intValue();
                    itemList.put(ID, quant);
                    itemPrices.put(ID, price);
                }

                Order order = new Order(itemList, totalPrice, orderNumber, itemPrices);
                order.setTime(date);
                orderDetails.put(orderNumber, order);
                }
            }

        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
    public int getAmountOfOrders(){
        HashMap<Integer, Order> orderDetails = getOrderHistory();
        return orderDetails.size();
    }
    public void createItem(String name, int price, int ID, int category, String description){
        try{
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(config);
            JSONObject json = (JSONObject) parser.parse(reader);
            reader.close();
            JSONObject newItem = new JSONObject();
            newItem.put("Name", name);
            newItem.put("Price", price);
            newItem.put("ID", ID);
            newItem.put("Category", category);
            newItem.put("Description", description);
            JSONArray orderHistory = (JSONArray) json.get("Items");
            orderHistory.add(newItem);
            FileWriter writer = new FileWriter(config);
            json.writeJSONString(writer);
            writer.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, String> getAdmins(){
        HashMap<String, String> admins = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(config));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray itemsArray = (JSONArray) jsonObject.get("Admins");
            for (Object item : itemsArray) {
                JSONObject itemObject = (JSONObject) item;
                String username = (String) itemObject.get("Username");
                String password = (String) itemObject.get("Password");
                admins.put(username, password);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return admins;
    }
    public void addAdmin(String username, String password){
        try{
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(config);
            JSONObject json = (JSONObject) parser.parse(reader);
            reader.close();
            JSONObject newItem = new JSONObject();
            newItem.put("Username", username);
            newItem.put("Password", password);
            JSONArray admins = (JSONArray) json.get("Admins");
            admins.add(newItem);
            FileWriter writer = new FileWriter(config);
            json.writeJSONString(writer);
            writer.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void removeItem(int ID){
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(config);
            JSONObject json = (JSONObject) parser.parse(reader);
            reader.close();
            JSONArray items = (JSONArray) json.get("Items");
            for(int i = 0; i < items.size(); i++){
                JSONObject obj= (JSONObject) items.get(i);
                if(((Long) obj.get("ID")).intValue() == ID){
                    items.remove(obj);
                    break;
                }
            }
            FileWriter writer = new FileWriter(config);
            json.writeJSONString(writer);
            writer.close();
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void editItem(String name, int price, int ID, int category, String description){
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(config);
            JSONObject json = (JSONObject) parser.parse(reader);
            reader.close();
            JSONArray items = (JSONArray) json.get("Items");
            for(int i = 0; i < items.size(); i++){
                JSONObject obj= (JSONObject) items.get(i);
                if(((Long) obj.get("ID")).intValue() == ID){
                    items.remove(obj);
                    break;
                }
            }
            JSONObject newItem = new JSONObject();
            newItem.put("Name", name);
            newItem.put("Price", price);
            newItem.put("ID", ID);
            newItem.put("Category", category);
            newItem.put("Description", description);
            JSONArray itemlist = (JSONArray) json.get("Items");
            itemlist.add(newItem);
            FileWriter writer = new FileWriter(config);
            json.writeJSONString(writer);
            writer.close();
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Category> getCategories(){
        HashMap<Integer, Category> Categories = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(config));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray itemsArray = (JSONArray) jsonObject.get("Category");
            for (Object item : itemsArray) {
                JSONObject itemObject = (JSONObject) item;
                String name = (String) itemObject.get("Name");
                int ID = ((Long) itemObject.get("ID")).intValue();
                Category category = new Category(name, ID);
                Categories.put(ID, category);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return Categories;
    }
    public void createCategory(String name, Integer ID) {
        if (!getCategories().containsKey(ID)) {
            try {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(config);
                JSONObject json = (JSONObject) parser.parse(reader);
                reader.close();
                JSONObject newItem = new JSONObject();
                newItem.put("Name", name);
                newItem.put("ID", ID);
                JSONArray admins = (JSONArray) json.get("Category");
                admins.add(newItem);
                FileWriter writer = new FileWriter(config);
                json.writeJSONString(writer);
                writer.close();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<String> CategoryNameCheck() {
        ArrayList<String> nameList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(config));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray itemsArray = (JSONArray) jsonObject.get("Category");
            for (Object item : itemsArray) {
                JSONObject itemObject = (JSONObject) item;
                String name = (String) itemObject.get("Name");
                nameList.add(name);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return nameList;
    }
    public String CategoryIDSearch(int ID){
        String name = "";
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(config));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray itemsArray = (JSONArray) jsonObject.get("Category");
            for (Object item : itemsArray) {
                JSONObject itemObject = (JSONObject) item;
                String foundName = (String) itemObject.get("Name");
                int foundID = ((Long) itemObject.get("ID")).intValue();
                if(foundID == ID){name = foundName;}
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return name;
    }
    public HashMap<Integer, Item1> getItemsOfCategory(int catID){
        HashMap<Integer, Item1> itemCatList = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(config));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray itemsArray = (JSONArray) jsonObject.get("Items");
            for (Object item : itemsArray) {
                JSONObject itemObject = (JSONObject) item;
                String name = (String) itemObject.get("Name");
                int price = ((Long) itemObject.get("Price")).intValue();
                int ID = ((Long) itemObject.get("ID")).intValue();
                int category = ((Long) itemObject.get("Category")).intValue();
                String description = (String) itemObject.get("Description");
                if(price != NULL && !name.isEmpty() && ID != NULL && category != NULL) {
                    if(catID == category) {
                        Item1 newItem = new Item1(name, price, ID, category, description);
                        itemCatList.put(newItem.getID(), newItem);
                    }
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return itemCatList;
    }
    public void editCategory(String name, int ID){
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(config);
            JSONObject json = (JSONObject) parser.parse(reader);
            reader.close();
            JSONArray items = (JSONArray) json.get("Category");
            for(int i = 0; i < items.size(); i++){
                JSONObject obj= (JSONObject) items.get(i);
                if(((Long) obj.get("ID")).intValue() == ID){
                    items.remove(obj);
                    break;
                }
            }
            JSONObject newItem = new JSONObject();
            newItem.put("Name", name);
            newItem.put("ID", ID);
            JSONArray itemlist = (JSONArray) json.get("Category");
            itemlist.add(newItem);
            FileWriter writer = new FileWriter(config);
            json.writeJSONString(writer);
            writer.close();
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void removeCategory(int ID){
        try {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(config);
        JSONObject json = (JSONObject) parser.parse(reader);
        reader.close();
        JSONArray items = (JSONArray) json.get("Category");
        for(int i = 0; i < items.size(); i++){
            JSONObject obj= (JSONObject) items.get(i);
            if(((Long) obj.get("ID")).intValue() == ID){
                items.remove(obj);
                break;
            }
        }
        FileWriter writer = new FileWriter(config);
        json.writeJSONString(writer);
        writer.close();
    }
    catch (IOException | ParseException e) {
        e.printStackTrace();
    }
    }
}
