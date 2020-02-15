package sample.data;

import project.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Datasource {

    //Datasource instance creation
    private static Datasource instance = new Datasource();
    private Datasource(){}
    public static Datasource getInstance(){
        return instance;
    }

    //Global Variables
    public Account account;
    public User user;
    public Transaction transaction;
    public Details details;
    public double savings;

    //Socket creation
    public Socket socket;

    private boolean connectToServer(){
        try{
            socket = new Socket("localhost", 6000);
            socket.setSoTimeout(5000);
            return true;
        } catch (IOException e){
            return false;
        }
    }

    private void closeConnection(){
        try{
            socket.close();
        } catch (IOException e){
            System.out.println("Connection was not properly closed");
        }
    }

    //======================METHODS=====================//


    public boolean login(String username, String password){
        try{
            if(connectToServer()){
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                //send request//
                Object[] request = {"login", username, password};
                output.writeObject(request);

                //receive user//
                user = (User) input.readObject();

                if(user != null){
                    closeConnection();
                    return true;
                } else {
                    closeConnection();
                    return false;
                }
            }
            return false;
        } catch (Exception e){
            closeConnection();
            return false;
        }
    }

    public boolean createUser(User user){
        try{
            if(connectToServer()){
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                System.out.println("This part was reached");

                //send request//
                Object[] request = {"createUser", user};
                output.writeObject(request);

                //receive confirmation//
                String confirmation = (String) input.readObject();
                if(confirmation.equals("success")){
                    closeConnection();
                    login(user.getUsername(), user.getPassword());
                    return true;
                } else {
                    closeConnection();
                    return false;
                }

            }
            return false;
        } catch (Exception e){
            closeConnection();
            return false;
        }
    }

    public boolean queryAccount(){
        try{
            if(connectToServer()) {
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                //send request//
                Object[] request = {"queryAccount", user.getId()};
                output.writeObject(request);

                //receive Account//
                account = (Account) input.readObject();

                if (account != null) {
                    closeConnection();
                    return true;
                } else {
                    closeConnection();
                    return false;
                }
            }
            return false;
        } catch (Exception e){
            closeConnection();
            return false;
        }
    }

    public List<String> queryAllAccounts(){
        try{
            if(connectToServer()){
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                //send request//
                Object[] request = {"queryAllAccounts"};
                output.writeObject(request);

                //receive account numbers//
                List<String> accounts = (List<String>) input.readObject();
                closeConnection();
                return accounts;
            }
            return null;
        } catch (Exception e){
            return null;
        }
    }

    public List<MainTable> queryTransactionsForTable(){
        DecimalFormat formatter= new DecimalFormat("#,##0.00");
        try{
            if(connectToServer()){
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                //send request//
                Object[] request = {"queryTransactionsForTable", account.getAccountNumber()};
                output.writeObject(request);

                //receive transactions//
                List<Transaction> serverTransactions = (List<Transaction>) input.readObject();
                List<MainTable> transactions = new ArrayList<>();
                for(int i = 0; i < serverTransactions.size(); i++){
                    Transaction serverTransaction = serverTransactions.get(i);
                    MainTable transaction = new MainTable();
                    transaction.setId(serverTransaction.getId());
                    transaction.setOrigin(serverTransaction.getAccount());
                    transaction.setType(serverTransaction.getType());
                    transaction.setAmount("$ " + formatter.format(serverTransaction.getAmount()));
                    transaction.setDetails(serverTransaction.getDetails());
                    transaction.setDate(serverTransaction.getDate());
                    transactions.add(transaction);
                }

                closeConnection();
                return transactions;
            }
            return null;
        } catch (Exception e){
            closeConnection();
            return null;
        }
    }

    public boolean queryTransaction(int id){
        try{
            if(connectToServer()){
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                //send request//
                Object[] request = {"queryTransaction", id};
                output.writeObject(request);

                //receive transaction//
                transaction = (Transaction) input.readObject();
                if(transaction != null){
                    closeConnection();
                    return true;
                } else {
                    closeConnection();
                    return false;
                }
            }
            return false;
        } catch (Exception e){
            closeConnection();
            return false;
        }
    }

    public boolean saveTransaction(Transaction transaction){
        try{
            if(connectToServer()){
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                //send request//
                Object[] request = {"saveTransaction", transaction};
                output.writeObject(request);

                //receive confirmation//
                String confirmation = (String) input.readObject();
                if(confirmation.equals("success")){
                    closeConnection();
                    return true;
                } else {
                    closeConnection();
                    return false;
                }
            }
            return false;
        } catch (Exception e){
            closeConnection();
            return false;
        }
    }

    public List<Transaction> queryStatement(String month, String year, String account){
        try {
            if (connectToServer()) {
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                //send request//
                Object[] request = {"queryStatement", month, year, account};
                output.writeObject(request);

                //receive transactions//
                return (List<Transaction>) input.readObject();
            }
            return null;
        }catch (Exception e){
            closeConnection();
            return null;
        } finally {
            closeConnection();
        }
    }

    public boolean saveDetails(Details details){
        try{
            if(connectToServer()){
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                //send request//
                Object[] request = {"saveDetails", details, user.getId()};
                output.writeObject(request);

                //receive confirmation//
                String confirmation = (String) input.readObject();
                if(confirmation.equals("success")){
                    closeConnection();
                    return true;
                } else {
                    closeConnection();
                    return false;
                }
            }
            return false;
        } catch (Exception e){
            closeConnection();
            return false;
        }
    }

    public boolean queryDetails(int id){
        try{
            if(connectToServer()){
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                //send request//
                Object[] request = {"queryDetails", id};
                output.writeObject(request);

                //receive Details//
                details = (Details) input.readObject();

                if(details != null){
                    closeConnection();
                    return true;
                } else {
                    closeConnection();
                    return false;
                }
            }
            return false;
        } catch (Exception e){
            closeConnection();
            return false;
        }
    }






}
