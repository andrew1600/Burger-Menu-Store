package eric_burgers.GUI;
import eric_burgers.JSONItems;
import eric_burgers.objects.Category;
import eric_burgers.objects.Item1;
import eric_burgers.objects.Order;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * The HomePage class initialises the homepage and all other pages. It acts as the center of the GUI.
 */
public class HomePage implements GUI{
    private final Stage stage;
    private final CreateAccountPage accountPage;
    private final CartPage cartpage;
    private final LoginPage loginPage;
    private final MenuPage menuPage;
    private final HashMap<Integer, Integer> cartList;
    private final PaymentPage paymentPage;
    private int orderNumber;
    private final OrderConfirmationPage orderConfirmationPage;
    private final AdminPage adminPage;
    private final NewItemPage newItemPage;
    private boolean loggedIn;
    private String username;
    private final EditItemPage editItemPage;
    private final OrderHistoryPage orderHistoryPage;
    private final OrderSearchPage orderSearchPage;
    private final CreateCategoryPage createCategoryPage;
    private final FurtherEditCategoryPage furtherEditCategoryPage;
    private final EditCategoryPage editCategoryPage;
    private final ItemViewPage itemViewPage;
    private int newCategory;
    /**
     * Constructor of HomePage
     *
     * @param  stage   The stage that all the scenes will be displayed on
     */
    public HomePage(Stage stage){
        this.stage = stage;
        this.cartpage = new CartPage(stage, this);
        this.accountPage = new CreateAccountPage(stage, this);
        this.loginPage = new LoginPage(stage, this);
        this.menuPage = new MenuPage(stage, this);
        this.cartList = new HashMap<>();
        this.paymentPage = new PaymentPage(stage, this);
        this.orderConfirmationPage = new OrderConfirmationPage(stage, this);
        this.adminPage = new AdminPage(stage, this);
        this.newItemPage = new NewItemPage(stage, this);
        this.editItemPage = new EditItemPage(stage, this);
        this.orderHistoryPage = new OrderHistoryPage(stage, this);
        this.orderSearchPage = new OrderSearchPage(stage, this);
        this.createCategoryPage = new CreateCategoryPage(stage, this);
        this.furtherEditCategoryPage = new FurtherEditCategoryPage(stage, this);
        this.editCategoryPage = new EditCategoryPage(stage, this);
        this.itemViewPage = new ItemViewPage(stage, this);
    }
    /**
     * Creates the homepage scene
     *
     * @return         the newly created scene
     */
    public Scene createScene(){
        VBox vb = new VBox(10);
        Button loginActionButton= new Button("Admin Login");
        loginActionButton.setVisible(!loggedIn);
        loginActionButton.setOnAction(event -> stage.setScene(loginPage.createScene()));
        Button MenuActionButton = new Button("Menu");
        MenuActionButton.setOnAction(event -> stage.setScene(menuPage.createScene()));
        Button adminActionButton = new Button("Admin Page");
        adminActionButton.setVisible(loggedIn);
        adminActionButton.setOnAction(event -> stage.setScene(adminPage.createScene()));
        Label welcomeLabel = bigLabelMaker("Welcome " + username);
        welcomeLabel.setVisible(loggedIn);
        Button logoutActionButton = new Button("LogOut");
        logoutActionButton.setVisible(loggedIn);
        logoutActionButton.setOnAction(event -> {
            loggedInStatus(false);
            stage.setScene(createScene());
        });
        Button OrderSearchButton= mediumButtonMaker("Search For Previous Order");
        OrderSearchButton.setOnAction(event -> stage.setScene(orderSearchPage.createScene()));
        BorderPane bp = new BorderPane();
        HBox hbox = new HBox(welcomeLabel);
        hbox.setAlignment(Pos.CENTER);
        bp.setTop(hbox);
        vb.getChildren().addAll(adminActionButton,MenuActionButton,loginActionButton);
        vb.setAlignment(Pos.TOP_CENTER);
        bp.setCenter(vb);
        HBox hboxlogout = new HBox(logoutActionButton);
        VBox vboxBottom = new VBox(10, hboxlogout, OrderSearchButton);
        hboxlogout.setAlignment(Pos.TOP_CENTER);
        vboxBottom.setAlignment(Pos.TOP_CENTER);
        bp.setBottom(vboxBottom);
        BorderPane.setMargin(hbox, new Insets(10,10,60,10));
        BorderPane.setMargin(vboxBottom, new Insets(10,10,60,10));
        return new Scene(bp,600,400);
    }
    /**
     * Updates the boolean status of the loggedIn variable
     * @param  loggedIn   the boolean value of whether someone has logged in
     */
    public void loggedInStatus(boolean loggedIn){
        this.loggedIn = loggedIn;
    }
    /**
     * Returns the boolean status of the loggedIn variable
     *
     * @return         the boolean status of the loggedIn variable
     */
    public boolean getLoggedInStatus(){return loggedIn;}
    /**
     * Sets the username of the currently logged in Admin
     *
     * @param  name   the amount the value should be incremented by
     * @return         the post-incremented value
     */
    public void setUsername(String name){
        username = name;
    }
    /**
     * Gets the username of the currently logged in Admin
     *
     * @return         Username of the currently logged in Admin
     */
    public String getUsername(){return username;}
    /**
     * Gets the current contents of cart
     *
     * @return         the Hashmap of the current cart with key being item ID and value being the quantity in cart
     */
    public HashMap<Integer, Integer> getCartList(){
        return this.cartList;
    }
    /**
     * Adds an item to the cart
     *
     * @param  ID   the item ID to be added to cart
     */
    public void addToCart(int ID){
        if(cartList.containsKey(ID)){
            int value = cartList.get(ID);
            cartList.put(ID, value + 1);
        } else{cartList.put(ID, 1);}
    }
    /**
     * Removes an Item from cart
     *
     * @param  ID   the item ID to be added to cart
     */
    public void removeFromCart(int ID){
        if(cartList.containsKey(ID)){
            int value = cartList.get(ID);
            if(value == 1){cartList.remove(ID);}
            else{cartList.put(ID, value - 1);}
        }
    }
    /**
     * gets the current CartPage object
     *
     * @return         the current CartPage object
     */
    public CartPage getCartpage(){
        return cartpage;
    }
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createMenuPageScene(){return menuPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createPaymentScene(){return paymentPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createCartScene(){return cartpage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createConfirmationScene(){return orderConfirmationPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createAdminScene(){return adminPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createNewItemScene(){return newItemPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createAccountPageScene(){return accountPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createEditItemPageScene(){return editItemPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createOrderHistoryPageScene(){return orderHistoryPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createCreateCategoryPage(){return createCategoryPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createFurtherEditCategoryPage(){return furtherEditCategoryPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createEditCategoryPage(){return editCategoryPage.createScene();}
    /**
     * Creates a scene of the desired page
     *
     * @return         the new scene
     */
    public Scene createItemViewPage(){return itemViewPage.createScene();}
    /**
     * sets the values in the item view page to the desired item
     *
     * @param  item   the item to be displayed
     */
    public void setItemViewPageItem(Item1 item){itemViewPage.setItem(item);}

    /**
     * Returns the item associated with an ID
     *
     * @param  ID   the ID to search for
     * @return         the post-incremented value
     */
    public Item1 IdChecker(int ID){
        JSONItems obj= new JSONItems();
        return obj.idChecker(ID);
    }
    /**
     * returns the total price of a hashmap of items and quantity
     *
     * @param  itemlist   the hashmap of items to get the value of
     * @return         the total price of the hashmap
     */
    public int getTotalPrice(HashMap<Integer, Integer> itemlist) {
        int totalPrice = 0;
        for (HashMap.Entry<Integer, Integer> entry : itemlist.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            Item1 currentItem = IdChecker(key);
            int currentPrice = currentItem.getPrice();
            totalPrice = totalPrice + (currentPrice * value);
        }
        return totalPrice;
    }
    /**
     * gets the price of a quanitity of items
     *
     * @param  ID   the ID to search for
     * @param  itemList the hashmap of items
     * @return         the price of an item multiplied by it's quantity
     */
    public int getIndividualPrice(int ID, HashMap<Integer, Integer> itemList){
        Item1 item = IdChecker(ID);
        int currentPrice = item.getPrice();
        return currentPrice * itemList.get(ID);
    }
    /**
     * Helper method to VboxCartItemsCreator. Creates individual boxes for items in cart
     *
     * @param  ID   the item ID to search for
     * @param  quant   the quantity of that item
     * @param  itemList   hashmap of cart
     * @return         a Vbox containing the price and quantity of an item in cart
     */
    public VBox vboxCartItemsHelper(int ID, int quant, HashMap<Integer, Integer> itemList){
        VBox vboxNew = new VBox();
        Item1 item = IdChecker(ID);
        Label nameLabel = new Label(item.getName());
        Label priceLabel = new Label("Price: $" + getIndividualPrice(ID, itemList));
        return getVboxLabels(quant, vboxNew, nameLabel, priceLabel);
    }
    /**
     * Does alignment and styling for vbox
     *
     * @param  quant   quantity of an item
     * @param  vboxNew   vbox to style
     * @param  nameLabel   label of item name
     * @param  priceLabel   label of price
     * @return         styled vbox
     */

    private VBox getVboxLabels(int quant, VBox vboxNew, Label nameLabel, Label priceLabel) {
        Label quantLabel = new Label("Qty: " + quant);
        HBox quantityBox = new HBox(quantLabel, priceLabel);
        quantityBox.setSpacing(5);
        quantityBox.setAlignment(Pos.CENTER);
        vboxNew.getChildren().addAll(nameLabel, quantityBox);
        vboxNew.setAlignment(Pos.CENTER);
        vboxNew.setStyle("-fx-border-color:black;");
        return vboxNew;
    }
    /**
     * Creates a vbox with all the items contained within itemList
     *
     * @param  vbox   the Vbox to add all the cart items to
     * @param  itemList   a hashmap with item id and quantity
     * @return         a vbox containing vbox for each item
     */

    public VBox vboxCartItemsCreator(VBox vbox, HashMap<Integer, Integer> itemList){
        vbox.getChildren().clear();
        VBox vboxItems = new VBox();
        for (HashMap.Entry<Integer, Integer> entry : itemList.entrySet()) {
            int ID = entry.getKey();
            int quant = entry.getValue();
            vboxItems.getChildren().add(vboxCartItemsHelper(ID, quant, itemList));
        }
        vboxItems.setSpacing(5);
        Label totalPriceLabel = labelStandardMaker("Cart total: $" + getTotalPrice(itemList));
        return getVboxItemLabels(vbox, vboxItems, totalPriceLabel);
    }
    /**
     * Creates a vbox with all the items contained within itemList
     *
     * @param  vbox   the Vbox to add all the cart items to
     * @param  vboxItems   a vbox containing all the cart items
     * @param  totalPriceLabel   a label containing the total price
     * @return         a vbox containing vbox for each item
     */
    private VBox getVboxItemLabels(VBox vbox, VBox vboxItems, Label totalPriceLabel) {
        totalPriceLabel.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(vboxItems, totalPriceLabel);
        vbox.setSpacing(10);
        vbox.setPrefWidth(150);
        vbox.setMaxWidth(150);
        vbox.setAlignment(Pos.TOP_CENTER);
        return vbox;
    }
    /**
     * returns the next available order number
     *
     * @return         the next available order number
     */
    public int getOrderNumber() {
        JSONItems json = new JSONItems();
        this.orderNumber = json.getAmountOfOrders();
        return orderNumber + 1;
    }
    /**
     * updates current order number
     */
    public void updateOrderNumber(){
        JSONItems json = new JSONItems();
        this.orderNumber = json.getAmountOfOrders();
    }
    /**
     * Empties the cart upon purchase
     */
    public void reset(){
        cartList.clear();
    }
    /**
     * label maker for make big labels with consistent style and size
     *
     * @param  text   the text for the label to display
     * @return         a new styled label
     */
    public Label bigLabelMaker(String text){
        Label label = new Label(text);
        label.setFont(new Font("Ariel", 24));
        return label;
    }
    /**
     * button maker for make medium buttons with consistent style and size
     *
     * @param  text   the text for the button to display
     * @return         a new styled button
     */
    public Button mediumButtonMaker(String text){
        Button button = new Button(text);
        button.setPrefWidth(200);
        return button;
    }
    /**
     * label maker for make standard sized labels with consistent style and size
     *
     * @param  text   the text for the label to display
     * @return         a new styled label
     */
    public Label labelStandardMaker(String text){
        Label label = new Label(text);
        label.setFont(new Font("Arial", 14));
        //label.setStyle("-fx-border-color:black;");
        return label;
    }
    /**
     * sorts a hashmap by key
     *
     * @param  map   map of item IDs and items
     * @return         a sorted map
     */
    public TreeMap<Integer, Item1> sortbykey(HashMap<Integer, Item1> map)
    {
        return new TreeMap<>(map);
    }
    /**
     * sorts a hashmap by key
     *
     * @param  map   map of order IDs and orders
     * @return         a sorted map
     */
    public TreeMap<Integer, Order> sortbykeyOrder(HashMap<Integer, Order> map)
    {
        return new TreeMap<>(map);
    }
    /**
     * dynamic grid layout creator
     *
     * @param  grid   a grid to use as the parent node
     * @param  source   specifies what sort of grid it shoud make. 1 is for items and 2 is for categories
     */
    public void gridCreator(GridPane grid, int source){
        JSONItems obj = new JSONItems();
        int row = 0;
        int col = 0;
        if(source == 1) {
            Map<Integer, Item1> map = sortbykey(obj.itemCheck());
            for (Map.Entry<Integer, Item1> entry : map.entrySet()) {
                int ID = entry.getKey();
                Item1 item = entry.getValue();
                grid.add(vboxEdititemsCreator(ID, item), col, row);
                col += 1;
                if (col == 2) {
                    col = 0;
                    row += 1;
                }

            }
        }
        else{
            Map<Integer, Category> map = sortbykeyCategory(obj.getCategories());
            for (Map.Entry<Integer, Category> entry : map.entrySet()) {
                int ID = entry.getKey();
                Category category = entry.getValue();
                grid.add(vboxEditCategoryCreator(ID, category), col, row);
                col += 1;
                if (col == 2) {
                    col = 0;
                    row += 1;
                }

            }
        }
    }
    /**
     * Creates a vbox of an item that can be edited
     *
     * @param  ID   the ID of an item that can be edited
     * @param  item   the item that can be edited
     * @return         a vbox containing button options to remove or edit an item
     */
    public VBox vboxEdititemsCreator(int ID, Item1 item){
        VBox vboxNew = new VBox();
        JSONItems obj = new JSONItems();
        String name = item.getName();
        int price = item.getPrice();
        Label nameLabel = labelStandardMaker(name);
        Label IDLabel = labelStandardMaker("ID: " + ID);
        HBox nameHbox = new HBox(nameLabel, IDLabel);
        Label priceLabel = labelStandardMaker(" Price: $" + price);
        Label categoryLabel = labelStandardMaker("Category: " + obj.CategoryIDSearch(item.getCategory()));
        HBox quantityBox = new HBox(priceLabel, categoryLabel);
        quantityBox.setSpacing(5);
        quantityBox.setAlignment(Pos.CENTER);
        nameHbox.setSpacing(5);
        nameHbox.setAlignment(Pos.CENTER);
        Button editButton = mediumButtonMaker("Edit");
        editButton.setOnAction(event -> {
            furtherEditCategoryPage.setItemtoChange(item);
            furtherEditCategoryPage.setPageType(true);
            stage.setScene(createFurtherEditCategoryPage());
        });
        Button removeButton = mediumButtonMaker("Remove Item");
        removeButton.setOnAction(event -> {
            JSONItems obj1 = new JSONItems();
            obj1.removeItem(ID);
            stage.setScene(editItemPage.createScene());
        });
        HBox hboxButtons = new HBox(editButton, removeButton);
        vboxNew.getChildren().addAll(nameHbox, quantityBox, hboxButtons);
        vboxNew.setAlignment(Pos.CENTER);
        return vboxNew;
        /**
         * Creates a vbox of a category that can be edited
         *
         * @param  ID   the ID of a category that can be edited
         * @param  category   the category that can be edited
         * @return         a vbox containing button options to remove or edit a category
         */
    }public VBox vboxEditCategoryCreator(int ID, Category category){
        VBox vboxNew = new VBox();
        String name = category.getCategoryName();
        Label nameLabel = labelStandardMaker(name);
        Label IDLabel = labelStandardMaker("ID: " + ID);
        HBox nameHbox = new HBox(nameLabel, IDLabel);
        nameHbox.setSpacing(5);
        nameHbox.setAlignment(Pos.CENTER);
        Button editButton = mediumButtonMaker("Edit Item");
        editButton.setOnAction(event -> {
            furtherEditCategoryPage.setCategoryToChange(category);
            furtherEditCategoryPage.setPageType(false);
            stage.setScene(createFurtherEditCategoryPage());
        });
        Button removeButton = mediumButtonMaker("Remove Item");
        removeButton.setOnAction(event -> {
            JSONItems obj1 = new JSONItems();
            obj1.removeCategory(ID);
            stage.setScene(editCategoryPage.createScene());
        });
        HBox hboxButtons = new HBox(editButton, removeButton);
        vboxNew.getChildren().addAll(nameHbox, hboxButtons);
        vboxNew.setAlignment(Pos.CENTER);
        return vboxNew;
    }
    /**
     * helper to creating order history Vboxes
     *
     * @param  ID   the ID of an item
     * @param  quant   the quantity of that item
     * @param  order   an order that contains the item
     * @return         a vbox containing an items price and name
     */
    public VBox vboxOderHistoryHelper(int ID, int quant, Order order){
        VBox vboxNew = new VBox();
        Item1 item = IdChecker(ID);
        Label nameLabel = new Label(item.getName());
        Label priceLabel = new Label("Price: $" + order.getItemPrices().get(ID) * quant);
        return getVboxLabels(quant, vboxNew, nameLabel, priceLabel);
    }
    /**
     * creates a vbox containing vboxes of items within an order
     *
     * @param  vbox   a vbox to serve as parent
     * @param  order   an Order
     * @return         a vbox containing vboxes of items within an order
     */
    public VBox vboxorderHistoryCreator(VBox vbox, Order order){
        vbox.getChildren().clear();
        VBox vboxItems = new VBox();
        int total = order.getTotalAmount();
        for (HashMap.Entry<Integer, Integer> entry : order.getMap().entrySet()) {
            int itemID = entry.getKey();
            int quant = entry.getValue();
            vboxItems.getChildren().add(vboxOderHistoryHelper(itemID, quant, order));
        }
        vboxItems.setSpacing(5);
        Label totalPriceLabel = labelStandardMaker("Cart total: $" + total);
        return getVboxItemLabels(vbox, vboxItems, totalPriceLabel);
    }
    /**
     * sorts a hashmap by key
     *
     * @param  map   map of order IDs and orders
     * @return         a sorted map
     */
    public TreeMap<Integer, Category> sortbykeyCategory(HashMap<Integer, Category> map) {return new TreeMap<>(map);}
    /**
     * makes a grid of available categories
     *
     * @param  grid   a grid to use as the parent node
     * @param  currentCategory   the label displaying the name of the current category selected
     * @param  categoryId   the current category selected
     * @return the category ID of the newly selected category
     */
    public int gridCreatorCategory(GridPane grid, Label currentCategory, int categoryId) {
        JSONItems obj = new JSONItems();
        newCategory = categoryId;
        int row = 0;
        int col = 0;
        Map<Integer, Category> map = sortbykeyCategory(obj.getCategories());
        for (Map.Entry<Integer, Category> entry : map.entrySet()) {
            int ID = entry.getKey();
            Category category = entry.getValue();
            Button button = mediumButtonMaker(ID + ": " + category.getCategoryName());
            button.setOnAction(t -> {
                currentCategory.setText("Select Category. Currently selected is " + category.getCategoryName());
                newCategory = ID;
                newItemPage.setNewCategory(newCategory);
            });
            grid.add(button, col, row);
            col += 1;
            if (col == 3) {
                col = 0;
                row += 1;
            }
        } return newCategory;
    }
}
