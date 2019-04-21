package com.parking.users.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloudant.client.api.Database;
import com.parking.users.exceptions.ApiException;
import com.parking.users.model.User;
import com.parking.users.util.Constant;
import com.parking.users.util.DatabaseApi;

@Repository
public class UserRepository implements IUserRepository {

	@Autowired
	private DatabaseApi databaseApi;

	@Autowired
	private Constant constant;

	@Override
	public User getUserByCardNumber(String cardNumber) throws ApiException {

		Database db = databaseApi.getCloudantConnection();

		try {
			List<User> users = db.search(constant.searchIndexByCardNumber).includeDocs(true)
					.query(String.format("cardNumber: %s", cardNumber), User.class);
			return users.get(0);
		} catch (Exception e) {
			throw new ApiException(e);
		}
	}

}
