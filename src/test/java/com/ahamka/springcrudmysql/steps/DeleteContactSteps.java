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
public class DeleteContactSteps extends AbstractSteps implements En {

    public DeleteContactSteps() {

        Given("user wants to call del_contact_by_email", () -> {
            testContext().reset();
        });
        
        When("user calls del_contact_by_email with {string}", (String email) -> {        
            String getContactsUrl = "/contacts/email/"+email;

            // AbstractSteps class makes the DELETE call and stores response in TestContext
            executeDelete(getContactsUrl);
        });
        
        Then("the delete {string}", (String expectedResult) -> {
            
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
