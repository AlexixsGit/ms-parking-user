package com.parking.users.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloudant.client.api.Database;
import com.parking.users.exceptions.ApiException;
import com.parking.users.model.User;
import com.parking.users.util.DatabaseApi;
import com.parking.users.util.YamlProperties;

@Repository
public class UserRepository implements IUserRepository {

	@Autowired
	private DatabaseApi databaseApi;

	private Database db;

	@Autowired
	private YamlProperties yamlProperties;

	@Override
	public User getUserByCardNumber(String cardNumber) throws ApiException {

		try {
			db = databaseApi.getCloudantConnection();

			List<User> users = db.search(yamlProperties.searchIndexByCardNumber).includeDocs(true)
					.query(String.format("cardNumber: '%s'", cardNumber), User.class);

			if (users.size() == 0) {
				return null;
			}
			return users.get(0);
		} catch (Exception e) {
			throw new ApiException(e);
		}
	}
}
