package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import project.Account;
import project.Transaction;
import sample.data.Datasource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ControllerDeposit {

    @FXML
    private TextField amountDeposit;
    @FXML
    private TextArea detailsDeposit;

    private Account account(){
        Datasource.getInstance().queryAccount();
        return Datasource.getInstance().account;
    }

    public void initialize(){

    }

    public boolean checkErrors(){
        return checkAmount(amountDeposit.getText()) && checkDetails(detailsDeposit.getText());
    }

    public boolean checkAmount(String amount){
        try{
            double number = Double.parseDouble(amount);
            if(number <= 0){
                return false;
            }
        } catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean checkDetails(String text){
        return !(text == null);
    }


    public boolean deposit(){
        Transaction deposit = new Transaction();
        //Setting transaction details
        deposit.setType("Deposit");
        deposit.setAccount(account().getAccountNumber());
        deposit.setAmount(Double.parseDouble(amountDeposit.getText()));
        deposit.setDetails(detailsDeposit.getText());
        //Getting Date
        Date localDate = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
        deposit.setDate(date);
        //Saving deposit transaction
        return Datasource.getInstance().saveTransaction(deposit);

    }









}
