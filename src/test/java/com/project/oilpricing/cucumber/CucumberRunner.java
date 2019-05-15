package com.project.oilpricing.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = {"stories"},
	glue={"com.project.oilpricing.cucumber"},
	plugin = {"pretty", "html:target/cucumber"}
)
public class CucumberRunner {

}
