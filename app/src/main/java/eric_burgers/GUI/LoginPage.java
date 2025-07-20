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
 * Contains functionality for logging in
 */
public class LoginPage implements GUI{
    private final Stage stage;
    private String username;
    private String password;
    private final HomePage homepage;
    /**
     * Constructor
     */
    public LoginPage(Stage stage, HomePage homepage){
        this.stage = stage;
        this.homepage = homepage;
    }
    /**
     * Creates LoginPage Scene
     *
     * @return         LoginPage Scene
     */
    public Scene createScene() {
        VBox vbox = new VBox();
        Text text = new Text();
        Label userLabel = new Label("Insert username:");
        final TextField usernameField = new TextField();
        usernameField.setMaxWidth(200);
        Label passwordLabel = new Label("Insert Password");
        final PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(200);
        Button createAccountButton = new Button("Login");
        createAccountButton.setOnAction(t -> {
            JSONItems obj = new JSONItems();
            HashMap<String, String> adminMap = obj.getAdmins();
            username = usernameField.getText();
            password = passwordField.getText();
            if(adminMap.containsKey(username)){
                if(adminMap.get(username).equals(password)){
                    homepage.loggedInStatus(true);
                    homepage.setUsername(username);
                    stage.setScene(homepage.createScene());
                }
                else{
                    text.setText("Password Incorrect");
                    usernameField.clear();
                }
            }
            else{
                text.setText("Cannot find username, please register account");
                usernameField.clear();
                passwordField.clear();
            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> stage.setScene(homepage.createScene()));
        vbox.getChildren().addAll(backButton, userLabel,usernameField,passwordLabel, passwordField, createAccountButton, text);
        return new Scene(vbox, 600, 400);
    }
}

