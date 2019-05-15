package com.project.oilpricing.cucumber;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OilpricingCucumberSteps {
	
	private int statusCode = 0;
	
	@When("^the client calls /list/oil$")
	public void the_client_issues_GET_version() throws Throwable{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet();
		request.addHeader("content-type", "application/json");
		request.setURI(URI.create("http://localhost:8080/list/oil"));
		HttpResponse response = httpClient.execute(request);
		this.statusCode = response.getStatusLine().getStatusCode();
	}

	@Then("^client receives status code of (\\d+)$")
	public void client_receives_status_code_of(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(this.statusCode, statusCode);
	}

}
