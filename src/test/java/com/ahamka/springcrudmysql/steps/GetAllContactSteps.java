package com.ahamka.springcrudmysql.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ahamka.springcrudmysql.model.Contact;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import java.util.Iterator;
import java.util.List;

/**
 * Step Definition Class for Contact.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class GetAllContactSteps extends AbstractSteps implements En {

    public GetAllContactSteps() {

        Given("user wants to call get all contacts", () -> {
            testContext().reset();
        });
        
        When("user calls get all contacts", () -> {
            String getContactsUrl = "/contacts";

            // AbstractSteps class makes the GET call and stores response in TestContext
            executeGet(getContactsUrl);
        });
        
        /**
         * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
         */
        Then("the result is$", (DataTable dt) -> {

            Response response = testContext().getResponse();

            io.restassured.path.json.JsonPath jsonPathEvaluator = response.getBody().jsonPath();

            List<Contact> actualContacts = jsonPathEvaluator.getList("", Contact.class);
            List<Contact> expectedContacts = dt.asList(Contact.class);

            assertEquals(actualContacts.size(), expectedContacts.size());

            Iterator <Contact> actual   = actualContacts.iterator();
            Iterator <Contact> expected = expectedContacts.iterator();

            while (actual.hasNext() &&  expected.hasNext()) {
                
                Contact expectedContact = expected.next();
                Contact actualContact = actual.next();

                assertEquals(actualContact.getName(), expectedContact.getName());
                assertEquals(actualContact.getEmail(), expectedContact.getEmail());
                assertEquals(actualContact.getPhone(), expectedContact.getPhone());
            }            
        });

  }
}



