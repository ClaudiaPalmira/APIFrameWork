package Service;

import Rest.RestRequest;
import Rest.RestRequestType;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonAPIService {

    //Aceasta clasa contine metode pentru tipuri de requesturi cu diferiti parametri (vom folosi polimorfismul)

    public Response post(Object body, String url){  //post fara token, prin polimorfism
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(body);

        //aici vom introduce logurile pentru request si response

        Response response = performRequest(RestRequestType.REQUEST_POST, requestSpecification, url);
        return response;
    }

    public Response post(Object body, String url, String token){  //post cu token, prin polimorfism
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);
        requestSpecification.body(body);

        //aici vom introduce logurile pentru request si response

        Response response = performRequest(RestRequestType.REQUEST_POST, requestSpecification, url);
        return response;
    }
    public Response get(String url, String token){  //post cu token, prin polimorfism
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer " + token);


        //aici vom introduce logurile pentru request si response

        Response response = performRequest(RestRequestType.REQUEST_GET, requestSpecification, url);
        return response;
    }

    //Facem o instanta de RestRequest care sa apeleze metoda de performRequest

    private Response performRequest(String requestType, RequestSpecification requestSpecification, String url){
        return new RestRequest().performRequest(requestType, requestSpecification, url);

    }
}
