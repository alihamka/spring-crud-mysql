package com.ahamka.springcrudmysql.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * To run cucumber test.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",glue = {"com.ahamka.springcrudmysql"}, plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTest {

}
