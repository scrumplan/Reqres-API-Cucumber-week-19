package in.reqres.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.reqres.userinfo.UsersSteps;
import in.reqres.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;


public class UserStepdefs {

    static String first_name = "Lalita"+ TestUtils.getRandomValue();
    static String job = "Tester";
    static int userId;
    static ValidatableResponse response;

    @Steps
    UsersSteps userSteps;

    @Given("^I am on homepage of the given url$")
    public void iAmOnHomepageOfTheGivenUrl() {
    }

    @When("^I send POST request to the the application using a valid payload to create a User$")
    public void iSendPOSTRequestToTheTheApplicationUsingAValidPayloadToCreateAUser() {
        response = userSteps.createUser(first_name,job);
    }

    @Then("^I get status code (\\d+)$")
    public void iGetStatusCode(int exp) {
        response.log().all().statusCode(exp);
    }

    @And("^I verify if user has been added to the application$")
    public void iVerifyIfUserHasBeenAddedToTheApplication() {
        first_name = "Paul";
        HashMap<String, Object> userMap = userSteps.getUserInfoByFirstName(first_name);
        Assert.assertThat(userMap, hasValue(first_name));
        userId = (int) userMap.get("id");
        System.out.println(userId);
    }

    @When("^I send PUT request to the the application using a valid payload to update an User$")
    public void iSendPUTRequestToTheTheApplicationUsingAValidPayloadToUpdateAnUser() {

        first_name = first_name + "_updated";
        job = job + "01";
        userId = 7;
        response = userSteps.updateUser(userId, first_name, job);
    }

    @Then("^I will get status code (\\d+)$")
    public void iWillGetStatusCode(int exp) {
        response.statusCode(exp);
    }

    @And("^I verify if user has been updated$")
    public void iVerifyIfUserHasBeenUpdated() {
        response.body("name",equalTo(first_name),"job",equalTo(job));
    }

    @When("^I send Delete request to the the application using userId$")
    public void iSendDeleteRequestToTheTheApplicationUsingUserId() {
        userId = 11;
        response = userSteps.deleteUser(userId);
    }

    @And("^I verify if user has been deleted$")
    public void iVerifyIfUserHasBeenDeleted() {
        response.statusCode(204).log().status();
    }
}
