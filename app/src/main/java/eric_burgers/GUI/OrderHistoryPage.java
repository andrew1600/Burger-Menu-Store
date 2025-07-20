package eric_burgers.GUI;
import eric_burgers.JSONItems;
import eric_burgers.objects.Order;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Map;
/**
 * Displays the order history for the entire app
 */
public class OrderHistoryPage implements GUI {
    private final Stage stage;
    private final HomePage homepage;
    private boolean orderSelected;
    private int currentID;
    private VBox cartVbox;
    /**
     * Constructor
     */
    public OrderHistoryPage(Stage stage, HomePage homepage) {
        this.stage = stage;
        this.homepage = homepage;
        cartVbox = new VBox();
    }
    /**
     * Creates OrderHistoryPage Scene
     *
     * @return         OrderHistoryPage Scene
     */
    public Scene createScene() {
        JSONItems json = new JSONItems();
        Button backButton = homepage.mediumButtonMaker("Back");
        backButton.setOnAction(event -> {
            orderSelected = false;
            stage.setScene(homepage.createAdminScene());
        });
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        if(orderSelected) {cartVbox = homepage.vboxorderHistoryCreator(cartVbox, json.getOrderHistory().get(currentID));}
        cartVbox.setVisible(orderSelected);
        gridCreator(gp);
        bp.setRight(cartVbox);
        bp.setCenter(gp);
        bp.setTop(backButton);
        BorderPane.setMargin(gp, new Insets(10));
        return new Scene(bp,600,400);
    }
    /**
     * Creates a grid with the order histories
     *
     * @param grid parent node
     */
    private void gridCreator(GridPane grid){
        JSONItems obj = new JSONItems();
        int row = 0;
        int col = 0;
        Map<Integer, Order> map = homepage.sortbykeyOrder(obj.getOrderHistory());
        for (Map.Entry<Integer, Order> entry : map.entrySet()) {
            int ID = entry.getKey();
            Order order = entry.getValue();
            Button getHistoryButton = homepage.mediumButtonMaker("Order ID: " + ID);
            getHistoryButton.setOnAction(event -> {
                orderSelected = true;
                currentID = ID;
                cartVbox = homepage.vboxorderHistoryCreator(cartVbox, obj.getOrderHistory().get(currentID));
                cartVbox.setVisible(orderSelected);
                Label dateLabel = homepage.labelStandardMaker(order.getTime());
                cartVbox.getChildren().add(dateLabel);
            });
            grid.add(getHistoryButton, col, row);
            col += 1;
            if(col == 2) {
                col = 0;
                row += 1;
            }
        }
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.TOP_CENTER);
    }
}