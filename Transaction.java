package project;

import java.io.Serializable;

public class Transaction implements Serializable {

    private String type;
    private String account;
    private String receivingAccount;
    private int id;
    private double amount;
    private String date;
    private String details;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(String receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
