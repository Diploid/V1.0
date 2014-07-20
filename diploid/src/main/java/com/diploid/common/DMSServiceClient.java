/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.common;

import javax.management.RuntimeErrorException;
import javax.ws.rs.core.MediaType;

import com.diploid.entity.ResponseForm;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class DMSServiceClient {

	// Adding entry via UI
	public void AddEntryUI(ResponseForm responseForm) {
		System.out.println("got it here");
		// Create client configuration
		ClientConfig clientConfig = new DefaultClientConfig();

		// make mapping
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);

		// create client
		Client client = Client.create(clientConfig);

		// Create Web-resources //need to change
		WebResource webResource = client
				.resource("http://raghu:8080/diploid-1.0/rest/jsonService/add");

		ClientResponse response = webResource
				.type(MediaType.APPLICATION_JSON_TYPE)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.post(ClientResponse.class, responseForm);

		/*
		 * ClientResponse response =
		 * webResource.type(MediaType.APPLICATION_JSON).post(
		 * ClientResponse.class, responseForm);
		 */
		// create client response
		// ClientResponse response =
		// webResource.type("application/json").accept(new
		// MediaType("application", "json")).post(ClientResponse.class ,
		// responseForm);

		// check response from server
		if (response.getStatus() != 201) {
			throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE "
					+ response.getStatus());
		}

		// if all well, get response from server
		System.out.println("Response from server.... "
				+ response.getEntity(String.class));

	}
}
