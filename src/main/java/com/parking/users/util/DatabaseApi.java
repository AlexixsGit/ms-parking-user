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

	public static final String USER_NAME = "";
	public static final String PASSWORD = "";

	/**
	 * Connect to cloudant database
	 * 
	 * @return
	 */
	public Database getCloudantConnection() {
		CloudantClient cloudantClient = ClientBuilder.account(USER_NAME).username(USER_NAME)
				.password(PASSWORD).build();
		return cloudantClient.database("parking_users", false);
	}

}
