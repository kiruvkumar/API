package stepdefinition;

import helpers.ToDoHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ToDoStepDefinition {

    public static ResponseOptions<Response> response;
    public static HttpURLConnection postResponse;

    @Given("I use the GET endpoint {string}")
    public void iUseTheGETEndpoint(String pathUrl) {
        // invoke GET request
        response = ToDoHelper.GetToDo(pathUrl);
    }

    @Then("I should be able to view all available ToDo items")
    public void iShouldBeAbleToViewAllAvailableToDoItems() {
        // validate status code and size of items returned
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.getBody().jsonPath().getList("id").size(), greaterThan(0));
    }

    @Then("I should be able to view ToDo details of the specific item")
    public void iShouldBeAbleToViewToDoDetailsOfTheSpecificItem() {
        // validate status code and item details
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.getBody().jsonPath().get("id"), equalTo(25));
        assertThat(response.getBody().jsonPath().get("userId"), equalTo(2));
        assertThat(response.getBody().jsonPath().get("title"), equalTo("voluptas quo tenetur perspiciatis explicabo natus"));
        assertThat(response.getBody().jsonPath().get("completed"), equalTo(true));
    }

    @Given("I use the POST endpoint \\/todos with a request body")
    public void iUseThePOSTEndpointTodosWithARequestBody() throws IOException {
        // invoke POST request
        postResponse = ToDoHelper.PostToDo();
    }

    @Then("I should be able to create a new ToDo item")
    public void iShouldBeAbleToCreateANewToDoItem() throws IOException {
        // assert creation
        assertThat(postResponse.getResponseCode(), equalTo(201));
    }
}
