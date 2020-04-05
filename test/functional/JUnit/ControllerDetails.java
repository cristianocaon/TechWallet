package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import project.Details;
import sample.data.Datasource;



public class ControllerDetails {


    @FXML
    private TextField newGoal;
    @FXML
    private TextField income;
    @FXML
    private Spinner<Integer> incomeDay;


    public void initialize(){

    }

    public boolean checkErrors(){
        return (checkAmounts(newGoal.getText()) && checkAmounts(income.getText()) &&
                checkDay(incomeDay.getValue()));
    }

    public boolean checkAmounts(String goal){
        try{
            double number = Double.parseDouble(goal);
            if(number <= 0){
                return false;
            }
        } catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean checkDay(int day){
        return !(day == 0);
    }

    public boolean saveDetails(){
        Details details = new Details();

        try{
            details.setSavingsGoal(Double.parseDouble(newGoal.getText()));
            details.setIncome(Double.parseDouble(income.getText()));
            details.setIncomeDay(incomeDay.getValue());
        } catch (Exception e){
            return false;
        }

        return Datasource.getInstance().saveDetails(details);
    }



}
