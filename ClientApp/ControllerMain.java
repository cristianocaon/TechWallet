package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import project.Account;
import project.Details;
import project.Transaction;
import project.User;
import sample.data.Datasource;
import sample.data.MainTable;

import javax.swing.text.html.Option;
import java.text.DecimalFormat;
import java.util.Optional;

public class ControllerMain {

    @FXML
    private Label balance;
    @FXML
    private Label savings;
    @FXML
    private BorderPane mainStage;
    @FXML
    private TableView<MainTable> table;

    private DecimalFormat formatter = new DecimalFormat("#,##0.00");

    private ButtonType save = new ButtonType("Save");
    private ButtonType cancel = new ButtonType("Cancel");
    private ButtonType export = new ButtonType("Export");
    private ButtonType accept = new ButtonType("Accept");

    private User user(){
        return Datasource.getInstance().user;
    }

    private Account account(){
        Datasource.getInstance().queryAccount();
        return Datasource.getInstance().account;
    }

    private Transaction transaction(){
        return Datasource.getInstance().transaction;
    }

    private Details details(){
        return Datasource.getInstance().details;
    }

    private void setBalance(){
        balance.setText("$ " + formatter.format(account().getBalance()));
    }

    private void confirmationAlert(String text, String title, String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(header);

        alert.showAndWait();
    }

    private void errorAlert(String text, String title, String header){
        Alert alert = new Alert(Alert.AlertType.ERROR, text, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(header);

        alert.showAndWait();
    }

    private void listTransactions(){
        Task<ObservableList<MainTable>> task = new GetAllTransactions();
        table.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    public void initialize() {
        Datasource.getInstance().queryAccount();
        balance.setText("$ " + formatter.format(account().getBalance()));
        Datasource.getInstance().queryDetails(user().getId());
        savings.setText("$ " + formatter.format(details().getSavingsTotal()));

        listTransactions();
    }

    @FXML
    public void handleStatement() throws Exception{

        Dialog<ButtonType> dialogStatement = new Dialog<>();
        dialogStatement.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Statement.fxml"));
        dialogStatement.setTitle("Tech Wallet - Statement");
        try{
            dialogStatement.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();
            return;
        }
        dialogStatement.getDialogPane().getButtonTypes().addAll(export, cancel);

        Optional<ButtonType> result = dialogStatement.showAndWait();
        if(result.isPresent() && result.get() == export){
            ControllerStatement controller = fxmlLoader.getController();
            controller.exportStatement();
        }
    }

    @FXML
    public void handleWithdraw() throws Exception {

        Dialog<ButtonType> dialogWithdraw = new Dialog<>();
        dialogWithdraw.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("withdrawal.fxml"));
        dialogWithdraw.setTitle("Tech Wallet - Withdraw");
        try{
            dialogWithdraw.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        dialogWithdraw.getDialogPane().getButtonTypes().addAll(save, cancel);

        Optional<ButtonType> result = dialogWithdraw.showAndWait();
        int status = 0;
        while(status == 0){
            if(result.isPresent() && result.get() == save){
                ControllerWithdrawal controller = fxmlLoader.getController();
                if(controller.checkErrors()){
                    if(controller.withdrawal()){
                        confirmationAlert("withdrawal recorded", "Withdrawal", "Saved");
                        status ++;
                        setBalance();
                    } else {
                        errorAlert("Error ocurred saving transaction\n\nTry again", "Withdrawal", "Error");
                    }
                }
            } else {
                status ++;
            }
        }
        listTransactions();
    }

    @FXML
    public void handleTransfer() throws Exception{

        Dialog<ButtonType> dialogTransfer = new Dialog<>();
        dialogTransfer.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Transfer.fxml"));
        dialogTransfer.setTitle("Tech Wallet - Transfer");
        try{
            dialogTransfer.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            return;
        }
        dialogTransfer.getDialogPane().getButtonTypes().addAll(save, cancel);

        int status = 0;
        while(status == 0){
            Optional<ButtonType> result = dialogTransfer.showAndWait();
            if(result.isPresent() && result.get() == save){
                ControllerTransfer controller = fxmlLoader.getController();
                if(controller.checkForErrors()){
                    if(controller.transfer()){
                        confirmationAlert("The transfer was recorder successfully", "Transfer", "Success");
                        status ++;
                        setBalance();
                    } else {
                        errorAlert("There was an error completing the transaction\n\nPlease try again", "Transfer", "Error");
                    }
                }
            } else {
                status ++;
            }
        }
        listTransactions();
    }

    @FXML
    public void handleDeposit() throws Exception{

        Dialog<ButtonType> dialogDeposit = new Dialog<>();
        dialogDeposit.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Deposit.fxml"));
        dialogDeposit.setTitle("Tech Wallet - Deposit");
        try{
            dialogDeposit.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            return;
        }

        dialogDeposit.getDialogPane().getButtonTypes().addAll(save, cancel);
        int status = 0;
        while(status == 0){
            Optional<ButtonType> result = dialogDeposit.showAndWait();
            if(result.isPresent() && result.get() == save){
                ControllerDeposit controller = fxmlLoader.getController();
                if(controller.checkErrors()){
                    if(controller.deposit()){
                        confirmationAlert("Deposit was recorded successfully", "Deposit", "Success");
                        status++;
                        setBalance();
                    } else {
                        errorAlert("Something went wrong recording the deposit\n\nPlease try again later", "Deposit", "Error");
                        status++;
                    }
                }
            } else {
                status++;
            }
        }
        listTransactions();
    }

    @FXML
    public void handleAddExpense() throws Exception{

        Dialog<ButtonType> dialogAddExpense = new Dialog<>();
        dialogAddExpense.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Expense.fxml"));
        dialogAddExpense.setTitle("Tech Wallet - Add Expense");
        try{
            dialogAddExpense.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            return;
        }
        dialogAddExpense.getDialogPane().getButtonTypes().addAll(save, cancel);
        int status = 0;
        while(status == 0){
            Optional<ButtonType> result = dialogAddExpense.showAndWait();
            if(result.isPresent() && result.get() == save){
                ControllerExpense controller = fxmlLoader.getController();
                if(controller.checkErrors()){
                    if(controller.expense()){
                        confirmationAlert("The expense was recorded successfully", "Expense", "Success");
                        status ++;
                        setBalance();
                    } else {
                        errorAlert("Something went wrong recording the expense", "Expense", "Error");
                        status++;
                    }
                }
            } else {
                status++;
            }
        }
        listTransactions();
    }

//    @FXML
//    public void handleSettings() throws Exception{
//
//        Dialog<ButtonType> dialogSettings = new Dialog<>();
//        dialogSettings.initOwner(mainStage.getScene().getWindow());
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("Settings.fxml"));
//        dialogSettings.setTitle("Tech Wallet - Settings");
//        try{
//            dialogSettings.getDialogPane().setContent(fxmlLoader.load());
//        } catch (Exception e){
//            return;
//        }
//        ButtonType buttonConfirm = new ButtonType("Confirm");
//        ButtonType buttonCancel = new ButtonType("Cancel");
//        dialogSettings.getDialogPane().getButtonTypes().addAll(buttonConfirm, buttonCancel);
//        dialogSettings.showAndWait();
//    }

    @FXML
    public void handleDetails() throws Exception{
        System.out.println("savings entered");
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("details.fxml"));
        dialog.setTitle("Tech Wallet - Savings details");
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().addAll(save, cancel);

        int status = 0;
        while(status == 0){
            Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && result.get() == save){
                ControllerDetails controller = fxmlLoader.getController();
                if(controller.checkErrors()){
                    if(controller.saveDetails()){
                        confirmationAlert("Savings details were recorder successfully", "Savings", "Success");
                        status ++;
                    } else {
                        errorAlert("Something went wrong saving the details", "Savings", "Error");
                        status ++;
                    }
                }
            } else {
                status ++;
            }
        }

    }

}

class GetAllTransactions extends Task {
    @Override
    public ObservableList<MainTable> call(){
        return FXCollections.observableArrayList(Datasource.getInstance().queryTransactionsForTable());
    }
}