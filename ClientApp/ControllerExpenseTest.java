package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.Transaction;

import static org.junit.Assert.*;

public class ControllerExpenseTest {

    Transaction instance;
    ControllerExpense controllerInstance;

    @Before
    public void setUp() throws Exception {
        System.out.println("ControllerExpenseTest: Before");
        instance = new Transaction();
        controllerInstance = new ControllerExpense();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("ControllerExpenseTest: After method tearDown()");
        instance = null;
        controllerInstance = null;
    }

    //Testing amount
    //Partition ID: E.1
    @Test
    public void testExpense_validAmount(){
        String expenseAmount = "300";
        boolean expResult = true;
        boolean result = controllerInstance.checkAmount(expenseAmount);
        if(expResult != result){
            fail("Amount was not valid");
        }
    }

    //Testing zero as amount
    //Partition ID: E.3
    @Test
    public void testExpense_zeroAmount(){
        String expenseAmount = "0";
        boolean expResult = false;
        boolean result = controllerInstance.checkAmount(expenseAmount);
        if(expResult != result){
            fail("Amount was not valid");
        }
    }

    //Testing negative as amount
    //Partition ID: E.4
    @Test
    public void testExpense_negativeAmount(){
        String expenseAmount = "-76";
        boolean expResult = false;
        boolean result = controllerInstance.checkAmount(expenseAmount);
        if(expResult != result){
            fail("Amount was not valid");
        }
    }

    //Testing alphanumerical amount
    //Partition ID: E.2 & E.5
    @Test
    public void testExpense_alphanumericalAmount(){
        String expenseAmount = "test09";
        boolean expResult = false;
        boolean result = controllerInstance.checkAmount(expenseAmount);
        if(expResult != result){
            fail("Amount was not valid");
        }
    }

    //Testing alphanumerical details
    //Partition ID: E.6
    @Test
    public void testExpense_alphanumericalDetails(){
        String details = "Test 01";
        boolean expResult = true;
        boolean result = controllerInstance.checkDetails(details);
        if(expResult != result){
            fail("Details are invalid");
        }
    }

    //Testing null details
    //Partition ID: E.7
    @Test
    public void testExpense_nullDetails(){
        String details = null;
        boolean expResult = false;
        boolean result = controllerInstance.checkDetails(details);
        if(expResult != result){
            fail("Details null test failed");
        }
    }

    //Testing empty string
    //Partition ID: E.8
    @Test
    public void testExpense_emptyDetails(){
        String details = "";
        boolean expResult = true;
        boolean result = controllerInstance.checkDetails(details);
        if(expResult != result){
            fail("Details empty test failed");
        }
    }





}