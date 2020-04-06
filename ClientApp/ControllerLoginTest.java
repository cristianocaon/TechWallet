package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.Transaction;
import sample.data.Datasource;

import static org.junit.Assert.*;

public class ControllerLoginTest {

    Transaction instance;
    ControllerLogin controllerInstance;

    @Before
    public void setUp() throws Exception {
        System.out.println("ControllerLoginTest: Before");
        instance = new Transaction();
        controllerInstance = new ControllerLogin();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("ControllerLoginTest: After method tearDown()");
        instance = null;
        controllerInstance = null;
    }

    @Test
    public void testCreateUser_validName(){
        String name = "Luke";
        boolean expResult = true;
        boolean result = controllerInstance.checkNames(name);
        if(expResult != result){
            fail("valid name test failed");
        }
    }

    @Test
    public void testCreateUser_numericalName(){
        String name = "Luke01";
        boolean expResult = false;
        boolean result = controllerInstance.checkNames(name);
        if(expResult != result){
            fail("numerical name test failed");
        }
    }

    @Test
    public void testCreateUser_emptyName(){
        String name = "";
        boolean expResult = false;
        boolean result = controllerInstance.checkNames(name);
        if(expResult != result){
            fail("Empty name test failed");
        }
    }

    @Test
    public void testCreateUser_nullName(){
        String name = null;
        boolean expResult = false;
        boolean result = controllerInstance.checkNames(name);
        if(expResult != result){
            fail("null name test failed");
        }
    }

    @Test
    public void testCreateUser_validLastName(){
        String name = "Skywalker";
        boolean expResult = true;
        boolean result = controllerInstance.checkNames(name);
        if(expResult != result){
            fail("valid last name test failed");
        }
    }

    @Test
    public void testCreateUser_numericalLastName(){
        String name = "Skywalker1";
        boolean expResult = false;
        boolean result = controllerInstance.checkNames(name);
        if(expResult != result){
            fail("numerical last name test failed");
        }
    }

    @Test
    public void testCreateUser_emptyLastName(){
        String name = "";
        boolean expResult = false;
        boolean result = controllerInstance.checkNames(name);
        if(expResult != result){
            fail("empty last name test failed");
        }
    }

    @Test
    public void testCreateUser_nullLastName(){
        String name = null;
        boolean expResult = false;
        boolean result = controllerInstance.checkNames(name);
        if(expResult != result){
            fail("null last name test failed");
        }
    }

    @Test
    public void testCreateUser_validUsername(){
        String username = "luke01";
        boolean expResult = true;
        boolean result = controllerInstance.checkCredentials(username);
        if(expResult != result){
            fail("valid username test failed ");
        }
    }

    @Test
    public void testCreateUser_emptyUsername(){
        String username = "";
        boolean expResult = false;
        boolean result = controllerInstance.checkCredentials(username);
        if(expResult != result){
            fail("empty username test failed ");
        }
    }

    @Test
    public void testCreateUser_validPassword(){
        String password = "test01";
        boolean expResult = true;
        boolean result = controllerInstance.checkCredentials(password);
        if(expResult != result){
            fail("valid password test failed");
        }
    }

    @Test
    public void testCreateUser_emptyPassword(){
        String password = "";
        boolean expResult = false;
        boolean result = controllerInstance.checkCredentials(password);
        if(expResult != result){
            fail("empty password test failed");
        }
    }

    @Test
    public void testCreateUser_nullPassword(){
        String password = null;
        boolean expResult = false;
        boolean result = controllerInstance.checkCredentials(password);
        if(expResult != result){
            fail("null password test failed");
        }
    }

    @Test
    public void testCreateUser_validPin(){
        String pin = "1234";
        boolean expResult = true;
        boolean result = controllerInstance.checkPin(pin);
        if(expResult != result){
            fail("valid pin test failed");
        }
    }

    @Test
    public void testCreateUser_alphanumericalPin(){
        String pin = "12ab";
        boolean expResult = false;
        boolean result = controllerInstance.checkPin(pin);
        if(expResult != result){
            fail("alphanumerical pin test failed");
        }
    }

    @Test
    public void testCreateUser_emptyPin(){
        String pin = "";
        boolean expResult = false;
        boolean result = controllerInstance.checkPin(pin);
        if(expResult != result){
            fail("empty pin test failed");
        }
    }

    @Test
    public void testCreateUser_nullPin(){
        String pin = null;
        boolean expResult = false;
        boolean result = controllerInstance.checkPin(pin);
        if(expResult != result){
            fail("null pin test failed");
        }
    }

    @Test
    public void testCreateUser_longPin(){
        String pin = "12345";
        boolean expResult = false;
        boolean result = controllerInstance.checkPin(pin);
        if(expResult != result){
            fail("long pin test failed");
        }
    }

    @Test
    public void testCreateUser_shortPin(){
        String pin = "123";
        boolean expResult = false;
        boolean result = controllerInstance.checkPin(pin);
        if(expResult != result){
            fail("short pin test failed");
        }
    }

//=============================================LOG IN TEST=================================================//

    @Test
    public void ControllerLogin_ExistentUser() {
        // Test ID: 1 --> Partition ID covered: 1

        System.out.println("ControllerLoginTest: Testing ControllerLogin for an existent username");

        String userInput = "user1";
        boolean expectedResult = true;
        boolean result = Datasource.getInstance().login(userInput, "12345");
        assertEquals(expectedResult, result);
    }

    @Test
    public void ControllerLogin_NonExistentUser() {
        // Test ID: 2 --> Partition ID covered: 2

        System.out.println("ControllerLoginTest: Testing ControllerLogin for a non-existent username");

        String userInput = "user99";
        boolean expectedResult = false;
        boolean result = Datasource.getInstance().login(userInput, "12345");
        assertEquals(expectedResult, result);
    }

    @Test
    public void ControllerLogin_NullUser() {
        // Test ID: 3 --> Partition ID covered: 3

        System.out.println("ControllerLoginTest: Testing ControllerLogin for a null username");

        String userInput = null;
        boolean expectedResult = false;
        boolean result = Datasource.getInstance().login(userInput, "12345");
        assertEquals(expectedResult, result);
    }

    @Test
    public void ControllerLogin_EmptyUser() {
        // Test ID: 4 --> Partition ID covered: 4

        System.out.println("ControllerLoginTest: Testing ControllerLogin for a empty username");

        String userInput = "";
        boolean expectedResult = false;
        boolean result = Datasource.getInstance().login(userInput, "12345");
        assertEquals(expectedResult, result);
    }

    @Test
    public void ControllerLogin_ValidPassword() {
        // Test ID: 5 --> Partition ID covered: 5

        System.out.println("ControllerLoginTest: Testing ControllerLogin for a valid password for a valid user.");

        String userInput = "user1";
        String password = "12345";
        boolean expectedResult = true;
        boolean result = Datasource.getInstance().login(userInput, password);
        assertEquals(expectedResult, result);
    }

    @Test
    public void ControllerLogin_InvalidPassword() {
        // Test ID: 6 --> Partition ID covered: 6

        System.out.println("ControllerLoginTest: Testing ControllerLogin for an invalid password for a valid user.");

        String userInput = "user1";
        String password = "123456";
        boolean expectedResult = false;
        boolean result = Datasource.getInstance().login(userInput, password);
        assertEquals(expectedResult, result);
    }

    @Test
    public void ControllerLogin_NullPassword() {
        // Test ID: 7 --> Partition ID covered: 7

        System.out.println("ControllerLoginTest: Testing ControllerLogin for a null password for a valid user.");

        String userInput = "user1";
        String password = null;
        boolean expectedResult = false;
        boolean result = Datasource.getInstance().login(userInput, password);
        assertEquals(expectedResult, result);
    }

    @Test
    public void ControllerLogin_EmptyPassword() {
        // Test ID: 8 --> Partition ID covered: 8

        System.out.println("ControllerLoginTest: Testing ControllerLogin for an empty password for a valid user.");

        String userInput = "user1";
        String password = "";
        boolean expectedResult = false;
        boolean result = Datasource.getInstance().login(userInput, password);
        assertEquals(expectedResult, result);
    }





}