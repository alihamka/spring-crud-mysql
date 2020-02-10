package com.ahamka.springcrudmysql.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import cucumber.api.java8.En;
import io.restassured.response.Response;

public class GetContactByInvalidEmail extends AbstractSteps implements En {

    public GetContactByInvalidEmail() {

        Given("user wants to call get_contact_by_invalid_email", () -> {
            testContext().reset();
        });
        
        When("user calls get_contact_by_invalid_email with {string}", (String email) -> {        
            String getContactsUrl = "/contacts/email/"+email;

            // AbstractSteps class makes the GET call and stores response in TestContext
            executeGet(getContactsUrl);
        });
        
        Then("the get invalid contact result is {string}", (String expectedResult) -> {
            
            Response response = testContext().getResponse();

            switch (expectedResult) {
                case "IS SUCCESSFUL":
                    assertThat(response.statusCode()).isIn(200, 201);
                break;

                case "FAILS":
                    assertThat(response.statusCode()).isBetween(400, 504);
                break;

                default:
                    fail("Unexpected error");
            }
        });

  }
}



