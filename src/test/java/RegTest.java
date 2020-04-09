import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegTest {
    @Test
    public void registerSuccessfulTest() {
        JSONObject productJson = new JSONObject();
        productJson.put("email", "eve.holt@reqres.in");
        productJson.put("password", "pistol");
        // System.out.println(productJson.toJSONString());

        Response response = given().
                contentType("application/json").
                accept("application/json, text/plain, */*").
                body(productJson.toJSONString()).
                when().
                post("https://reqres.in/api/register").
                then()
                .statusCode(200)
                .and()
                .body("id", equalTo(4))
                .and()
                .body("token", equalTo("QpwL5tke4Pnpja7X4")).
                        extract().response().andReturn();
        System.out.println(response.getBody().print());
    }
    @Test
    public void registerUnsuccessfulTest() {
        JSONObject productJson = new JSONObject();
        productJson.put("email", "sydney@fife");

        Response response = given().
                contentType("application/json").
                accept("application/json, text/plain, */*").
                body(productJson.toJSONString()).
                when().
                post("https://reqres.in/api/register").
                then()
                .statusCode(400)
                .and()
                .body("error", equalTo("Missing password")).
                        extract().response().andReturn();
        System.out.println(response.getBody().print());
    }



}
