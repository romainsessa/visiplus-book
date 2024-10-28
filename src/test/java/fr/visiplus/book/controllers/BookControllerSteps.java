package fr.visiplus.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookControllerSteps extends SpringIntegrationTest {

	@Autowired
	protected MockMvc mockMvc;

	private ResultActions resultActions;

	@Given("the client calls book endpoint")
	public void the_client_calls_book() throws Exception {
		resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/book"));
	}

	@When("the response is provided")
	public void the_response_is_provided_to_user() throws Exception {
		resultActions = resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Then("the client receives a list with {int} elements")
	public void on_retourne(int expectedResult) throws Exception {
		resultActions.andExpect(jsonPath("$",hasSize(expectedResult)));
	}
		
}
