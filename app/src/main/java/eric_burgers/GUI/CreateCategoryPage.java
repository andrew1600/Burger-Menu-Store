package eric_burgers.GUI;
import eric_burgers.JSONItems;
import eric_burgers.objects.Category;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Contains functionality for creating a new category
 */
public class CreateCategoryPage implements GUI{
    private final Stage stage;
    private final HomePage homepage;
    private VBox cartVbox;
    /**
     * Constructor
     */
    public CreateCategoryPage(Stage stage, HomePage homepage){
        this.stage = stage;
        this.homepage = homepage;
        this.cartVbox = new VBox();
    }
    /**
     * Creates CreateAccountPage Scene
     *
     * @return         CreateAccountPage Scene
     */
    public Scene createScene() {
        Label nameLabel = new Label("Insert Category name: ");
        final TextField nameField = new TextField();
        nameField.setMaxWidth(200);
        HBox hboxName = new HBox(nameLabel, nameField);
        Label iDLabel = new Label("Insert Category ID: ");
        final TextField idField = new TextField();
        idField.setMaxWidth(100);
        HBox hboxID = new HBox(iDLabel, idField);
        Label errorLabel = homepage.bigLabelMaker("");
        Button backButton = new Button("Cancel");
        backButton.setOnAction(event -> stage.setScene(homepage.createAdminScene()));
        Button createItemButton = new Button("Create Category");
        createItemButton.setOnAction(t -> {
            String name = nameField.getText();
            int ID = Integer.parseInt(idField.getText());
            JSONItems obj = new JSONItems();
            ArrayList<String> nameList = obj.CategoryNameCheck();
            HashMap<Integer, Category> itemMap = obj.getCategories();
            if (itemMap.containsKey(ID) && nameList.contains(name)) {
                errorLabel.setText("Category name and ID already exist please retry");
                nameField.clear();
                idField.clear();
            } else if (itemMap.containsKey(ID) && !nameList.contains(name)){
                errorLabel.setText("Category ID already exist please retry");
                idField.clear();
            } else if (!itemMap.containsKey(ID) && nameList.contains(name)){
                errorLabel.setText("Category already exist please retry");
                nameField.clear();
            } else {
                obj.createCategory(name, ID);
                stage.setScene(homepage.createAdminScene());
            }
        });
        cartVbox = vboxCategoryCreator(cartVbox);
        VBox vbox = new VBox(hboxName, hboxID, createItemButton);
        vbox.setSpacing(10);
        BorderPane bp = new BorderPane();
        bp.setTop(backButton);
        vbox.setAlignment(Pos.TOP_CENTER);
        bp.setCenter(vbox);
        bp.setBottom(errorLabel);
        BorderPane.setMargin(vbox, new Insets(10));
        bp.setRight(cartVbox);
        return new Scene(bp, 600, 400);
    }
    /**
     * Creates Vbox containing the details of a category
     * @param ID     Id of category to create vbox of
     * @param category    category to create vbox of
     * @return         vbox containing a categories details
     */
    public VBox vboxCategoryHelper(int ID, Category category){
        VBox vboxNew = new VBox();
        String name = category.getCategoryName();
        Label nameLabel = homepage.labelStandardMaker(name);
        Label IDLabel = homepage.labelStandardMaker(ID + ": ");
        HBox quantityBox = new HBox(IDLabel, nameLabel);
        quantityBox.setSpacing(5);
        quantityBox.setAlignment(Pos.CENTER);
        vboxNew.getChildren().add(quantityBox);
        vboxNew.setAlignment(Pos.CENTER);
        vboxNew.setStyle("-fx-border-color:black;");
        return vboxNew;
    }
    /**
     * Creates Vbox all available categories
     * @param vbox    Parent node
     * @return         vbox containing details of all categories
     */
    public VBox vboxCategoryCreator(VBox vbox){
        vbox.getChildren().clear();
        VBox vboxCategories = new VBox();
        JSONItems obj = new JSONItems();
        HashMap<Integer, Category> categoryList = obj.getCategories();
        for (HashMap.Entry<Integer, Category> entry : categoryList.entrySet()) {
            int ID = entry.getKey();
            Category category = entry.getValue();
            vboxCategories.getChildren().add(vboxCategoryHelper(ID, category));
        }
        Label currentCategories = homepage.bigLabelMaker("Current Categories");
        vbox.getChildren().addAll(currentCategories, vboxCategories);
        return vbox;
    }
}