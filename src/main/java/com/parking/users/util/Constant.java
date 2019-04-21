package com.parking.users.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

	@Value("${cloudant.search_index}")
	public String searchIndexByCardNumber;

	@Value("${cloudant.connection.account}")
	public String account;

	@Value("${cloudant.connection.user_name}")
	public String username;

	@Value("${cloudant.connection.user_password}")
	public String password;

	@Value("${cloudant.connection.database_name}")
	public String databaseName;
}
