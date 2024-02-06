package Actions;

import Objects.RequestObject.RequestAccount;
import Objects.RequestObject.RequestAccountToken;
import Objects.ResponseObject.ResponseAccountSuccess;
import Objects.ResponseObject.ResponseTokenSuccess;
import Rest.RestRequestStatus;
import Service.ServiceImplementation.AccountServiceImplementation;
import io.restassured.response.Response;
import org.testng.Assert;

public class AccountActions {

    public AccountServiceImplementation accountService;

    public ResponseAccountSuccess createNewAccount(RequestAccount requestAccount){
        accountService = new AccountServiceImplementation();  //facem instantierea obiectului nostru
        Response response = accountService.createAccount(requestAccount); //facem un request unde conectam serviciul cu layerul de Rest

        // URMATOAREA ETAPA - VALIDARILE
        Assert.assertEquals((int) RestRequestStatus.SC_CREATED, response.getStatusCode());  //am specificat primitiva "int" si facem assert-ul

        ResponseAccountSuccess responseAccountSuccess = response.body().as(ResponseAccountSuccess.class); //face deserializarea
        Assert.assertNotNull(responseAccountSuccess.getUserID()); //facem asserturi restul 3 coloanelor in jos
        Assert.assertEquals(responseAccountSuccess.getUsername(), requestAccount.getUserName());
        Assert.assertNotNull(responseAccountSuccess.getBooks());

        return responseAccountSuccess;
    }

    public ResponseTokenSuccess generateToken(RequestAccountToken requestAccountToken){
        accountService = new AccountServiceImplementation();
        Response response = accountService.generateToken(requestAccountToken);

        // URMATOAREA ETAPA - VALIDARILE
        Assert.assertEquals(RestRequestStatus.SC_OK,response.getStatusCode());

        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        Assert.assertNotNull(responseTokenSuccess.getToken());
        Assert.assertNotNull(responseTokenSuccess.getExpires());
        Assert.assertEquals(responseTokenSuccess.getStatus(),"Success");
        Assert.assertEquals(responseTokenSuccess.getResult(), "User authorized successfully.");

         return responseTokenSuccess;

    }

}
