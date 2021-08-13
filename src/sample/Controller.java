package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginErrorLabel;
    @FXML
    private TextField userIDTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent e) {


        if (userIDTextField.getText().isBlank() == true && passwordPasswordField.getText().isBlank() == true){
            loginErrorLabel.setText("User ID and Password are empty.");
        } else {
            validateLogin();
        }
    }

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM USERS WHERE User_ID = '" + userIDTextField.getText().isBlank() + "' AND Password = '" + passwordPasswordField.getText() + "'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
// there should only be 1 unique user_id and password match
            while(queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginErrorLabel.setText("Welcome");
                } else {
                    loginErrorLabel.setText("Invalid Login. Please enter a valid user id and password");
                }
            }

        } catch (Exception e){

        }


    }

}
