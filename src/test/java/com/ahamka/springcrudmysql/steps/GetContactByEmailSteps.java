package com.ahamka.springcrudmysql.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ahamka.springcrudmysql.model.Contact;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Step Definition Class for Contact.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class GetContactByEmailSteps extends AbstractSteps implements En {

    public GetContactByEmailSteps() {

        Given("user wants to call get_contact_by_email", () -> {
            testContext().reset();
        });
        
        When("user calls get_contact_by_email with {string}", (String email) -> {        
            String getContactsUrl = "/contacts/email/"+email;

            // AbstractSteps class makes the GET call and stores response in TestContext
            executeGet(getContactsUrl);
        });
        
        Then("the get by email result is$", (DataTable dt) -> {

            Response response = testContext().getResponse();

            io.restassured.path.json.JsonPath jsonPathEvaluator = response.getBody().jsonPath();

            HashMap<String, String> actualContact = jsonPathEvaluator.get("");

            List<Contact> expectedContacts = dt.asList(Contact.class);
            Iterator <Contact> expected = expectedContacts.iterator();

            Contact expectedContact = expected.next();
            
            assertEquals(actualContact.get("name"), expectedContact.getName());
            assertEquals(actualContact.get("email"), expectedContact.getEmail());
            assertEquals(actualContact.get("phone"), expectedContact.getPhone());
            
        });

  }
}



