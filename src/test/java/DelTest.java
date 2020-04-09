import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class DelTest {

    @Test
public void deleteTest() {
    when()
            .delete("https://reqres.in/api/users/2")
            .then()
            .statusCode(204);

}
    @Test
    public void deleteSecondTest() {
        when()
                .delete("https://reqres.in/api/users/1")
                .then()
                .statusCode(204);

    }
}
