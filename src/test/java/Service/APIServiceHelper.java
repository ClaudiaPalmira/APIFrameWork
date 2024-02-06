package Service;

import LoggerUtility.LoggerUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.internal.RequestSpecificationImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIServiceHelper {

    //Clasa contine metode care afiseaza informatii despre REQUEST si RESPONSE

    public static void requestLogs(RequestSpecification requestSpecification, String path, String methodType){
        LoggerUtility.info("=== Request Info ===");
        LoggerUtility.info(getRequestUrl(path));
        LoggerUtility.info(getRequestMethod(methodType));
        LoggerUtility.info(getRequestBody(requestSpecification));
    }

    public static void responseLogs(Response response){
        LoggerUtility.info("=== Response Info ===");
        LoggerUtility.info(getResponseStatusCode(response));
        LoggerUtility.info(getResponseStatus(response));
        LoggerUtility.info(getResponseBody(response));

    }

    private static String getResponseStatusCode(Response response){
        return "Response status: " + response.getStatusLine();  //ne ofera statusul
    }

    private static String getResponseStatus(Response response){
        return "Response status code: " + response.getStatusCode(); //ne ofera status code
    }

    private static String getRequestUrl(String path){
        return "Request URL: " + "https://demoqa.com" + path;

    }

    private static String getRequestMethod(String method){
        return "Request METHOD: " +  method;
    }

    private static String getRequestBody(RequestSpecification requestSpecification){
        String requestBody = "Request BODY: \n";
        Object object = ((RequestSpecificationImpl) requestSpecification).getBody();  //scoatem body-ul de pe request

        if(object!= null){
            ObjectMapper mapper = new ObjectMapper();
            try {
                requestBody = requestBody + mapper.readTree(object.toString()).toPrettyString();
            } catch (JsonProcessingException e) {

            }

        }
        return requestBody;
    }

    private static String getResponseBody(Response response){
        if(response.getBody() != null){
            return "Response Body: \n" + response.getBody().asPrettyString();
        }
        else{
            return "Response Body \n";
        }

    }
}
