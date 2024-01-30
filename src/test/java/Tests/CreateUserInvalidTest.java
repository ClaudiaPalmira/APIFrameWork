package Tests;

import RequestObject.RequestAccount;
import RequestObject.RequestAccountToken;
import ResponseObject.ResponseAccountAuthSuccess;
import ResponseObject.ResponseAccountFailed;
import ResponseObject.ResponseTokenSuccess;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserInvalidTest {

    public String userID;
    public String username;
    public String password;
    public String token;
    public Integer code;
    public String message;


    @Test
    public void testMethod(){

        System.out.println("Step 1 - create user");
        createUser();

    }

    public void createUser(){  //PASUL 1 CREEAM USERUL CARE SA NE OFERE ID-UL

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com");
        requestSpecification.contentType("application/json");

        //Configuram request-ul

        username = "Claudia"; //+ System.currentTimeMillis()- scoatem partea aceasta pentru a determina daca ne da eroare ca rulam testul de 2 ori cu acelasi date
        password = "Password!??@#1234";

        RequestAccount requestAccount = new RequestAccount(username,password );
        requestSpecification.body(requestAccount);


        //Accesam response-ul

        Response response = requestSpecification.post("/Account/v1/User");
        ResponseBody body = response.getBody();
        body.prettyPrint();

        //Validam statusul requestului

        Assert.assertEquals(response.getStatusCode(), 406); //principala validare

        //Validam response body-ul requestului

        ResponseAccountFailed responseAccountInvalid = response.body().as(ResponseAccountFailed.class);

        Assert.assertNotNull(responseAccountInvalid.getCode());
        Assert.assertEquals(responseAccountInvalid.getMessage(), "User exists!");

   }

}