package eric_burgers.GUI;
import eric_burgers.JSONItems;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
/**
 * Contains functionality for making a new admin account
 */
public class CreateAccountPage implements GUI{
    private final Stage stage;
    private String username;
    private String password;
    private final HomePage homepage;

    /**
     * Constructor
     */
    public CreateAccountPage(Stage stage, HomePage homepage){
        this.stage = stage;
        this.homepage = homepage;
    }
    /**
     * Creates CreateAccountPage Scene
     *
     * @return         CreateAccountPage Scene
     */
    public Scene createScene() {
        Text text = new Text();
        Label userLabel = new Label("Insert username:");
        final TextField usernameField = new TextField();
        usernameField.setMaxWidth(200);
        Label passwordLabel = new Label("Insert Password");
        final PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(200);
        Button createAccountButton = new Button("create account");
        createAccountButton.setOnAction(t -> {
            username = usernameField.getText();
            password = passwordField.getText();
            JSONItems obj = new JSONItems();
            HashMap<String, String> adminMap = obj.getAdmins();
            if(adminMap.containsKey(username)){
                text.setText("Username already registered");
                usernameField.clear();
                passwordField.clear();
            }
            else {
                obj = new JSONItems();
                obj.addAdmin(username, password);
                stage.setScene(homepage.createAdminScene());
            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> stage.setScene(homepage.createAdminScene()));
        VBox root = new VBox(backButton, userLabel,usernameField, passwordLabel, passwordField, createAccountButton, text);
        return new Scene(root, 600, 400);
    }

}
