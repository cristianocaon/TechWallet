package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import project.Account;
import project.Details;
import project.Transaction;
import project.User;
import sample.data.Datasource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ControllerStatement {

    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private ComboBox<String> yearComboBox;

    @FXML
    private Label username;
    @FXML
    private Label accountNumber;
    @FXML
    private Label totalExpenses;
    @FXML
    private Label totalMoneySaved;
    @FXML
    private Label finalBalance;

    private DecimalFormat formatter = new DecimalFormat("#,##0.00");


    private Account account(){
        return Datasource.getInstance().account;
    }

    private User user(){
        return Datasource.getInstance().user;
    }

    private Details details(){
        return Datasource.getInstance().details;
    }

    public void initialize(){

        username.setText(user().getName() + " " + user().getLastName());
        accountNumber.setText(account().getAccountNumber());
        totalMoneySaved.setText("$ " + formatter.format(details().getSavingsTotal()));
        finalBalance.setText("$ " + formatter.format(account().getBalance()));

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String[] today = dateFormat.format(date).split("-");

        monthComboBox.setValue(today[1]);
        yearComboBox.setValue(today[2]);
    }

    public void exportStatement(){
        System.out.println("File exported");

        String accountNum = account().getAccountNumber();
        String pullMonth = monthComboBox.getValue();
        String pullYear = yearComboBox.getValue();

        String userHome = System.getProperty("user.home"); //Pulls the users home directory dynamically for file storage
        String outputFolder = userHome + File.separator + "statements"; //Creates a folder named "statements" in the users home directory
        File folder = new File(outputFolder);
        if (!folder.exists()) { //Checks if the folder (statements) already exists, and if it doesn't it creates one
            folder.mkdir();
        }

        try{
            FileWriter csvfile = new FileWriter(outputFolder+File.separator+""+pullYear+""+pullMonth+"_MonthlyStatement.csv");
            List<Transaction> transactions = Datasource.getInstance().queryStatement(pullMonth, pullYear, accountNum);
            String holder;

            //Loops through the list of transactions and pulls amount, type, details, and date and appends it to the csv file, separated by a comma
            for(int i=0;i<transactions.size();i++)
            {
                holder = transactions.get(i).getType();
                csvfile.append(holder);
                csvfile.append(",");
                holder = Double.toString(transactions.get(i).getAmount());
                csvfile.append(holder);
                csvfile.append(",");
                holder = transactions.get(i).getDetails();
                csvfile.append(holder);
                csvfile.append(",");
                holder = transactions.get(i).getDate();
                csvfile.append(holder);
                csvfile.append("\n");
            }
            csvfile.flush();
            csvfile.close();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not save file", ButtonType.OK);
            alert.setTitle("Exporting error");
            alert.setHeaderText("Error");
            alert.showAndWait();
        }

    }

}
