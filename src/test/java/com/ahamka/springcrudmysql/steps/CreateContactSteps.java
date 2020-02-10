package com.ahamka.springcrudmysql.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.ahamka.springcrudmysql.model.Contact;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import java.util.List;

/**
 * Step Definition Class for Contact.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class CreateContactSteps extends AbstractSteps implements En {

  public CreateContactSteps() {

    Given("user wants to create a contact with the following attributes", (DataTable contactDt) -> {
      testContext().reset();
      List<Contact> contactList = contactDt.asList(Contact.class);

      // First row of DataTable has the contact attributes hence calling get(0) method.
      super.testContext()
          .setPayload(contactList.get(0));
    });

    // And("with the following phone numbers", (DataTable phoneDt) -> {
    //   List<Phone> phoneList = phoneDt.asList(Phone.class);
    //   super.testContext()
    //       .getPayload(Employee.class)
    //       .setPhones(phoneList);
    // });

    When("user saves the new contact {string}", (String testContext) -> {
      String createContactUrl = "/contacts";

      // AbstractSteps class makes the POST call and stores response in TestContext
      executePost(createContactUrl);
    });

    /**
     * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
     */
    Then("the save {string}", (String expectedResult) -> {
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
