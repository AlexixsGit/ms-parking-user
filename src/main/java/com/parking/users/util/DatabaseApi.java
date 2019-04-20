package com.parking.users.util;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${cloudant.connection.account}")
	public String account;

	@Value("${cloudant.connection.user_name}")
	public String username;

	@Value("${cloudant.connection.user_password}")
	public String password;

	@Value("${cloudant.connection.database_name}")
	public String databaseName;

	/**
	 * Connect to cloudant database
	 * 
	 * @return
	 */
	public Database getCloudantConnection() {
		CloudantClient cloudantClient = ClientBuilder.account(account).username(username).password(password).build();
		return cloudantClient.database(databaseName, false);
	}

}
