package eric_burgers.GUI;
import eric_burgers.objects.Item1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;
/**
 * CartPage contains functionality for displaying the cart
 */
public class CartPage implements GUI {
    private final Stage stage;
    private final HomePage homePage;
    private VBox cartVbox;
    private Button payButton;
    /**
     * Constructor
     */
    public CartPage(Stage stage, HomePage homePage) {
        this.stage = stage;
        this.homePage = homePage;
        cartVbox = new VBox();
    }
    /**
     * Creates Vbox containing an item and buttons to remove or increase quantity
     * @param item     Item to create vbox of
     * @param parent    Parent node
     * @return         vbox containing item detaisl
     */

    private VBox vboxCreator(Item1 item, VBox parent){
        VBox vboxNew = new VBox();
        int quantity = homePage.getCartList().get(item.getID());
        Label nameLabel = new Label(item.getName());
        Label priceLabel = new Label("$" + homePage.getIndividualPrice(item.getID(), homePage.getCartList()));
        Label quantLabel = new Label(String.valueOf(quantity));
        Button minus = new Button("-");
        minus.setOnAction(event -> {
            if(Integer.parseInt(quantLabel.getText()) == 1){
                homePage.removeFromCart(item.getID());
                if(homePage.getCartList().isEmpty()){
                    parent.getChildren().clear();
                    payButton.setVisible(false);
                }
                else{
                    vboxNew.getChildren().clear();
                    vboxNew.setStyle("-fx-border-color:null;");
                }
            }
            else{
                homePage.removeFromCart(item.getID());
                int value = Integer.parseInt(quantLabel.getText());
                quantLabel.setText(String.valueOf(value - 1));
                priceLabel.setText("$" + homePage.getIndividualPrice(item.getID(), homePage.getCartList()));
            }
            cartVbox = homePage.vboxCartItemsCreator(cartVbox, homePage.getCartList());
        });
        Button add = new Button("+");
        add.setOnAction(event -> {
            homePage.addToCart(item.getID());
            int value = Integer.parseInt(quantLabel.getText());
            quantLabel.setText(String.valueOf(value + 1));
            priceLabel.setText("$" + item.getPrice() * (value + 1));
            cartVbox = homePage.vboxCartItemsCreator(cartVbox, homePage.getCartList());
            payButton.setVisible(true);
        });
        HBox quantityBox = new HBox(3, minus,quantLabel, add);
        quantityBox.setAlignment(Pos.CENTER);
        quantityBox.setPadding(new Insets(5));
        vboxNew.getChildren().addAll(nameLabel, priceLabel, quantityBox);
        vboxNew.setStyle("-fx-border-color:black;");
        vboxNew.setAlignment(Pos.CENTER);
        vboxNew.setMaxWidth(100);
        return vboxNew;
    }
    /**
     * Creates CartPage Scene
     *
     * @return         CartPage Scene
     */
    public Scene createScene() {
        BorderPane borderPane = new BorderPane();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        for (HashMap.Entry<Integer, Integer> entry : homePage.getCartList().entrySet()) {
            int key = entry.getKey();
            vbox.getChildren().add(vboxCreator(homePage.IdChecker(key), vbox));
            vbox.setSpacing(5);
        }
        Button backButton = new Button("Continue Shopping");
        backButton.setOnAction(event -> stage.setScene(homePage.createMenuPageScene()));
        ObservableList<String> deliveryOptions = FXCollections.observableArrayList("Pickup", "Delivery");
        ComboBox comboBox = new ComboBox(deliveryOptions);
        comboBox.setItems(deliveryOptions);
        comboBox.setVisibleRowCount(5);
        comboBox.getSelectionModel().selectFirst();
        VBox vboxdelivery = new VBox();
        Label deliveryLabel = homePage.labelStandardMaker("Please Enter Delivery Address");
        TextField deliveryField = new TextField();
        vboxdelivery.getChildren().addAll(deliveryLabel, deliveryField);
        vboxdelivery.setAlignment(Pos.CENTER);
        vboxdelivery.setVisible(false);
        vboxdelivery.setSpacing(5);
        comboBox.setOnAction(event -> vboxdelivery.setVisible("Delivery".equals(comboBox.getValue())));
        payButton = homePage.mediumButtonMaker("Pay Now");
        payButton.setOnAction(event -> stage.setScene(homePage.createPaymentScene()));
        payButton.setVisible(!homePage.getCartList().isEmpty());
        cartVbox = homePage.vboxCartItemsCreator(cartVbox, homePage.getCartList());
        VBox rightVbox = new VBox(cartVbox, comboBox, vboxdelivery, payButton);
        rightVbox.setSpacing(10);
        rightVbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().addAll(backButton);
        borderPane.setTop(hbox);
        BorderPane.setMargin(hbox, new Insets(10,0,20,10));
        borderPane.setCenter(vbox);
        BorderPane.setMargin(vbox, new Insets(10));
        borderPane.setRight(rightVbox);
        BorderPane.setMargin(rightVbox, new Insets(10));
        return new Scene(borderPane, 600, 400);
    }
}
