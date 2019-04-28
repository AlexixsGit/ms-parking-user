package com.parking.users.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

/**
 * Class to manage database operations
 * 
 * @author alexis
 *
 */
@Component
public class DatabaseApi {

	@Autowired
	private YamlProperties yamlProperties;

	/**
	 * Connect to cloudant database
	 * 
	 * @return
	 */
	public Database getCloudantConnection() {
		CloudantClient cloudantClient = ClientBuilder.account(this.yamlProperties.account).username(this.yamlProperties.username)
				.password(this.yamlProperties.password).build();
		return cloudantClient.database(this.yamlProperties.databaseName, false);
	}

}
