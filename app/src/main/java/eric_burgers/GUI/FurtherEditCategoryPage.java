package eric_burgers.GUI;
import eric_burgers.JSONItems;
import eric_burgers.objects.Category;
import eric_burgers.objects.Item1;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * Contains functionality for editing either an item or a category.
 */
public class FurtherEditCategoryPage implements GUI {
    private final Stage stage;
    private final HomePage homepage;
    private Category categoryToChange;
    private Item1 itemtoChange;
    private int newCategory;
    private boolean itemType;
    private String name;
    private String description = "";
    private int ID;
    private int price = 0;
    private int categoryId = 1;
    /**
     * Constructor
     */
    public FurtherEditCategoryPage(Stage stage, HomePage homepage) {
        this.stage = stage;
        this.homepage = homepage;
    }
    /**
     * Creates FurtherEditCategoryPage Scene
     *
     * @return         FurtherEditCategoryPage Scene
     */
    public Scene createScene() {
        if (itemType) {
            name = itemtoChange.getName();
            ID = itemtoChange.getID();
            price = itemtoChange.getPrice();
            categoryId = itemtoChange.getCategory();
            description = itemtoChange.getDescription();
        } else {
            name = categoryToChange.getCategoryName();
            ID = categoryToChange.getCategoryID();
        }
        JSONItems obj = new JSONItems();
        Label nameLabel = homepage.labelStandardMaker("Insert new name: ");
        final TextField nameField = new TextField();
        nameField.setMaxWidth(200);
        HBox hboxName = new HBox(nameLabel, nameField);
        Label priceLabel = homepage.labelStandardMaker("Insert new price($): ");
        final TextField priceField = new TextField();
        priceField.setMaxWidth(100);
        HBox hboxPrice = new HBox(priceLabel, priceField);
        Label IDLabel = homepage.labelStandardMaker("Insert new ID: ");
        final TextField IDField = new TextField();
        IDField.setMaxWidth(100);
        HBox hboxID = new HBox(IDLabel, IDField);
        Label descriptionLabel = homepage.labelStandardMaker("Insert new Description: ");
        final TextField descriptionField = new TextField();
        descriptionField.setMaxWidth(200);
        HBox hboxDescription = new HBox(descriptionLabel, descriptionField);
        String categoryName = obj.CategoryIDSearch(categoryId);
        Label categoryLabel = homepage.labelStandardMaker("Select Category. Currently selected is " + categoryName);
        HBox hboxCategory = new HBox(categoryLabel);
        hboxCategory.setSpacing(5);
        GridPane gp = new GridPane();
        newCategory = homepage.gridCreatorCategory(gp, categoryLabel, categoryId);
        VBox vboxCategory = new VBox(hboxCategory, gp);
        vboxCategory.setSpacing(10);
        Label errorLabel = homepage.bigLabelMaker("");
        Label currentItemNameLabel = homepage.labelStandardMaker("Current Name: " + name);
        Label currentItemID = homepage.labelStandardMaker("Current ID: " + ID);
        Label currentItemPrice = homepage.labelStandardMaker("Current price: " + price);
        Label currentItemCategory = homepage.labelStandardMaker("Current Category: " + obj.CategoryIDSearch(categoryId));
        VBox currentItem = new VBox();
        if (itemType) {
            currentItem.getChildren().clear();
            currentItem.getChildren().addAll(currentItemNameLabel, currentItemID, currentItemPrice, currentItemCategory);
        } else {
            currentItem.getChildren().clear();
            currentItem.getChildren().addAll(currentItemNameLabel, currentItemID);
        }


        Button backButton = new Button("Cancel");
        backButton.setOnAction(event -> {
            if (itemType) {
                stage.setScene(homepage.createEditItemPageScene());
            } else {
                stage.setScene(homepage.createEditCategoryPage());
            }
        });
        Button createItemButton = new Button("Confirm Edit");
        createItemButton.setOnAction(t -> {
            if (!nameField.getText().isEmpty()) {
                name = nameField.getText();
            }
            if (!IDField.getText().isEmpty()) {
                ID = Integer.parseInt(IDField.getText());
            }
            JSONItems obj1 = new JSONItems();
            if (itemType) {
                if (!priceField.getText().isEmpty()) {
                    price = Integer.parseInt(priceField.getText());
                }
                else if (!descriptionField.getText().isEmpty()){
                        description = descriptionField.getText();
                    }
                obj1.editItem(name, price, ID, newCategory, description);
                stage.setScene(homepage.createEditItemPageScene());
            } else {
                obj1.editCategory(name, ID);
                stage.setScene(homepage.createEditCategoryPage());
            }
        });
        VBox vbox = new VBox();
        if (itemType) {vbox.getChildren().addAll(hboxName, hboxPrice, hboxID, hboxDescription, vboxCategory, createItemButton);}
        else {vbox.getChildren().addAll(hboxName, hboxID, createItemButton);}
            vbox.setSpacing(10);
            BorderPane bp = new BorderPane();
            bp.setTop(backButton);
            bp.setCenter(vbox);
            bp.setBottom(errorLabel);
            BorderPane.setMargin(currentItem, new Insets(10, 10, 10, 10));
            bp.setRight(currentItem);
            return new Scene(bp, 600, 400);
    }
    /**
     * sets the category that is to be edited
     * @param categorytoChange this is the category that will be edited
     * @return         EditItemPage Scene
     */
    public void setCategoryToChange (Category categorytoChange){
        this.categoryToChange = categorytoChange;
    }
    /**
     * sets the category that is to be edited
     *
     * @return         the category that is to be changed
     */
    public Category getCategoryToChange() {
        return this.categoryToChange;
    }
    /**
     * sets the item that is to be edited
     * @param item this is the item that will be edited
     *
     */
    public void setItemtoChange (Item1 item){this.itemtoChange = item;}
    /**
     * gets the item that is to be changed
     *
     * @return         item to be changed
     */
    public Item1 getItemtoChange() {
        return this.itemtoChange;
    }
    /**
     * sets whether this page will be for editing an item or a category
     * @param itemType if this page is for editing item it will be true, else it will be false
     */
    public void setPageType ( boolean itemType){
        this.itemType = itemType;
    }
    /**
     * gets boolean of whether this is for an item or category
     * @return         boolean value of whether it is editing item or category
     */

    public boolean getPageType() {
        return this.itemType;
    }
}