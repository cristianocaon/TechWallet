package project;

import java.io.Serializable;

public class Details implements Serializable {

    private double income;
    private int incomeDay;
    private double savingsGoal;
    private double savingsTotal;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getIncomeDay() {
        return incomeDay;
    }

    public void setIncomeDay(int incomeDay) {
        this.incomeDay = incomeDay;
    }

    public double getSavingsGoal() {
        return savingsGoal;
    }

    public void setSavingsGoal(double savingsGoal) {
        this.savingsGoal = savingsGoal;
    }

    public double getSavingsTotal() {
        return savingsTotal;
    }

    public void setSavingsTotal(double savingsTotal) {
        this.savingsTotal = savingsTotal;
    }
}
