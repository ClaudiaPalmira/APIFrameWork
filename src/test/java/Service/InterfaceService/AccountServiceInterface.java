package Service.InterfaceService;

import Objects.RequestObject.RequestAccount;
import Objects.RequestObject.RequestAccountToken;
import io.restassured.response.Response;

public interface AccountServiceInterface {

    Response createAccount(RequestAccount requestAccount);  //primul post pe care serviciul acesta il poate face
    Response generateToken(RequestAccountToken requestAccountToken);
    Response getSpecificAccount(String userID, String token);


}
