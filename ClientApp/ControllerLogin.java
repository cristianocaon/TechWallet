package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import project.User;
import sample.data.Datasource;

public class ControllerLogin {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    @FXML
    private TextField newName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField newUserName;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField passwordConfirmation;
    @FXML
    private PasswordField pin;
    @FXML
    private GridPane loginStage;
    @FXML
    private Label actionWarningLogIn;
    @FXML
    private Label actionWarningRegister;


    public void initialize() {

    }

    @FXML
    protected void handleLogin() throws Exception {
        if (Datasource.getInstance().login(userName.getText(), password.getText())) {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/Main.fxml"));
            Scene mainScene = new Scene(root);
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            mainStage.setTitle("Tech Wallet - Main");
            loginStage.getScene().getWindow().hide();
            mainStage.show();
        } else {
            actionWarningLogIn.setText("Invalid user!");
        }
    }

    @FXML
    public void handleRegistration() throws Exception {

        if (newUserName.getText().isEmpty() || newPassword.getText().isEmpty() || passwordConfirmation.getText().isEmpty() ||
                newName.getText().isEmpty() || lastName.getText().isEmpty() || pin.getText().isEmpty()) {
            actionWarningRegister.setText("All fields must be fulfilled!");
            return;
        }

        if (!(newPassword.getText().equals(passwordConfirmation.getText()))) {
            actionWarningRegister.setText("Passwords must match!");
            return;

        }

        User user = new User();
        user.setName(newName.getText());
        user.setLastName(lastName.getText());
        user.setUsername(newUserName.getText());
        user.setPassword(newPassword.getText());
        user.setPin(pin.getText());

        if (Datasource.getInstance().createUser(user)) {
            Stage mainStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/Main.fxml"));
            Scene mainScene = new Scene(root);
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            mainStage.setTitle("Tech Wallet - Main");
            loginStage.getScene().getWindow().hide();
            mainStage.show();
        }
    }

    public boolean checkNames(String name){
        if(name == null || name.isEmpty()){
            return false;
        } else {
            String[] characters = name.split("|");
            int status = 0;
            for (int i = 0; i < name.length(); i++) {
                try {
                    Integer.parseInt(characters[i]);
                    return false;
                } catch (Exception e) {
                    status ++;
                }
            }
            if(status == name.length()){
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean checkCredentials(String text){
        if(text == null || text.equals("") || text.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean checkPin(String pin){
        if(pin != null && pin.length() == 4){
            try{
                Integer.parseInt(pin);
                return true;
            } catch (Exception e){
                return false;
            }
        } else {
            return false;
        }

    }


}
