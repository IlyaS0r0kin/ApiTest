import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostTest {

    @Test
    public void postTest() {
        JSONObject productJson = new JSONObject();
        productJson.put("name", "morpheus");
        productJson.put("job", "leader");
       // System.out.println(productJson.toJSONString());

        Response response = given().
                contentType("application/json").
                accept("application/json, text/plain, */*").
                body(productJson.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then()
                .statusCode(201)
                .and()
                .body("name", equalTo("morpheus")).
                extract().response().andReturn();
        System.out.println(response.getBody().print());
    }
    @Test
    public void updateTest() {
        JSONObject productJson = new JSONObject();
        productJson.put("name", "morpheus");
        productJson.put("job", "zion resident");


        Response response = given().
                contentType("application/json").
                accept("application/json, text/plain, */*").
                body(productJson.toJSONString()).
                when().
                put("https://reqres.in/api/users/2").
                then()
                .statusCode(200)
                .and()
                .body("name", equalTo("morpheus")).
                        extract().response().andReturn();
        System.out.println(response.getBody().print());
    }



}
