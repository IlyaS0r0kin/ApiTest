import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class GetTest {
    @DataProvider(name = "getRestParameters")
    public Object[][] dataForRest() throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("data.txt"));
        Object[][] outArray = new Object[allLines.size()][2];
        for (int i = 0; i < allLines.size(); i++) {
            String[] tokens = allLines.get(i).split(",");
            outArray[i][0] = tokens[0];
            outArray[i][1] = Integer.parseInt(tokens[1]);
        }
        return outArray;
    }
    @Test
    public void getListUsersPage() {
    when()
            .get("https://reqres.in/api/users?page=2")
            .then()
            .statusCode(200)
            .and()
            .body("page", equalTo(2))
            .body("per_page", equalTo(6))
            .body("total", equalTo(12));
}
    @Test
    public void getListUsersPerPage() {
        when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .and()
                .body("data.last_name[0]", equalTo("Lawson"));
    }
    @Test
    public void getListUsersTotal() {
        when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .and()
                .body("data.first_name[5]", equalTo("Rachel"));
    }
    @Test(dataProvider = "getRestParameters")
    public void getListUsersDataFirstName(String inId, Integer outId) {
        when()
                .get("https://reqres.in/api/users/{id}", inId)
                .then()
                .statusCode(200)
                .and()
                .body("data.id", equalTo(outId));
    }
}
