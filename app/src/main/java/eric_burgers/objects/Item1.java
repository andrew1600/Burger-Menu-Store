package eric_burgers.objects;
/**
 * object holds details about an item
 */
public class Item1 {
    private String name;
    private int price;
    private int ID;
    private int category;
    private String description;
    /**
     * Constructor
     */
    public Item1(String name,int price, int ID, int category, String description){
        this.name = name;
        this.price = price;
        this.ID = ID;
        this.category = category;
        this.description = description;
    }
    /**
     * gets the name of the item
     * @return returns the name of the item
     */
    public String getName(){return name;}
    /**
     * gets the price of the item
     * @return returns the price of the item
     */
    public int getPrice(){return price;}
    /**
     * gets the ID of the item
     * @return returns the ID of the item
     */
    public int getID(){return ID;}
    /**
     * gets the category ID the item belongs to
     * @return returns the category ID the item belongs to
     */
    public int getCategory(){return category;}
    /**
     * sets the name of the item
     * @param name the name of the item
     */
    public void setName(String name){this.name = name;}
    /**
     * sets the price of the item
     * @param price the price of the item
     */
    public void setPrice(Integer price){this.price = price;}
    /**
     * gets the description of the item
     * @return returns the description of the item
     */
    public String getDescription(){return description;}

}
