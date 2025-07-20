package eric_burgers.GUI;
import eric_burgers.JSONItems;
import eric_burgers.objects.Item1;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Contains functionality for creating a new item
 */
public class NewItemPage implements GUI{
    private final Stage stage;
    private final HomePage homepage;
    private int newCategory;
    /**
     * Constructor
     */
    public NewItemPage(Stage stage, HomePage homepage){
        this.stage = stage;
        this.homepage = homepage;
    }
    /**
     * Creates NewItemPage Scene
     *
     * @return         NewItemPage Scene
     */
    public Scene createScene() {
        Label nameLabel = new Label("Insert item name: ");
        final TextField nameField = new TextField();
        nameField.setMaxWidth(200);
        HBox hboxName = new HBox(nameLabel, nameField);
        Label priceLabel = new Label("Insert price($): ");
        final TextField priceField = new TextField();
        priceField.setMaxWidth(100);
        HBox hboxPrice = new HBox(priceLabel, priceField);
        Label descriptionLabel = new Label("insert item description: ");
        final TextField descriptionField = new TextField();
        descriptionField.setMaxWidth(200);
        HBox hboxdescription = new HBox(descriptionLabel, descriptionField);
        Label categoryLabel = homepage.labelStandardMaker("Select Category");
        GridPane gp = new GridPane();
        newCategory = homepage.gridCreatorCategory(gp, categoryLabel, 0);
        VBox vboxCategory = new VBox(categoryLabel, gp);
        vboxCategory.setSpacing(10);
        Label errorLabel = homepage.bigLabelMaker("");
        Button backButton = new Button("Cancel");
        backButton.setOnAction(event -> stage.setScene(homepage.createAdminScene()));
        Button createItemButton = new Button("Create Item");
        createItemButton.setOnAction(t -> {
            String name = nameField.getText();
            String description = descriptionField.getText();
            int price = Integer.parseInt(priceField.getText());
            JSONItems obj = new JSONItems();
            ArrayList<String> nameList = obj.nameCheck();
            if (nameList.contains(name)){
                errorLabel.setText("Name already exist please retry");
                nameField.clear();
            }else if(getNewCategory() == 0) {
                errorLabel.setText("Please choose a category");
            } else if(description.isEmpty()){
                errorLabel.setText("No Description Entered");
            }
            else {
                obj.createItem(name, price, getNextID(), newCategory, description);
                stage.setScene(homepage.createAdminScene());
            }
        });
        VBox vbox = new VBox(hboxName, hboxPrice, hboxdescription, vboxCategory, createItemButton);
        vbox.setSpacing(10);
        BorderPane bp = new BorderPane();
        bp.setTop(backButton);
        bp.setCenter(vbox);
        bp.setBottom(errorLabel);
        return new Scene(bp, 600, 400);
    }
    /**
     * gets the next available ID to create an item with.
     *
     * @return         integer value of the next available ID
     */
    public int getNextID(){
        JSONItems obj = new JSONItems();
        HashMap<Integer, Item1> itemlist = obj.itemCheck();
        int nextID = itemlist.size() + 1;
        for(int i = 0; i < itemlist.size(); i++){
            if(!itemlist.containsKey(i+1)){
                nextID = i + 1;
            }
        }
        return nextID;
    }
    /**
     * gets ID of the category chosen
     *
     * @return         returns the ID of the new category
     */
    public int getNewCategory(){return newCategory;}
    /**
     * sets the ID of the chosen category
     * @param newCategory this is the ID of the new category
     *
     */
    public void setNewCategory(int newCategory){this.newCategory = newCategory;}
}

