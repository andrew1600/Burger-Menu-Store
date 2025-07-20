package eric_burgers.GUI;
import eric_burgers.JSONItems;
import eric_burgers.objects.Category;
import eric_burgers.objects.Item1;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.HashMap;
/**
 * Contains functionality for displaying the menu
 */
public class MenuPage implements GUI {
    private final Stage stage;
    private final HomePage homePage;
    private VBox cartVbox;
    private int totalPrice;
    private final HashMap<Integer, Integer> cartList;
    private final CartPage cartpage;
    private int currentCategory;
    private boolean emptyCart;
    private GridPane grid;
    private Button cartButton;
    /**
     * Constructor
     */
    public MenuPage(Stage stage, HomePage homePage) {
        this.stage = stage;
        this.homePage = homePage;
        this.cartList = new HashMap<>();
        this.cartpage = homePage.getCartpage();
        cartVbox = new VBox();
        grid = new GridPane();
        currentCategory = 1;
    }
    /**
     * Creates MenuPage Scene
     *
     * @return         MenuPage Scene
     */
    public Scene createScene() {
        emptyCart = homePage.getTotalPrice(homePage.getCartList()) == 0;
        JSONItems obj= new JSONItems();
        obj.getCategories();
        BorderPane borderpane = new BorderPane();
        Label priceLabel = homePage.labelStandardMaker("Total Price: $" + homePage.getTotalPrice(homePage.getCartList()));
        VBox categoryVbox = new VBox(10);
        for (HashMap.Entry<Integer, Category> entry : obj.getCategories().entrySet()) {
            int key = entry.getKey();
            Button categoryButton = new Button(obj.CategoryIDSearch(key));
            categoryButton.setOnAction(event -> {
                currentCategory = key;
                grid = createMenuGrid(grid, priceLabel);
            });
            categoryVbox.getChildren().add(categoryButton);
        }
        createMenuGrid(grid, priceLabel);
        Button backButton = new Button("Home");
        backButton.setOnAction(event -> stage.setScene(homePage.createScene()));
        cartButton = new Button("Cart");
        cartButton.setOnAction(event -> stage.setScene(cartpage.createScene()));
        cartButton.setVisible(!emptyCart);
        Label contentsLabel = homePage.labelStandardMaker("Contents of Cart");
        cartVbox = homePage.vboxCartItemsCreator(cartVbox, homePage.getCartList());
        VBox rightVbox = new VBox(contentsLabel, cartVbox);
        rightVbox.setAlignment(Pos.TOP_CENTER);
        HBox hbox = new HBox(backButton, priceLabel, cartButton);
        hbox.setPadding(new Insets(10, 10, 10,10));
        hbox.setSpacing(30);
        hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        borderpane.setTop(hbox);
        BorderPane.setMargin(hbox, new Insets(10));
        borderpane.setCenter(grid);
        borderpane.setRight(rightVbox);
        BorderPane.setMargin(rightVbox, new Insets(10));
        borderpane.setLeft(categoryVbox);
        BorderPane.setMargin(categoryVbox, new Insets(10));
        return new Scene(borderpane, 600, 400);
    }
    /**
     * Creates grid that displays menu options
     * @param grid this is the parent node
     * @param priceLabel this is the label of the total price
     * @return         returns the grid
     */
    public GridPane createMenuGrid(GridPane grid, Label priceLabel) {
        grid.getChildren().clear();
        JSONItems obj= new JSONItems();
        obj.getCategories();
        HashMap<Integer, Item1> itemList1 = obj.getItemsOfCategory(currentCategory);
        int col = 0;
        int row = 0;
        for (HashMap.Entry<Integer, Item1> entry : itemList1.entrySet()) {
            Item1 currentItem = entry.getValue();
            String name = currentItem.getName();
            int price = currentItem.getPrice();
            Label nameLabel = homePage.labelStandardMaker(name);
            Button button = homePage.mediumButtonMaker("$" + price);
            button.setOnAction(event -> {
                homePage.setItemViewPageItem(currentItem);
                stage.setScene(homePage.createItemViewPage());
            });
            VBox vbox = new VBox(nameLabel, button);
            vbox.setAlignment(Pos.CENTER);
            //int row
            col = col + 1;
            grid.add(vbox, col, row);
            if (col == 2) {
                col = 0;
                row += 1;
            }
        }
        return grid;
    }
}
