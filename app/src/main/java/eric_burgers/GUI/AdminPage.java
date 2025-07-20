package eric_burgers.GUI;
import eric_burgers.JSONItems;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * AdminPage contains admin functionality
 */
public class AdminPage implements GUI{
    private final Stage stage;
    private final HomePage homepage;
    /**
     * Constructor
     */
    public AdminPage(Stage stage, HomePage homepage){
        this.stage = stage;
        this.homepage = homepage;
    }
    /**
     * Creates AdminPage Scene
     *
     * @return         AdminPage Scene
     */
    public Scene createScene() {
        JSONItems json = new JSONItems();
        Label numberOfOrdersLabel= homepage.bigLabelMaker("Total orders placed: " + json.getAmountOfOrders());
        Button eraseActionButton = homepage.mediumButtonMaker("Erase History");
        eraseActionButton.setOnAction(event -> {
            JSONItems json1 = new JSONItems();
            json1.ClearOrderHistory();
            homepage.updateOrderNumber();
            numberOfOrdersLabel.setText("Total orders placed: " + json1.getAmountOfOrders());
        });
        Button backButton = homepage.mediumButtonMaker("Home");
        backButton.setOnAction(event -> stage.setScene(homepage.createScene()));
        Button NewItemButton = homepage.mediumButtonMaker("New Item");
        NewItemButton.setOnAction(event -> stage.setScene(homepage.createNewItemScene()));
        Button createNewAdmin = homepage.mediumButtonMaker("Register New Admin");
        createNewAdmin.setOnAction(event -> stage.setScene(homepage.createAccountPageScene()));
        Button editItemButton = homepage.mediumButtonMaker("Edit Item");
        editItemButton.setOnAction(event -> stage.setScene(homepage.createEditItemPageScene()));
        Button orderHistoryButton = homepage.mediumButtonMaker("Application Order History");
        orderHistoryButton.setOnAction(event -> stage.setScene(homepage.createOrderHistoryPageScene()));
        Button createCategoryButton = homepage.mediumButtonMaker("Create Category");
        createCategoryButton.setOnAction(event -> stage.setScene(homepage.createCreateCategoryPage()));
        Button editCategoryButton = homepage.mediumButtonMaker("Edit Category");
        editCategoryButton.setOnAction(event -> stage.setScene(homepage.createEditCategoryPage()));

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.add(NewItemButton, 0, 0);
        gp.add(editItemButton, 1, 0);
        gp.add(createNewAdmin, 0, 1);
        gp.add(orderHistoryButton, 1, 1);
        gp.add(createCategoryButton, 0, 2);
        gp.add(editCategoryButton, 1, 2);
        gp.setHgap(10);
        gp.setVgap(10);
        BorderPane bp = new BorderPane();
        VBox vboxOrders = new VBox(eraseActionButton, numberOfOrdersLabel);
        HBox performanceBox = new HBox(vboxOrders);
        performanceBox.setAlignment(Pos.CENTER);
        bp.setTop(backButton);
        bp.setBottom(performanceBox);
        bp.setCenter(gp);
        return new Scene(bp, 600, 400);
    }
}
