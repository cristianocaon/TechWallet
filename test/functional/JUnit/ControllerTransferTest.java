package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.Transaction;

import static org.junit.Assert.*;

public class ControllerTransferTest {

    Transaction instance;
    ControllerTransfer controllerInstance;

    @Before
    public void setUp() throws Exception {
        System.out.println("ControllerTransferTest: Before");
        instance = new Transaction();
        controllerInstance = new ControllerTransfer();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("ControllerTransferTest: After method tearDown()");
        instance = null;
        controllerInstance = null;
    }

    //Tests
    @Test
    public void ControllerTransfer_PositiveAmount() {
        // Test ID: 1 --> Partition ID covered: 1

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for a positive transfer");

        String userInput = "100";
        boolean expectedResult = true;
        boolean result = controllerInstance.checkAmount(userInput);
        if(expectedResult != result){
            fail("Positive amount test failed");
        }

    }

    @Test
    public void ControllerTransfer_NegativeAmount() {
        // Test ID: 2 --> Partition ID covered: 2

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for a negative transfer");

        String userInput = "-100";
        boolean expectedResult = false;
        boolean result = controllerInstance.checkAmount(userInput);
        if(expectedResult != result){
            fail("Negative amount test failed");
        }
    }

    @Test
    public void ControllerTransfer_ZeroAmount() {
        // Test ID: 3 --> Partition ID covered: 2

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for a zero transfer");

        String userInput = "0";
        boolean expectedResult = false;
        boolean result = controllerInstance.checkAmount(userInput);
        if(expectedResult != result){
            fail("Zero amount test failed");
        }
    }

    @Test
    public void ControllerTransfer_NonNumericAmount() {
        // Test ID: 4 --> Partition ID covered: 3

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for a non-numeric transfer");

        String userInput = "str";
        boolean expectedResult = false;
        boolean result = controllerInstance.checkAmount(userInput);
        if(expectedResult != result){
            fail("Non numerical amount test failed");
        }

    }

    @Test
    public void ControllerTransfer_EmptyAmount() {
        // Test ID: 5 --> Partition ID covered: 4

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for an empty transfer");

        String userInput = "";
        boolean expectedResult = false;
        boolean result = controllerInstance.checkAmount(userInput);
        if(expectedResult != result){
            fail("empty amount test failed");
        }
    }

    @Test
    public void ControllerTransfer_NullAmount() {
        // Test ID: 6 --> Partition ID covered: 5

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for a null transfer");

        String userInput = null;
        boolean expectedResult = false;
        boolean result = controllerInstance.checkAmount(userInput);
        if(expectedResult != result){
            fail("null amount test failed");
        }
    }

    @Test
    public void ControllerTransfer_ValidDetails() {
        // Test ID: 7 --> Partition ID covered: 6

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for a valid detail");

        String userInput = "Simple transfer";
        boolean expectedResult = true;
        boolean result = controllerInstance.checkDetails(userInput);
        if(result != expectedResult){
            fail("valid details test failed");
        }
    }

    @Test
    public void ControllerTransfer_EmptyDetails() {
        // Test ID: 8 --> Partition ID covered: 7

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for an empty detail");

        String userInput = "";
        boolean expectedResult = true;
        boolean result = controllerInstance.checkDetails(userInput);
        if(expectedResult != result){
            fail("Empty details test failed");
        }
    }

    @Test
    public void ControllerTransfer_NullDetails() {
        // Test ID: 9 --> Partition ID covered: 8

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for a null detail");

        String userInput = null;
        boolean expectedResult = false;
        boolean result = controllerInstance.checkDetails(userInput);
        if(result != expectedResult){
            fail("null details test failed");
        }

    }

    @Test
    public void ControllerTransfer_ExistentReceivingAccount() {
        // Test ID: 10 --> Partition ID covered: 9

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for an existent receiving account");

        String userInput = "00001";
        boolean expectedResult = true;
        boolean result = controllerInstance.checkAccount(userInput);
        if(expectedResult != result) {
            fail("Existing account test failed");
        }
    }


    @Test
    public void ControllerTransfer_NonExistentReceivingAccount() {
        // Test ID: 11 --> Partition ID covered: 10

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for a non-existent receiving account");

        String userInput = "00234";
        boolean expectedResult = false;
        boolean result = controllerInstance.checkAccount(userInput);
        if(expectedResult != result){
            fail("Non-existent account test failed");
        }
    }

    @Test
    public void ControllerTransfer_EmptyReceivingAccount() {
        // Test ID: 12 --> Partition ID covered: 11

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for an empty receiving account");

        String userInput = "";
        boolean expectedResult = false;
        boolean result = controllerInstance.checkAccount(userInput);
        if(expectedResult != result){
            fail("empty account test failed");
        }
    }

    @Test
    public void ControllerTransfer_NullReceivingAccount() {
        // Test ID: 13 --> Partition ID covered: 12

        System.out.println("ControllerTransferTest: Testing ControllerTransfer for a null receiving account");

        String userInput = null;
        boolean expectedResult = false;
        boolean result = controllerInstance.checkAccount(userInput);
        if(expectedResult != result){
            fail("null account test failed");
        }

    }
}