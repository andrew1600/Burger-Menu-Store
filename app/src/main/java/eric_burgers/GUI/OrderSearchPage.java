package eric_burgers.GUI;
import eric_burgers.JSONItems;
import eric_burgers.objects.Order;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Objects;
/**
 * allows users to search for their orders
 */
public class OrderSearchPage implements GUI {
    private final Stage stage;
    private final HomePage homepage;
    private VBox cartVbox;
    private boolean error;
    /**
     * Constructor
     */
    public OrderSearchPage(Stage stage, HomePage homepage) {
        this.stage = stage;
        this.homepage = homepage;
    }
    /**
     * Creates OrderSearchPage Scene
     *
     * @return         OrderSearchPage Scene
     */
    public Scene createScene() {
        Button backButton = homepage.mediumButtonMaker("Back");
        backButton.setOnAction(event -> stage.setScene(homepage.createScene()));
        Label orderNumberLabel = homepage.bigLabelMaker("Please Enter Your Order Number");
        Label errorLabel = homepage.labelStandardMaker("Invalid Order Number");
        errorLabel.setVisible(error);
        TextField orderNumberField = new TextField();
        orderNumberField.setMaxWidth(100);
        cartVbox = new VBox();
        Button getHistoryButton = homepage.mediumButtonMaker("Search");
        getHistoryButton.setOnAction(event -> {
            String input = orderNumberField.getText();
            if(!Objects.equals(input, "")) {
                int orderNum = Integer.parseInt(input);
                JSONItems obj = new JSONItems();
                if (!obj.getOrderHistory().containsKey(orderNum)) {
                    error = true;
                    errorLabel.setVisible(true);
                    cartVbox.getChildren().clear();
                } else {
                    Order order = obj.getOrderHistory().get(orderNum);
                    cartVbox = homepage.vboxorderHistoryCreator(cartVbox, order);
                    Label dateLabel = homepage.labelStandardMaker(order.getTime());
                    cartVbox.getChildren().add(dateLabel);
                }
            } else{
                error = true;
                errorLabel.setVisible(true);
                cartVbox.getChildren().clear();
            }
        });
        VBox stack = new VBox(10, orderNumberLabel, orderNumberField, getHistoryButton, errorLabel);
        stack.setAlignment(Pos.CENTER);
        Label dateLabel = homepage.labelStandardMaker("");
        cartVbox.getChildren().add(dateLabel);
        BorderPane bp = new BorderPane();
        bp.setTop(backButton);
        bp.setCenter(stack);
        cartVbox.setAlignment(Pos.CENTER);
        bp.setRight(cartVbox);
        return new Scene(bp, 600, 400);
    }
}