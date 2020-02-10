package com.ahamka.springcrudmysql.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.List;

import com.ahamka.springcrudmysql.model.Contact;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;


/**
 * Step Definition Class for Contact.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class UpdateContactSteps extends AbstractSteps implements En {

    public UpdateContactSteps() {

        Given("user prepars update data", 
            (DataTable contactDt) -> {
            
                testContext().reset();                

                List<Contact> contactList = contactDt.asList(Contact.class);
                      // First row of DataTable has the contact attributes hence calling get(0) method.
                super.testContext()
                    .setPayload(contactList.get(0));
             });

          When("user calls update {string} with new data", (String email) -> {
                String putContactsUrl = "/contacts/email/"+email;      
                executePut(putContactsUrl);
          });

                
        Then("the update result {string}", (String expectedResult) -> {
            
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



