package eric_burgers.GUI;
import eric_burgers.JSONItems;
import eric_burgers.objects.Item1;
import eric_burgers.objects.Order;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;
/**
 * takes payment for order
 */
public class PaymentPage implements GUI {
    private final Stage stage;
    private final HashMap<Integer, Integer> finishedOrder;
    private final HashMap<Integer, Integer> orderPrices;
    private final HomePage homePage;
    /**
     * Constructor
     */
    public PaymentPage(Stage stage, HomePage homePage) {
        this.stage = stage;
        this.homePage = homePage;
        this.finishedOrder = new HashMap<>();
        this.orderPrices = new HashMap<>();
    }
    /**
     * Creates PaymentPage Scene
     *
     * @return         PaymentPage Scene
     */
    public Scene createScene() {
        Label cardNumberLabel = new Label("CardNumber:");
        final TextField cardNumberField = new TextField();
        cardNumberField.setMaxWidth(200);
        Label expiryLabel = new Label("ExpiryDate: ");
        final TextField expiryField = new TextField();
        expiryField.setMaxWidth(200);
        Label cvvLabel = new Label("CVV: ");
        final TextField cvvField = new TextField();
        cvvField.setMaxWidth(200);
        Button PaymentButton = new Button("Pay Now");
        PaymentButton.setOnAction(t -> {
            for (HashMap.Entry<Integer, Integer> entry : homePage.getCartList().entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                Item1 currentItem = homePage.IdChecker(key);
                finishedOrder.put(currentItem.getID(), value);
                orderPrices.put(currentItem.getID(), currentItem.getPrice());
            }
            homePage.updateOrderNumber();
            Order order = new Order(finishedOrder, homePage.getTotalPrice(homePage.getCartList()), homePage.getOrderNumber(), orderPrices);
            JSONItems obj = new JSONItems();
            obj.CreateOrderHistory(order);
            homePage.updateOrderNumber();
            stage.setScene(homePage.createConfirmationScene());
        });
        Button backButton = new Button("Back To Cart");
        backButton.setOnAction(event -> stage.setScene(homePage.createCartScene()));
        BorderPane bp = new BorderPane();
        HBox topBox = new HBox(backButton);
        HBox cardNumberBox = new HBox(cardNumberLabel, cardNumberField);
        HBox cardDetailsBox = new HBox(expiryLabel, expiryField, cvvLabel, cvvField);
        VBox vbox = new VBox(cardNumberBox, cardDetailsBox, PaymentButton);
        bp.setCenter(vbox);
        bp.setTop(topBox);
        return new Scene(bp, 600, 400);
    }
}
