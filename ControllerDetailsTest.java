package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.Transaction;

import static org.junit.Assert.*;

public class ControllerDetailsTest {

    Transaction instance;
    ControllerDetails controllerInstance;

    @Before
    public void setUp() throws Exception {
        System.out.println("ControllerDetailsTest: Before");
        instance = new Transaction();
        controllerInstance = new ControllerDetails();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("ControllerDetailsTest: After method tearDown()");
        instance = null;
        controllerInstance = null;
    }

    //Testing Goals
    @Test
    public void testDetails_validGoal(){
        String goal = "200";
        boolean expResult = true;
        boolean result = controllerInstance.checkAmounts(goal);
        if(expResult != result){
            fail("valid amount test failed");
        }
    }

    @Test
    public void testDetails_alphanumericalGoal(){
        String goal = "test01";
        boolean expResult = false;
        boolean result = controllerInstance.checkAmounts(goal);
        if(expResult != result){
            fail("alphanumerical amount test failed");
        }
    }

    @Test
    public void testDetails_negativeGoal(){
        String goal = "-10";
        boolean expResult = false;
        boolean result = controllerInstance.checkAmounts(goal);
        if(expResult != result){
            fail("valid amount test failed");
        }
    }

    @Test
    public void testDetails_validIncome(){
        String income = "200";
        boolean expResult = true;
        boolean result = controllerInstance.checkAmounts(income);
        if(expResult != result){
            fail("valid amount test failed");
        }
    }

    @Test
    public void testDetails_alphanumericalIncome(){
        String income = "test02";
        boolean expResult = false;
        boolean result = controllerInstance.checkAmounts(income);
        if(expResult != result){
            fail("alphanumerical amount test failed");
        }
    }

    @Test
    public void testDetails_negativeIncome(){
        String income = "-100";
        boolean expResult = false;
        boolean result = controllerInstance.checkAmounts(income);
        if(expResult != result){
            fail("negative amount test failed");
        }
    }

    @Test
    public void testDetails_validDay(){
        int day = 16;
        boolean expResult = true;
        boolean result = controllerInstance.checkDay(day);
        if(expResult != result){
            fail("valid day test failed");
        }
    }

    @Test
    public void testDetails_invalidDay(){
        int day = 0;
        boolean expResult = false;
        boolean result = controllerInstance.checkDay(day);
        if(expResult != result){
            fail("valid day test failed");
        }
    }









}