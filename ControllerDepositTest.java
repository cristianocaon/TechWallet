/*
Author: Nick Lovera
Date: 4/3/2020
*/
package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.Transaction;

import static org.junit.Assert.*;

public class ControllerDepositTest {


    Transaction instance;
    ControllerDeposit instanceController;

    @Before
    public void setUp(){
        System.out.println("* ControllerDepositTest: Before");
        instance = new Transaction();
        instanceController = new ControllerDeposit();
    }


    @After
    public void tearDown() {
        System.out.println("* controllerDepositTest: After method tearDown()");
        instance = null;
        instanceController = null;
    }

    //Testing normal amount
    //Partition ID: D.1
    @Test
    public void testDeposit_normal(){
        String depositAmount = "200";
        boolean expResult = true;
        boolean result = instanceController.checkAmount(depositAmount);
        if(expResult != result){
            fail("Amount was not valid");
        }
    }

    //Testing high amount
    //Partition ID: D.1
    @Test
    public void testDeposit_high(){
        String depositAmount = "50000";
        boolean expResult = true;
        boolean result = instanceController.checkAmount(depositAmount);
        if(expResult != result){
            fail("Amount was not valid");
        }
    }

    //Testing zero amount
    //Partition ID: D.2
    @Test
    public void testDeposit_zero(){
        String depositAmount = "0";
        boolean expResult = false;
        boolean result = instanceController.checkAmount(depositAmount);
        if(expResult != result){
            fail("Amount was not valid");
        }
    }

    //Testing lower than 0 amount
    //Partition ID: D.2
    @Test
    public void testDeposit_low(){
        String depositAmount = "-200";
        boolean expResult = false;
        boolean result = instanceController.checkAmount(depositAmount);
        if(expResult != result){
            fail("Amount was not valid");
        }
    }

    //Testing non numerical amount
    //Partition ID: D.3
    @Test
    public void testDeposit_alphanumerical(){
        String depositAmount = "abc";
        boolean expResult = false;
        boolean result = instanceController.checkAmount(depositAmount);
        if(expResult != result){
            fail("Amount was not valid");
        }
    }

    //Testing normal String
    //Partition ID: D.4
    @Test
    public void testDetails_normal(){
        String depositDetails = "for rent";
        boolean expResult = true;
        boolean result = instanceController.checkDetails(depositDetails);
        if(expResult != result){
            fail("Details was not valid");
        }
    }

    //Testing random String
    //Partition ID: D.4
    @Test
    public void testDetails_random(){
        String depositDetails = "sk;dfbgaSugbq;ghe424";
        boolean expResult = true;
        boolean result = instanceController.checkDetails(depositDetails);
        if(expResult != result){
            fail("Details was not valid");
        }
    }

    //Testing empty String
    //Partition ID: D.5
    @Test
    public void testDetails_empty(){
        String depositDetails = "";
        boolean expResult = true;
        boolean result = instanceController.checkDetails(depositDetails);
        if(expResult != result){
            fail("Details was not valid");
        }
    }

    //Testing null String
    //Partition ID: D.6
    @Test
    public void testDetails_null(){
        String depositDetails = null;
        boolean expResult = false;
        boolean result = instanceController.checkDetails(depositDetails);
        if(expResult != result){
            fail("Details was not valid");
        }
    }

}