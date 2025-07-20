package eric_burgers.objects;
/**
 * Category Object
 */
public class Category {
    private String name;
    private int categoryID;
    /**
     * Constructor
     */
    public Category(String name, Integer categoryID) {
        this.name = name;
        this.categoryID = categoryID;
    }
    /**
     * gets the name of the category
     * @return returns the name of the category
     */
    public String getCategoryName() {
        return name;
    }
    /**
     * gets the name of the category
     * @return returns the ID of the category
     */
    public int getCategoryID() {
        return categoryID;
    }
}