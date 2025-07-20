package eric_burgers.GUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * Lets the user know their order number and order
 */
public class OrderConfirmationPage implements GUI{
    private final Stage stage;
    private final HomePage homepage;
    private VBox orderItems;
    /**
     * Constructor
     */
    public OrderConfirmationPage(Stage stage, HomePage homepage){
        this.stage = stage;
        this.homepage = homepage;
        this.orderItems = new VBox();
    }
    /**
     * Creates OrderConfirmationPage Scene
     *
     * @return         OrderConfirmationPage Scene
     */

    public Scene createScene() {
        Label congratsLabel = new Label("Congratulations Your Order Has Been Placed!!!!!");
        congratsLabel.setFont(new Font("Ariel", 24));
        Label congratsLabel2 = new Label("Your order number is " + (homepage.getOrderNumber() - 1) + " and you ordered:");
        congratsLabel2.setFont(new Font("Ariel", 24));
        orderItems = homepage.vboxCartItemsCreator(orderItems, homepage.getCartList());
        Button returnHomeButton = new Button("Return to Home");
        returnHomeButton.setOnAction(event -> {
            homepage.reset();
            stage.setScene(homepage.createScene());
        });
        orderItems.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(congratsLabel, congratsLabel2, orderItems, returnHomeButton);
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        return new Scene(vbox, 600, 400);
    }
}
