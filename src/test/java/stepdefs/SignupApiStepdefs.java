package stepdefs;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import pojo.LoginPojo;
import pojo.SignUpPojo;;
import java.util.HashMap;
import static io.restassured.RestAssured.*;



public class SignupApiStepdefs {
    static String accessToken;
    static String accessTokenuser;
    static String UserID;
    LoginPojo loginPojo = new LoginPojo();

    @When("logged in with {string} and {string} in api and success message must be true")
    public void loggedInWithValidAndInApi(String email, String password) {

        SignUpPojo signUpPojo = new SignUpPojo(email, password);
        given()
                .contentType(ContentType.JSON)
                .body(new HashMap<String, SignUpPojo>() {{
                    put("params", signUpPojo);
                }})
                .log().all()
                .when()
                .post("auth/signUp")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
//                .statusCode(200)
                .log().all()
                .body("success", is(equalTo(true)));

    }
    @And("new signed up user log in with {string} and {string} and take id of new user")
    public void newSignedUpUserLogInWithAndAndTakeIdOfNewUser(String email, String password) {
        loginPojo.setUsername(email);
        loginPojo.setPassword(password);
        loginPojo.setDevice_os("web");
        accessTokenuser = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(loginPojo).
                when().post("auth/login").then().extract().response().path("payload.session.access.token");

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(loginPojo)
                .log().all()
                .when().post("auth/login")
                .then().assertThat()
                .contentType(ContentType.JSON)
                .log().all()
                .body("success", is(equalTo(true)))
                .extract().response();

        UserID = response.jsonPath().get("payload.user.id");

        given().contentType(ContentType.JSON).header("Authorization", accessTokenuser).and().accept(ContentType.JSON).when().post("auth/logout").
                then().assertThat().body("success", is(equalTo(true)));

    }

    @And("root user logs in with valid email {string} and {string} and delete user")
    public void rootUserLogsInWithValidEmailAnd(String email, String password) {
        loginPojo.setUsername(email);
        loginPojo.setPassword(password);
        loginPojo.setDevice_os("web");

        accessToken = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(loginPojo).
                when().post("auth/login").then().extract().response().path("payload.session.access.token");

        given().accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(loginPojo)
                .log().all().
                when().post("auth/login").
                then().assertThat().
                contentType(ContentType.JSON).
                log().all().
                body("success", is(equalTo(true)));
        //Assert that the user is available with get
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .when().get("account/user/" + UserID)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);

        // Delete the user.
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .when().delete("account/user/" + UserID)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK);

        // Assert that the user is deleted.
        try {
            given().accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .header("Authorization", accessToken)
                    .when().get("account/user/" + UserID)
                    .then().assertThat()
                    .body("error.message",is(equalTo("User not found")));
        } catch (Exception   e) {
            System.out.println(e.getMessage());
        }

    }

    @When("logged in with invalid {string} and {string} in api and success message must be false")
    public void loggedInWithInvalidAndInApiAndSuccessMessageMustBe(String email, String password) {
        SignUpPojo signUpPojo = new SignUpPojo(email, password);
        given()
                .contentType(ContentType.JSON)
                .body(new HashMap<String, SignUpPojo>() {{
                    put("params", signUpPojo);
                }})
                .log().all()
                .when()
                .post("auth/signUp")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .log().all()
                .body("success", is(equalTo(false)));
    }



}

