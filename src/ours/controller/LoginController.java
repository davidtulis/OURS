package ours.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ours.model.Login;
import ours.model.User;

/**
 * The LoginController Class is the control Class for the Login screen.
 *
 * @author John Harris
 * @author David Tulis Group 5
 */
public class LoginController implements Initializable {

    /**
     * User that logs in
     */
    public static User user = null;

    //Link the window components to these objects
    @FXML
    Button loginButton;
    @FXML
    Button exitButton;
    @FXML
    TextField userIDField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label statusLabel;
    @FXML
    Label titleLable;
    @FXML
    Label userIDFieldLabel;
    @FXML
    Label passwordFieldLabel;

    public void handleLoginButton(ActionEvent event) throws IOException {

        Login login = new Login(userIDField.getText(), passwordField.getText());

        int statusCode = login.login();

        if (statusCode == 1) {

            statusLabel.setText("Error(1): Invalid login!");

        } else if (statusCode == 10) {

            loadScreen("StudentAdminScreen.fxml", "Student Admin Panel - "
                    + login.getUserID());

            user = login.getUser();

        } else if (statusCode == 20) {

            loadScreen("InstructorAdminScreen.fxml", "Instructor Admin Panel - "
                    + login.getUserID());
            user = login.getUser();

        } else if (statusCode == 30) {

            loadScreen("StaffAdminScreen.fxml", "Staff Admin Panel - "
                    + login.getUserID());
            user = login.getUser();

        } else if (statusCode == 40) {

            statusLabel.setText("Error(40): Invalid Pattern!");

        }

    }

    public void handleExitButton(ActionEvent event) {

        System.exit(0);

    }

    public void loadScreen(String fxmlFile, String title)
            throws MalformedURLException, IOException {

        String directory = System.getProperty("user.dir"); //+ "-ours-view-"
        //.replace("-", System.getProperty("file.separator"));

        Stage stage = new Stage();
        Stage current = (Stage) loginButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().
                getResource("C:\\Users\\David\\Copy ressolute@gmail.com\\spring 2014\\cpsc 2100\\OURS\\src\\ours\\view\\StudentScreen.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle(title);

        current.close();
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
