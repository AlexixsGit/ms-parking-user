package com.parking.users.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class YamlProperties {

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

	@Value("${app.errors.card_number_empty.code}")
	public String appErrorsCardNumberEmptyCode;

	@Value("${app.errors.card_number_empty.message}")
	public String appErrorsCardNumberEmptyMessage;

	@Value("${app.errors.required_field_empty.code}")
	public String appErrorsRequiredFieldEmptyCode;

	@Value("${app.errors.required_field_empty.message}")
	public String appErrorsRequiredFieldEmptyMessage;

	@Value("${app.path.user_validation}")
	public String appPathUserValidation;
}
