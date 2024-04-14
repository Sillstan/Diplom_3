package Build;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API {
    private final Faker faker = new Faker();
    private final String email = randomEmail();
    private final String password = randomPassword();
    private final String name = randomName();
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    public static final String CREATE_USER_ENDPOINT = "api/auth/register";
    public static final String CHANGE_USER_ENDPOINT = "api/auth/user";

    protected static RequestSpecification baseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }
    private String randomEmail() {
        return faker.internet().emailAddress();
    }

    private String randomPassword() {
        return faker.internet().password();
    }

    private String randomName() {
        return faker.name().username();
    }
    public Response createRandomUser() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("name", name);
        return given()
                .spec(baseRequestSpec())
                .body(requestBody)
                .post(CREATE_USER_ENDPOINT)
                .thenReturn();
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void deleteUser(String accessToken) {
        given()
                .spec(baseRequestSpec())
                .header("Authorization", accessToken)
                .delete(CHANGE_USER_ENDPOINT);
    }
}
