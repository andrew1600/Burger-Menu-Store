package eric_burgers.GUI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * Contains functionality for editing item
 */
public class EditItemPage implements GUI{
    private final Stage stage;
    private final HomePage homepage;
    /**
     * Constructor
     */
    public EditItemPage(Stage stage, HomePage homepage){
        this.stage = stage;
        this.homepage = homepage;
    }
    /**
     * Creates EditItemPage Scene
     *
     * @return         EditItemPage Scene
     */
    public Scene createScene() {
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> stage.setScene(homepage.createAdminScene()));
        GridPane grid = new GridPane();
        homepage.gridCreator(grid, 1);
        BorderPane bp = new BorderPane();
        bp.setCenter(grid);
        bp.setTop(backButton);
        return new Scene(bp,600,400);
    }
}
