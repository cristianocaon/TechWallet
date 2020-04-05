/**
 Author: Nick Lovera
 Date: 4/3/20
 */
package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.Transaction;

import static org.junit.Assert.*;


public class ControllerWithdrawalTest {

    Transaction instance;
    ControllerWithdrawal instanceController;

    @Before
    public void setUp() {
        System.out.println("* ControllerWithdrawalTest: Before");
        instance = new Transaction();
        instanceController = new ControllerWithdrawal();
    }


    @After
    public void tearDown() {
        System.out.println("* controllerWithdrawalTest: After method tearDown()");
        instance = null;
        instanceController = null;
    }

    //Testing normal amount
    //Partition ID: W.1
    @Test
    public void testWithdrawal_normal() {
        String withdrawalAmount = "200";
        boolean expResult = true;
        boolean result = instanceController.checkAmount(withdrawalAmount);
        if (expResult != result) {
            fail("Amount was not valid");
        }
    }

    //Testing high amount
    //Partition ID: W.1
    @Test
    public void testWithdrawal_high() {
        String withdrawalAmount = "50000";
        boolean expResult = true;
        boolean result = instanceController.checkAmount(withdrawalAmount);
        if (expResult != result) {
            fail("Amount was not valid");
        }
    }

    //Testing zero amount
    //Partition ID: W.2
    @Test
    public void testWithdrawal_zero() {
        String withdrawalAmount = "0";
        boolean expResult = false;
        boolean result = instanceController.checkAmount(withdrawalAmount);
        if (expResult != result) {
            fail("Amount was not valid");
        }
    }

    //Testing lower than 0 amount
    //Partition ID: W.2
    @Test
    public void testWithdrawal_negative() {
        String withdrawalAmount = "-200";
        boolean expResult = false;
        boolean result = instanceController.checkAmount(withdrawalAmount);
        if (expResult != result) {
            fail("Amount was not valid");
        }
    }

    //Testing non numerical amount
    //Partition ID: W.3
    @Test
    public void testWithdrawal_alphanumerical() {
        String withdrawalAmount = "abc";
        boolean expResult = false;
        boolean result = instanceController.checkAmount(withdrawalAmount);
        if (expResult != result) {
            fail("Amount was not valid");
        }
    }

    //Testing normal String
    //Partition ID: W.4
    @Test
    public void testDetails_normal() {
        String withdrawalDetails = "for rent";
        boolean expResult = true;
        boolean result = instanceController.checkDetails(withdrawalDetails);
        if (expResult != result) {
            fail("Details was not valid");
        }
    }

    //Testing random String
    //Partition ID: W.4
    @Test
    public void testDetails_random() {
        String withdrawalDetails = "sk;dfbgaSugbq;ghe424";
        boolean expResult = true;
        boolean result = instanceController.checkDetails(withdrawalDetails);
        if (expResult != result) {
            fail("Details was not valid");
        }
    }

    //Testing empty String
    //Partition ID: W.5
    @Test
    public void testDetails_empty() {
        String withdrawalDetails = "";
        boolean expResult = true;
        boolean result = instanceController.checkDetails(withdrawalDetails);
        if (expResult != result) {
            fail("Details was not valid");
        }
    }

    //Testing null String
    //Partition ID: D.6
    @Test
    public void testDetails_null() {
        String withdrawalDetails = null;
        boolean expResult = false;
        boolean result = instanceController.checkDetails(withdrawalDetails);
        if (expResult != result) {
            fail("Details was not valid");
        }
    }
}