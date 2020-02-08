package com.ahamka.springcrudmysql.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import cucumber.api.java8.En;
import io.restassured.response.Response;


/**
 * Step Definition Class for Contact.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class UpdateContactSteps extends AbstractSteps implements En {

    public UpdateContactSteps() {

        Given("user wants to update a contact with the following attributes", () -> {
            testContext().reset();
        });
        
        When("user updates contact by mail {string}", (String email) -> {        
            String putContactsUrl = "/contacts/email/"+email;

            // AbstractSteps class makes the GET call and stores response in TestContext
            executePut(putContactsUrl);
        });
        
        Then("the update {string}", (String expectedResult) -> {
            
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



