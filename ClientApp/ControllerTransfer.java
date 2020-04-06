package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import project.Account;
import project.Transaction;
import sample.data.Datasource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ControllerTransfer {

    @FXML
    private TextField amountTransfer;
    @FXML
    private TextArea detailsTransfer;
    @FXML
    private TextField receivingAccount;
    @FXML
    private Label errorMessage;


    public void initialize(){

    }

    private Account account() {
        return Datasource.getInstance().account;
    }

    public boolean checkForErrors(){
        return (checkAccount(receivingAccount.getText()) && checkAmount(amountTransfer.getText()) && checkDetails(detailsTransfer.getText()));
    }

    public boolean checkAccount(String account){
        List<String> accounts = Datasource.getInstance().queryAllAccounts();
        if(account != null){
            System.out.println("Reached this");
            if(account.length() == 5){
                try{
                    Integer.parseInt(account);
                    int status = 0;
                    for(int i = 0; i < accounts.size(); i++){
                        if(accounts.get(i).equals(account)){
                            System.out.println(account + " vs " + accounts.get(i));
                            status++;
                            break;
                        }
                    }
                    if(status == 0){
                        errorMessage.setText("Receiving account does not exist, please double check");
                        return false;
                    }
                    return true;
                } catch (Exception e){
                    return false;
                }
            }
        }

        return false;
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

    //Transferring
    public boolean transfer() {

        Transaction transfer = new Transaction();

        transfer.setType("Transfer");
        transfer.setAccount(account().getAccountNumber());
        transfer.setReceivingAccount(receivingAccount.getText());
        transfer.setAmount(Double.parseDouble(amountTransfer.getText()));
        transfer.setDetails(detailsTransfer.getText());
        //Getting Date
        Date localDate = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
        transfer.setDate(date);


        return Datasource.getInstance().saveTransaction(transfer);
    }




}
