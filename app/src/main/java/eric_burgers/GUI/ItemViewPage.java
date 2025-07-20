package eric_burgers.GUI;
import eric_burgers.objects.Item1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * Contains functionality for editing item
 */
public class ItemViewPage implements GUI {
    private final Stage stage;
    private final HomePage homepage;
    private String name;
    private int price;
    private int ID;
    private String description;
    private int quantity;
    /**
     * Constructor
     */
    public ItemViewPage(Stage stage, HomePage homepage) {
        this.stage = stage;
        this.homepage = homepage;
    }
    /**
     * Creates ItemViewPage Scene
     *
     * @return         ItemViewPage Scene
     */
    public Scene createScene() {
        quantity = 1;
        BorderPane bp = new BorderPane();
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> stage.setScene(homepage.createMenuPageScene()));
        Label nameLabel = homepage.bigLabelMaker(name);
        Label priceLabel = homepage.labelStandardMaker("$" + price);
        Label descriptionLabel = homepage.labelStandardMaker(description);
        ObservableList<String> quantOptions = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6","7","8","9","10");
        ComboBox comboBox = new ComboBox(quantOptions);
        comboBox.setItems(quantOptions);
        comboBox.setVisibleRowCount(5);
        comboBox.getSelectionModel().selectFirst();
        comboBox.setOnAction(event -> quantity = Integer.valueOf((String) comboBox.getValue()));
        Button button = homepage.mediumButtonMaker("Add to cart");
        button.setOnAction(event -> {
            for(int i = 0; i < quantity; i++) {
                homepage.addToCart(ID);
                stage.setScene(homepage.createMenuPageScene());
            }
       });
        HBox hboxCartAdd = new HBox(10);
        hboxCartAdd.getChildren().addAll(comboBox, button);
        hboxCartAdd.setAlignment(Pos.CENTER);
        VBox vboxContent = new VBox(nameLabel, priceLabel, descriptionLabel, hboxCartAdd);
        vboxContent.setAlignment(Pos.CENTER);
        vboxContent.setSpacing(10);
        bp.setTop(backButton);
        bp.setCenter(vboxContent);
        return new Scene(bp, 600, 400);
    }
    /**
     * sets the item to be viewed
     * @param item this is the item to be viewed
     */
    public void setItem(Item1 item){
        this.name = item.getName();
        this.price = item.getPrice();
        this.description = item.getDescription();
        this.ID = item.getID();
    }
}