package wallet;

import wallet.data.Datasource;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {

        //===connect with database===//
        if(Datasource.getInstance().open()){
            System.out.println("Server is up and running!");
        }

        try(ServerSocket serverSocket = new ServerSocket(6000)){
            while(true){
                new Messenger(serverSocket.accept()).start();
                System.out.println("Client connected");
            }
        } catch (IOException e){
            System.out.println("Unable to establish connection with client: " + e.getMessage());
        }
    }
}
