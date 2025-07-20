package eric_burgers.GUI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * Contains functionality for editing category
 */
public class EditCategoryPage implements GUI{
    private final Stage stage;
    private final HomePage homepage;
    /**
     * Constructor
     */
    public EditCategoryPage(Stage stage, HomePage homepage){
        this.stage = stage;
        this.homepage = homepage;
    }
    /**
     * Creates EditCategoryPage Scene
     *
     * @return         EditCategoryPage Scene
     */
    public Scene createScene() {
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> stage.setScene(homepage.createAdminScene()));
        GridPane grid = new GridPane();
        homepage.gridCreator(grid, 2);
        BorderPane bp = new BorderPane();
        bp.setCenter(grid);
        bp.setTop(backButton);
        return new Scene(bp,600,400);
    }
}