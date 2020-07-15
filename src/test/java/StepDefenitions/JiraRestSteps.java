package StepDefenitions;

import Utils.PayloadUtil;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class JiraRestSteps {

    Response response;

    public static String jiraCookie;
    public int id;
    public int springID;
    public int summerID;
    public int winterID;

    @Given("the has an authorization in jira")
    public void the_has_an_authorization_in_jira() throws IOException, URISyntaxException {
        jiraCookie = PayloadUtil.getJsessionCookie();
    }

    @When("the user creates three new sprints with {string}, {string}, {string}, {int}, {string}")
    public void the_user_creates_three_new_sprints_with(String name, String startDate, String endDate, Integer boardId, String sprintGoal) throws IOException, URISyntaxException {


        response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Cookie", jiraCookie)
                .body(PayloadUtil.getSprintPayload(name, startDate, endDate, boardId, sprintGoal))
                .when().post("http://localhost:8080/rest/agile/1.0/sprint")
                .then().assertThat().statusCode(201).and().contentType(ContentType.JSON).extract().response();


        id = response.path("id");
        PayloadUtil.sprintIDs.add(id);

    }

    @Then("the user should see sprint ids")
    public void the_user_should_see_sprint_ids() {
        System.out.println(PayloadUtil.sprintIDs);

    }

    @When("the user creates issues with sprintID {string} {string} {string}")
    public void the_user_creates_issues_with_sprintID(String summary, String description, String issueType) {
        Faker faker = new Faker();

        summary = faker.job().keySkills();
        description = faker.chuckNorris().fact();

        issueType = PayloadUtil.generateStatus();

        for(int i =0; i<30; i++){
        response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Cookie", jiraCookie)
                .body(PayloadUtil.getIssuePayloadSprint(summary, description , issueType, PayloadUtil.sprintIDs.get(0)))
                .when().post("http://localhost:8080/rest/api/2/issue")
                .then().assertThat().statusCode(201).and().contentType(ContentType.JSON).extract().response();

        }


    }


}
